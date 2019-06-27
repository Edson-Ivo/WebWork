package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementacao userDSI;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDSI).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		
		web.ignoring().antMatchers("/css/**","/images/**","/img/**","/js/**","/webfonts/**");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		

		.antMatchers("/inserirPratos").hasRole("ADMIN")
		.antMatchers("/editarPrato").hasRole("ADMIN")
		.antMatchers("/excluirPrato").hasRole("ADMIN")
		.antMatchers("/alterarPrato").hasRole("ADMIN")
		.antMatchers("/inicio").permitAll()
		.antMatchers("/CadUsuario").permitAll()
		.antMatchers("/cardapio").permitAll()
		.antMatchers("/UsuarioCadastrar").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/").permitAll()
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login").permitAll().defaultSuccessUrl("/inicio").permitAll()
		
		
		.and()
		.logout()
		.logoutSuccessUrl("/inicio")
		.permitAll();
		// TODO Auto-generated method stub
		
	}
	
	

}
