package com.example.demo.classes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Pedidos {

	private List<Pratos> pratos;

	public List<Pratos> findAll() {
		return this.pratos;
	}

	public Pratos find(Long id) {
		for (Pratos pratos : this.pratos) {
			if (pratos.getId()==id) {
				return pratos;
			}
		}
		return null;
	}
	
	
	
	
}
