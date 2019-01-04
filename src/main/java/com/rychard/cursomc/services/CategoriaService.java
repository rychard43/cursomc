package com.rychard.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rychard.cursomc.domain.Categoria;
import com.rychard.cursomc.repositories.CategoriaRepository;
import com.rychard.cursomc.services.exceptions.ObjectNotFundException;

@Service
public class CategoriaService {

	@Autowired//faz a auto intancia
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) throws ObjectNotFundException {

		java.util.Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

		}
	
}
