package com.rychard.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rychard.cursomc.domain.Pedido;
import com.rychard.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResources {

	@Autowired//intacia automaticamente
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@ PathVariable Integer id) {
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
}