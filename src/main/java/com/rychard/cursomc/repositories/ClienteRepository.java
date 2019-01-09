package com.rychard.cursomc.repositories;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rychard.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly=true)//importar do springframework
	Cliente findByEmail(String email);//o spring data indentifica pelo findBy q vc esta querendo buscar um email
}
