package com.app.Controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Controller.entities.Pharmacie;

public interface PharmacieRepository extends JpaRepository<Pharmacie, Integer> {
	
	Pharmacie findByNom(String nom);

}
