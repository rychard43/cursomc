package com.rychard.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rychard.cursomc.domain.ItemPedido;
import com.rychard.cursomc.domain.PagamentoComBoleto;
import com.rychard.cursomc.domain.Pedido;
import com.rychard.cursomc.domain.enums.EstadoPagamento;
import com.rychard.cursomc.repositories.ItemPedidoRepository;
import com.rychard.cursomc.repositories.PagamentoRepository;
import com.rychard.cursomc.repositories.PedidoRepository;
import com.rychard.cursomc.services.exceptions.ObjectNotFundException;

@Service
public class PedidoService {

	@Autowired//faz a auto intancia
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Pedido find(Integer id) throws ObjectNotFundException {

		java.util.Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

		}
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		//so pra colocar uma data ficticia pois n esta usando um web service de boleto
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preenceherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());	
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
		
	}
	
}
