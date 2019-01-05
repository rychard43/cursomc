package com.rychard.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rychard.cursomc.domain.Pedido;
import com.rychard.cursomc.repositories.PedidoRepository;
import com.rychard.cursomc.services.exceptions.ObjectNotFundException;

@Service
public class PedidoService {

	@Autowired//faz a auto intancia
	private PedidoRepository repo;
	
	public Pedido find(Integer id) throws ObjectNotFundException {

		java.util.Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

		}
	
}
