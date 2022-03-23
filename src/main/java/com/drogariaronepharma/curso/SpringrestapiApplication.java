package com.drogariaronepharma.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = { "curso.api.rest.model" }) /* Criar as tabelas automaticas */
@ComponentScan(basePackages = { "curso.*" }) /* anotação Injeção de dependencias tudo que estiver dentro da pasta curso */
@EnableJpaRepositories(basePackages = {"curso.api.rest.repository"})/*Habilitar a parte do crud*/
@EnableTransactionManagement/*Controlar transações no banco de dados*/
@EnableWebMvc/*Habilitar a parte do mvc*/
@RestController
@EnableAutoConfiguration /*Para ter todas as anotações ativadas*/
public class SpringrestapiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestapiApplication.class, args);
	}

}
