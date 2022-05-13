package com.drogariaronepharma.curso.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drogariaronepharma.curso.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> { //tem que adicionar na classe indexController

	@Query("select u from Usuario u where u.login = ?1")/*Executar o select na tabela usuario onde o login é igual o que foi passado o parametro */
	Usuario findUserByLogin(String login);

}
