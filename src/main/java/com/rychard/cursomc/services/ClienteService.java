package com.rychard.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rychard.cursomc.domain.Cliente;
import com.rychard.cursomc.dto.ClienteDTO;
import com.rychard.cursomc.repositories.ClienteRepository;
import com.rychard.cursomc.services.exceptions.DataIntegrityException;
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
	
	public Cliente update(Cliente obj) {
		
		Cliente newObj = find(obj.getId());//verificar se existe 
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pq há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
