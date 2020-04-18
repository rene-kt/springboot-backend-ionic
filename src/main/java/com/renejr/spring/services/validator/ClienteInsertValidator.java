package com.renejr.spring.services.validator;

import com.renejr.spring.domain.Cliente;
import com.renejr.spring.dto.ClienteNewDto;
import com.renejr.spring.enums.TipoCliente;
import com.renejr.spring.repositories.ClienteRepository;
import com.renejr.spring.resources.exception.FieldMessage;
import com.renejr.spring.services.validator.util.DocumentUtil;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod())
                && !DocumentUtil.isValidCpf(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("CpfOuCnpj", "CPF Inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod())
                && !DocumentUtil.isValidCnpj(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("CpfOuCnpj", "CNPJ Inválido"));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());

        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));

        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField_name()).addConstraintViolation();
        }
        return list.isEmpty();
    }

}
