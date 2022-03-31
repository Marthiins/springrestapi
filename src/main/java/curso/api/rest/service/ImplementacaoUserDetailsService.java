package curso.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService { /* Padrão buscar banco de dados */

	@Autowired
	UsuarioRepository usuarioRepository;/* Pegar o UsuarioRepository */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* 1º Passo consultar no Banco o Usuario */
Usuario usuario = usuarioRepository.findByLogin(username);
		
		return null;
	}

}
