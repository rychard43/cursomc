package com.rychard.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rychard.cursomc.domain.Categoria;
import com.rychard.cursomc.dto.CategoriaDTO;
import com.rychard.cursomc.repositories.CategoriaRepository;
import com.rychard.cursomc.services.exceptions.DataIntegrityException;
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
   public Categoria update(Categoria obj) {
		
	   Categoria newObj = find(obj.getId());//verificar se existe 
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria q possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
