package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.classes.Pratos;

@Repository
public interface PratosRepository extends JpaRepository<Pratos, Long>{

}
