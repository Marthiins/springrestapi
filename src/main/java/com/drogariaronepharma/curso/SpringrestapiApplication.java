package com.drogariaronepharma.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@CrossOrigin
@SpringBootApplication
@EntityScan(basePackages = { "curso.api.rest.model" }) /* Criar as tabelas automaticas */
@ComponentScan(basePackages = { "curso.*" }) /* anotação Injeção de dependencias tudo que estiver dentro da pasta curso */
@EnableJpaRepositories(basePackages = {"curso.api.rest.repository"})/*Habilitar a parte do crud*/
@EnableTransactionManagement/*Controlar transações no banco de dados*/
@EnableWebMvc/*Habilitar a parte do mvc*/
@RestController
@EnableAutoConfiguration /*Para ter todas as anotações ativadas*/
public class SpringrestapiApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestapiApplication.class, args);
	    System.out.println(new BCryptPasswordEncoder().encode("123"));
	
	}

	@CrossOrigin
	/*Mapeamento global que refletem em todo o sistema*/
	@Override
		public void addCorsMappings(CorsRegistry registry) {
			
		registry.addMapping("/usuario/**") 
		.allowedMethods("*")
		.allowedOrigins("*");
		/*Liberando o mapeamento de usuario para todos as origens*/
		}
}
