package com.rychard.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rychard.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "informatica");
		Categoria cat2 = new Categoria(2, "escritorio");
		
		List<Categoria> list = new ArrayList<Categoria>();
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}
	
}
