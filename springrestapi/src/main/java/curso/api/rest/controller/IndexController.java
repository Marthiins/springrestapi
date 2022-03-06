package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;


@RestController /* Obrigatorio Anotação para aceitar os metodos Arquitetura Rest */
@RequestMapping(value = "/usuario")
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * para bater no metodo vamos precisar da um /usuario e / * no localhost:8080
	 */

	/* Serviço Restufull */
	@GetMapping(value = "/{id}", produces = "application/json") /* Para acessar pela URL é um get */
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id){
		
		Optional<Usuario> usuario =  usuarioRepository.findById(id);
		
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK );
				
	}
	
	/*Metodo consultar todos*/
	@GetMapping(value = "/" , produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario(){
		
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}
	
			
}