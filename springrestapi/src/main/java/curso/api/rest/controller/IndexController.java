package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;

@RestController /* Obrigatorio Anotação para aceitar os metodos rest */
@RequestMapping(value = "/usuario")
public class IndexController {

	/*
	 * para bater no metodo vamos precisar da um /usuario e / * no localhost:8080
	 */

	/* Serviço Restufull */
	@GetMapping(value = "/", produces = "application/json") /* Para acessar pela URL é um get */
	public ResponseEntity<Usuario> init(){
		Usuario usuario = new Usuario();
		usuario.setId(50L); //Id é Long
		usuario.setLogin("seergio@gmail.com");
		usuario.setNome("Sérgio");
		usuario.setSenha("1234");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId(10L); //Id é Long
		usuario2.setLogin("seo@gmail.com");
		usuario2.setNome("Kamylla");
		usuario2.setSenha("1234");
		
		/*Como tem mais de 2 usuariocria a lista*/
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);//adicionar os usuarios na lista
		usuarios.add(usuario2);
		
		return new ResponseEntity(usuarios, HttpStatus.OK );
				
	}
			
}