package curso.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import curso.api.rest.service.ImplementacaoUserDetailsService;

/*Mapeia URL, autoriza ou bloqueia acesso a URL*/
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;/* Chamar a classe */

	/* Configura as solicitações de acesso por http */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* Ativando a proteção contra usua´rio que não estão validados por token */
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

				/*Ativando a permissão para acesso a página inicial do sistema Ex:sistema.com.br/index*/
				.disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/index").permitAll()

				/* AutenticaçãodeURL de logout - Redireciona após o user deslogar do sistema */
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

				/* Mapeia URL de Logout e insavida o usuario */
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               
		/*Filtra requisições de login para requisição*/
	     .and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), 
	    		 UsernamePasswordAuthenticationFilter.class)
	    
		/*Filtra demais requisições para verificar a presença do TOKEN JWT no HEADER HTTP */
	     .addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/* Service que irá consultar o ususario no banco de dados */
		auth.userDetailsService(implementacaoUserDetailsService)

				/* Padrão de codificação de senha do usuario */
				.passwordEncoder(new BCryptPasswordEncoder());

	}
}
