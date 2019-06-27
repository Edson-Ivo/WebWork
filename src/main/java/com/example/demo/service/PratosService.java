package com.example.demo.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.classes.Pratos;
import com.example.demo.repository.PratosRepository;
import com.example.demo.util.Util;

@Service
public class PratosService {
	
	@Autowired
	private PratosRepository pratosRepository;
	
	public void cadastrarPratos(Pratos pratos,MultipartFile img) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String date = new SimpleDateFormat("dd/MM/yyyy").format(timestamp.getTime());
		
		String nomeimg=pratos.getId()+pratos.getNome()+date;
		MessageDigest m;
		
		
		try {
			
			m = MessageDigest.getInstance("MD5");
			m.update(nomeimg.getBytes(), 0, pratos.getNome().length());
			BigInteger nomeimg1 = new BigInteger(1, m.digest());
			nomeimg = String.format("%1$032X", nomeimg1);
					
			String href="img/"+nomeimg+".png";	
			Util.salvarimg(href,img);
			
			pratos.setImg(nomeimg);
			
			pratosRepository.save(pratos);
		
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
	
		
	}
	
	public List<Pratos> ListarPratos(){
		
		return pratosRepository.findAll();		
	}
	

	public void excluirPrato(Long id) {
		// TODO Auto-generated method stub
		pratosRepository.deleteById(id);
		
	}

	public Pratos buscarPratobyId(Long id) {
		return pratosRepository.getOne(id);
	}

}
