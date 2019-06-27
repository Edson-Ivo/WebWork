package com.example.demo.classes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import org.springframework.security.core.GrantedAuthority;


@Entity
public class Role implements GrantedAuthority{

	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long papelId;
	
	private String papel;
	
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;
	
	@Override
	public String getAuthority() {
		return this.papel;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
