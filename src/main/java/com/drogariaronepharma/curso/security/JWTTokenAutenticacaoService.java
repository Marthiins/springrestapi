package com.drogariaronepharma.curso.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.drogariaronepharma.curso.ApplicationContextLoad;
import com.drogariaronepharma.curso.model.Usuario;
import com.drogariaronepharma.curso.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component // Para fazer a injeção de dependencias
public class JWTTokenAutenticacaoService {

	/* Tempo de validade do token 2 dias em milisegundos */
	private static final long EXPIRATION_TIME = 172800000; /* Constante não vai poder alterar o valor */

	/* Uma senha unica para compor a autenticação e ajudar na segurança */
	private static final String SECRET = "SenhaExtremamenteSecreta";

	/* Prefixo padrão de Token */
	private static final String TOKEN_PREFIX = "Bearer ";

	private static final String HEADER_STRING = "Authorization";

	/* Gerando token de autenticação e adicionando ao cabeçalho resposta Http */
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {

		/* Montagem do Token */
		String JWT = Jwts.builder() /* Chama o gerador de Token */
				.setSubject(username) /* Adiciona o Usuario */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))// Data atual + tempo de expiração
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();/* Compactação e algoritmo de geração de senha */

		/* Juntar Token com o TOKEN_PREFIX */
		String token = TOKEN_PREFIX + "" + JWT; /* Bearer 878778BweBwe787Bwe787Bwe78BweBwe787Bwe787Bwe */

		/* Adiciona no cabeçalho Http */
		response.addHeader(HEADER_STRING, token);/* Autorization: Bearer 878778BweBwe787Bwe787Bwe78BweBwe787Bwe787Bwe */

		/* Escreve token como resposta no corpo http */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	/*
	 * Metodo para retornar o Usuario validado com o token ou caso não seja valido
	 * retorna null
	 */
	public Authentication getAuthentication(HttpServletRequest request) {

		/* Pega o token enviado no cabeçalho http */
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			/* Faz a validação do token do usuario na requisição */
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getSubject();
			if (user != null) {

				Usuario usuario = ApplicationContextLoad.getApplicationContext()
						.getBean(UsuarioRepository.class).findUserByLogin(user);

				if (usuario != null) {

					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(), 
							usuario.getSenha(), 
							usuario.getAuthorities());
				}
			}
		}

		return null; /* Não autorizado */

	}

}
