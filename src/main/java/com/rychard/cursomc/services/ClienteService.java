package com.rychard.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rychard.cursomc.domain.Cliente;
import com.rychard.cursomc.repositories.ClienteRepository;
import com.rychard.cursomc.services.exceptions.ObjectNotFundException;

@Service
public class ClienteService {

	@Autowired//faz a auto intancia
	private ClienteRepository repo;
	
	public Cliente find(Integer id) throws ObjectNotFundException {

		java.util.Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

		}
	
}
