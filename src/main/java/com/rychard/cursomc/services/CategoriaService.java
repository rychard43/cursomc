package com.rychard.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rychard.cursomc.domain.Categoria;
import com.rychard.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired//faz a auto intancia
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		java.util.Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);

	}
	
}
