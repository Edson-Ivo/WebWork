package com.example.demo.classes;

import javax.persistence.Entity;


public class Item {

	private Pratos pratos;
	private int quantidade;
	
	
	public Pratos getPratos() {
		return pratos;
	}
	public void setPratos(Pratos pratos) {
		this.pratos = pratos;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Item() {
	}

	
	
}
