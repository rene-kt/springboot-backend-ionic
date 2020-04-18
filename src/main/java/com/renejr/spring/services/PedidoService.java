/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Cliente;
import com.renejr.spring.domain.ItemPedido;
import com.renejr.spring.domain.PagamentoComBoleto;
import com.renejr.spring.domain.Pedido;
import com.renejr.spring.enums.EstadoPagamento;
import com.renejr.spring.repositories.ItemPedidoRepository;
import com.renejr.spring.repositories.PagamentoRepository;
import com.renejr.spring.services.exceptions.ObjectNotFoundException;
import com.renejr.spring.repositories.PedidoRepository;
import com.renejr.spring.security.UserSS;
import com.renejr.spring.services.exceptions.AuthorizationException;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Informática
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private PagamentoRepository Pagamentorepo;
    
     @Autowired
    private ItemPedidoRepository itemPedidoRepo;

    @Autowired
    private ProdutoService produtoService;
    
     @Autowired
    private ClienteService clienteService;
     
      @Autowired
    private EmailService emailService;
     

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);

        obj.setInstante(new Date());
        
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);

        obj.getPagamento().setPedido(obj);

        if (obj.getPagamento() instanceof PagamentoComBoleto) {

            PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();

            BoletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
        }

        obj = repo.save(obj);
        Pagamentorepo.save(obj.getPagamento());

        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            
            ip.setPreco(ip.getProduto().getPreco());
            
            ip.setPedido(obj);
        }
        itemPedidoRepo.saveAll(obj.getItens());
        
        emailService.sendOrderConfirmationHtmlEmail(obj);
        
        return obj;
    }
      public Page<Pedido> findPage(Integer page, Integer line_per_page, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        
        if(user == null){
            throw new AuthorizationException("Acesso negado");
        }
          
          PageRequest page_request = PageRequest.of(page, line_per_page, Direction.valueOf(direction), orderBy);
        
          Cliente cliente = clienteService.find(user.getId());
          
        return repo.findByCliente(cliente, page_request);
    }
}
