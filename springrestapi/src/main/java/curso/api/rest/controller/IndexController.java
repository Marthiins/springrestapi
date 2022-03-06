package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController /* Obrigatorio Anotação para aceitar os metodos rest */
@RequestMapping(value = "/usuario")
public class IndexController {

	/*
	 * para bater no metodo vamos precisar da um /usuario e / * no localhost:8080
	 */

	/* Serviço Restufull */
	@GetMapping(value = "/", produces = "application/json") /* Para acessar pela URL é um get */
	public ResponseEntity init(
			@RequestParam(value = "nome", required = true, defaultValue = "Nome não informado") String nome, @RequestParam(value = "salario") Long salario) {
		/*
		 * Sempre utilizar ResponseEntity nos metodos 
		 * * Passando varios parametros com nome e valor
		 */

		System.out.println("Parametro sendo recebido" + nome);

		return new ResponseEntity("Ola Usuario REST spring boot seu nome é : " + nome + " salario é "+ salario,
				HttpStatus.OK);
	}
			
}