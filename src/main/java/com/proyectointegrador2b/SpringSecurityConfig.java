package com.proyectointegrador2b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurarGlobal(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder( encoder::encode);
		builder.inMemoryAuthentication().withUser(users.username("admin").password("12345").roles("Administrador"))
		.withUser(users.username("carlos").password("123").roles("Vendedor"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**","/js/**","/img/**","/").permitAll()
	.antMatchers("/usuarios","/formUsuario","/modulo-inventario/dardealta/producto","/modulo-inventario/editarproducto/**"
		,"/delitecliente/**","/modulo-inventario/eliminarproducto/**",
				"/modulo-cobranza/listadodecobranza","/modulo-cobranza/eliminarcobranza/**").hasAnyRole("Administrador")
		
		.antMatchers("/clientes","/nuevocliente","/perfilcliente/**","/modulo-ventas/**","/modulo-cobranza/crear/**",
				"/modulo-cobranza/ver/detalle/cobranza/**")
		.hasAnyRole("Vendedor","Administrador")
		
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");	
	}
	
	
}
