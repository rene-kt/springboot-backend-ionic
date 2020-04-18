/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.renejr.spring.services.exceptions.FileException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Informática
 */
@Service
public class S3Service {

    private final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucket;

    public URI uploadFile(MultipartFile multiPartFile) {

        try {
            String file_name = multiPartFile.getOriginalFilename();
            InputStream is = multiPartFile.getInputStream();
            String contextType = multiPartFile.getContentType();
            return uploadFile(is, file_name, contextType);
            
            
        } catch (IOException ex) {
            throw new RuntimeException("Erro de IO"+ex.getMessage());
        }

    }

    public URI uploadFile(InputStream is, String file_name,
            String contextType) throws IOException {

        ObjectMetadata meta = new ObjectMetadata();

        meta.setContentType(contextType);

        LOG.info("Iniciando upload");
        s3client.putObject(new PutObjectRequest(bucket, file_name, is, meta));
        LOG.info("Upload finalizado");

        try {
            return s3client.getUrl(bucket, file_name).toURI();
        } catch (URISyntaxException ex) {
            throw new FileException("Erro ao converter url para uri");
        }
    }

}
