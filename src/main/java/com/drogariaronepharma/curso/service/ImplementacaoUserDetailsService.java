package com.drogariaronepharma.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.drogariaronepharma.curso.model.Usuario;
import com.drogariaronepharma.curso.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService { /* Padrão buscar banco de dados */

	@Autowired
	UsuarioRepository usuarioRepository;/* Pegar o UsuarioRepository */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* 1º Passo consultar no Banco o Usuario */
		Usuario usuario = usuarioRepository.findUserByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não foi encontrado");
		}
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}

}
