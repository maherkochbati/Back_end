package com.app.Controller.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Controller.entities.Pharmacie;
import com.app.Controller.repository.PharmacieRepository;

@RestController
@RequestMapping("/pharmacie")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class PharmacieController {
	@Autowired
	PharmacieRepository pharmacieRepository;
	
	/*ajouter pharmacie*/
	@PostMapping(value="/create", consumes = "application/json", produces = "application/json")
	public Pharmacie createPharmacie(@RequestBody Pharmacie pharmacie) {
		return pharmacieRepository.save(pharmacie);
		
	}
	
	
	
	
	/*retourner la liste des pharmacies*/
	@GetMapping(value="/listPharmacie")
	public List<Pharmacie> getAllPharmacies() {
		return pharmacieRepository.findAll();
		
	}
	
	/*retourner la liste des pharmacies*/
	@GetMapping(value="/listPharmacie/inscription")
	public List<Pharmacie> getPharmacies() {
		return pharmacieRepository.findAll();
		
	}
	
	
	
	
	/*modifier pharmacie*/
	@PutMapping("/update/{id}")
	public Pharmacie updatePharmacie(@PathVariable(value="id") int pharmacieId ,@RequestBody Pharmacie pharmacie){
		Optional<Pharmacie> f=pharmacieRepository.findById(pharmacieId);
		Pharmacie ff= f.get();
		
		ff.setAdresse(pharmacie.getAdresse());
		ff.setMail(pharmacie.getMail());
		ff.setNom(pharmacie.getNom());
		ff.setTel(pharmacie.getTel());
		return pharmacieRepository.save(ff);
	}
	
	
	
	
	/*rechercher pharmacie byID*/
	@GetMapping("/find/{id}")
	public Optional<Pharmacie> getPharmacieById(@PathVariable(value="id") int pharmacieId) {
		return pharmacieRepository.findById(pharmacieId);
	}
	
	
	
	
	
	/*supprimer pharmacie*/
	@DeleteMapping("/delete/{id}")
	public void deletePharmacie(@PathVariable(value="id") int pharmacieId){
		pharmacieRepository.deleteById(pharmacieId);
		
	}
	
	

}
