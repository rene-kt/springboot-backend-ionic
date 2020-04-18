/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Categoria;
import com.renejr.spring.domain.Cidade;
import com.renejr.spring.domain.Cliente;
import com.renejr.spring.domain.Endereco;
import com.renejr.spring.domain.Estado;
import com.renejr.spring.domain.ItemPedido;
import com.renejr.spring.domain.Pagamento;
import com.renejr.spring.domain.PagamentoComBoleto;
import com.renejr.spring.domain.PagamentoComCartao;
import com.renejr.spring.domain.Pedido;
import com.renejr.spring.domain.Produto;
import com.renejr.spring.enums.EstadoPagamento;
import com.renejr.spring.enums.Perfil;
import com.renejr.spring.enums.TipoCliente;
import com.renejr.spring.repositories.CategoriaRepository;
import com.renejr.spring.repositories.CidadeRepository;
import com.renejr.spring.repositories.ClienteRepository;
import com.renejr.spring.repositories.EnderecoRepository;
import com.renejr.spring.repositories.EstadoRepository;
import com.renejr.spring.repositories.ItemPedidoRepository;
import com.renejr.spring.repositories.PagamentoRepository;
import com.renejr.spring.repositories.PedidoRepository;
import com.renejr.spring.repositories.ProdutoRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Informática
 */
@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void instantiateTestDatabase() throws ParseException {
//
//        Categoria cat1 = new Categoria(null, "Informática");
//        Categoria cat2 = new Categoria(null, "Escritório");
//        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
//        Categoria cat4 = new Categoria(null, "Eletrônicos");
//        Categoria cat5 = new Categoria(null, "Jardinagem");
//        Categoria cat6 = new Categoria(null, "Decoração");
//        Categoria cat7 = new Categoria(null, "Perfumaria");
//
//        Produto p1 = new Produto(null, "Computador", 2000.00);
//        Produto p2 = new Produto(null, "Impressora", 800.00);
//        Produto p3 = new Produto(null, "Mouse", 80.00);
//        Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
//        Produto p5 = new Produto(null, "Toalha", 50.00);
//        Produto p6 = new Produto(null, "Colcha", 200.00);
//        Produto p7 = new Produto(null, "TV true color", 1200.00);
//        Produto p8 = new Produto(null, "Roçadeira", 800.00);
//        Produto p9 = new Produto(null, "Abajour", 100.00);
//        Produto p10 = new Produto(null, "Pendente", 180.00);
//        Produto p11 = new Produto(null, "Shampoo", 90.00);
//
//        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
//        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
//        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
//        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
//        cat5.getProdutos().addAll(Arrays.asList(p8));
//        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
//        cat7.getProdutos().addAll(Arrays.asList(p11));
//
//        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
//        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
//        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
//        p4.getCategorias().addAll(Arrays.asList(cat2));
//        p5.getCategorias().addAll(Arrays.asList(cat3));
//        p6.getCategorias().addAll(Arrays.asList(cat3));
//        p7.getCategorias().addAll(Arrays.asList(cat4));
//        p8.getCategorias().addAll(Arrays.asList(cat5));
//        p9.getCategorias().addAll(Arrays.asList(cat6));
//        p10.getCategorias().addAll(Arrays.asList(cat6));
//        p11.getCategorias().addAll(Arrays.asList(cat7));
//
//        Estado est1 = new Estado(null, "Minas Gerais");
//        Estado est2 = new Estado(null, "São Paulo");
//
//        Cidade c1 = new Cidade(null, "Uberlândia", est1);
//        Cidade c2 = new Cidade(null, "São Paulo", est2);
//        Cidade c3 = new Cidade(null, "Campinas", est2);
//
//        est1.getCidades().addAll(Arrays.asList(c1));
//        est2.getCidades().addAll(Arrays.asList(c2, c3));
//
//        Cliente cli1 = new Cliente(null, "Maria Silva", "nelio.cursos@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA, pe.encode("123"));
//
//        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
//
//        Cliente cli2 = new Cliente(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", TipoCliente.PESSOA_FISICA, pe.encode("123"));
//        cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
//        cli2.addPerfil(Perfil.ADMIN);
//
//        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
//        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
//        Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);
//
//        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
//        cli2.getEnderecos().addAll(Arrays.asList(e3));
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
//        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
//        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
//
//        Pagamento pagto1 = new PagamentoComCartao(6, null, EstadoPagamento.QUITADO, ped1);
//        ped1.setPagamento(pagto1);
//
//        Pagamento pagto2 = new PagamentoComBoleto(sdf.parse("22/10/2019 10:00"), null, null, EstadoPagamento.QUITADO, ped2);
//        ped2.setPagamento(pagto2);
//
//        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
//
//        ItemPedido item1 = new ItemPedido(ped2, p3, 0.0, 1, 100.0);
//        ItemPedido item2 = new ItemPedido(ped2, p2, 0.0, 2, 200.0);
//        ItemPedido item3 = new ItemPedido(ped2, p1, 0.0, 3, 300.0);
//
//        ped2.getItens().addAll(Arrays.asList(item1, item2, item3));
//
//        p1.getItens().add(item3);
//        p2.getItens().add(item2);
//        p1.getItens().add(item1);
//
//        itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
    }
}
