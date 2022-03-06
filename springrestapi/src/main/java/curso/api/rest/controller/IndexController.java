package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	/* Serviço Restufull serviço de relatorio */
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/pdf") /* Para acessar pela URL é um get */
	public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id") Long id ,
			                                 @PathVariable(value = "venda" ) Long venda){
		
		/*Como iria chamar esse relatório*/
		Optional<Usuario> usuario =  usuarioRepository.findById(id);
		
		/*Suponha que o retorno seria um relatório*/
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK );
				
	}
	
	
	/*Metodo consultar todos*/
	@GetMapping(value = "/" , produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario(){
		
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}
	
	/*Serviço post metodo cadastrar*/
	@PostMapping(value = "/", produces = "application/json") /*Mapear para a barra normal*/
   public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		
		/*captando objeto pelo post Instancia um objeto Usuario usuario com os mesmo atributo 
		 * e utiliza a anotação @requestBody*/
	
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
	
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
		
	/*Serviço post metodo cadastrar com @PathVariable só simulação */
	@PostMapping(value = "/{iduser}/idvenda{idvenda}", produces = "application/json") /*Mapear para a barra normal*/
   public ResponseEntity cadastrarVenda(@PathVariable Long iduser ,
		                                @PathVariable Long idvenda){
		
		/*Aqui seria o processo da venda*/
	    //Usuario usuarioSalvo = usuarioRepository.save(usuario);
	
		return new ResponseEntity("id user :" + iduser + "idvenda" + idvenda, HttpStatus.OK);
	}
	
	/*Atualizar Usuario PutMapping*/
	
	@PutMapping(value = "/", produces = "application/json") /*Mapear para a barra normal*/
	   public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
			
			Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
			return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		}
	
	/*Serviço post metodo cadastrar com @PathVariable só simulação */
	@PutMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json") /*Mapear para iduser / idvenda*/
   public ResponseEntity updateVenda(@PathVariable Long iduser ,
		                                @PathVariable Long idvenda){
		
		/*Outras rotina de atualizar*/
	    //Usuario usuarioSalvo = usuarioRepository.save(usuario);
	
		return new ResponseEntity("venda atualizada" , HttpStatus.OK);
	}
	
	}
