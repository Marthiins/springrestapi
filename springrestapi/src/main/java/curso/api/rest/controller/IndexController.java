package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /* Obrigatorio Anotação para aceitar os metodos rest */
@RequestMapping(value = "/usuario")
public class IndexController {

	/*para bater no metodo vamos precisar
	 * da um /usuario e /
	 * * no localhost:8080*/
	
	
	@GetMapping(value = "/", produces = "application/json") /* Para acessar pela URL é um get */
	public ResponseEntity init() {/* Sempre utilizar o response entity nos metodos */
		return new ResponseEntity("Ola Usuario REST spring boot", HttpStatus.OK);
	}
}