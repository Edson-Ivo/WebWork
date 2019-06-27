package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.classes.Role;
import com.example.demo.classes.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void cadastrarUsuario(Usuario usuario) {
	
		Role roleAdm=new Role();
		Role roleUsu=new Role();
				
		List<Usuario> u=new ArrayList<Usuario>();
		u.add(usuario);
		List<Role> roles=new ArrayList<Role>();
		
		roleAdm.setPapel("ROLE_ADMIN");
		roleAdm.setUsuarios(u);
		
		roleUsu.setPapel("ROLE_USER");
		roleUsu.setUsuarios(u);		
		
		if(contarUsuarios()==0) {
			
			roles.add(roleAdm);
			usuario.setRoles(roles);
			
		}else {
			
			roles.add(roleUsu);
			usuario.setRoles(roles);
			
		}
		
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		usuarioRepository.save(usuario);
		
	}
	
	public int existeCpf(String cpf) {
		
		Usuario usuario=usuarioRepository.findByCpf(cpf);		
		if(usuario!=null) {
			return 1;
		}
		return 0;
	}
	
	public int existeEmail(String email) {
		
		Usuario usuario=usuarioRepository.findByEmail(email);	
		if(usuario!=null) {
			return 1;
		}
		return 0;
	}
	
	public List<Usuario> listarUsuario(){
		return usuarioRepository.findAll();		
	}
	
	public Long contarUsuarios() {
		
		return usuarioRepository.count();
		
	}
	

	public void excluirUsuario(Long id) {
		
		usuarioRepository.deleteById(id);
		
	}

} 
