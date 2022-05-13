package com.drogariaronepharma.curso.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drogariaronepharma.curso.model.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long> { //tem que adicionar na classe indexController

}
