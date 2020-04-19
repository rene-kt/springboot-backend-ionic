/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Cidade;
import com.renejr.spring.domain.Cliente;
import com.renejr.spring.domain.Endereco;
import com.renejr.spring.dto.ClienteDto;
import com.renejr.spring.dto.ClienteNewDto;
import com.renejr.spring.enums.Perfil;
import com.renejr.spring.enums.TipoCliente;
import com.renejr.spring.services.exceptions.ObjectNotFoundException;
import com.renejr.spring.repositories.ClienteRepository;
import com.renejr.spring.repositories.EnderecoRepository;
import com.renejr.spring.security.UserSS;
import com.renejr.spring.services.exceptions.AuthorizationException;
import com.renejr.spring.services.exceptions.DataIntegratyException;
import com.renejr.spring.services.exceptions.FileException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Informática
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Cliente find(Integer id) {

        UserSS user = UserService.authenticated();

        /*
        Verificando se o usuário existe, se ele não tiver o perfil de ADMIN e se o id
        dele não for igual o id que ele quer buscar, ou seja, ele só pode buscar por ele msm
         */
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    @Transactional
    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer line_per_page, String orderBy, String direction) {
        PageRequest page_request = PageRequest.of(page, line_per_page, Sort.Direction.valueOf(direction), orderBy);

        return repo.findAll(page_request);
    }

    public Cliente fromDto(ClienteDto dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDto objDto) {

        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), encode.encode(objDto.getSenha()));

        Cidade cid = new Cidade(objDto.getCidade_id(), null, null);
        
        
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());

        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    private Cidade retornar_cidade(Optional<Cidade> obj) {
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado!"));
    }

    public URI upload_profile_picture(MultipartFile multipart) {
        UserSS user = UserService.authenticated();

        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipart);

        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String filename = prefix + user.getId() + ".jpg";

        try {
            return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), filename, "image");
        } catch (IOException ex) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    public Cliente findByEmail(String email) {
        UserSS user = UserService.authenticated();

        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Cliente obj = repo.findByEmail(email);

        if (obj == null) {
            throw new ObjectNotFoundException("Email não encontrado: " + email);

        }

        return obj;
    }
}
