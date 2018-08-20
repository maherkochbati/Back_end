package com.app.Controller.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.Controller.entities.Produit;
import com.app.Controller.repository.ProduitRepository;



@RestController
@RequestMapping("/produit")
public class ProduitController {
	@Autowired
	ProduitRepository produitRespository;
	
	
	
	/*ajouter produit*/
	@PostMapping(value="/create", consumes = "application/json", produces = "application/json")
	public Produit createProduit(@RequestBody Produit produit) {
		return produitRespository.save(produit);
		
	}
	
	
	
	
	/*retourner la liste des produits*/
	@GetMapping(value="/listProduit", consumes = "application/json", produces = "application/json")
	public List<Produit> getAllProduits() {
		return produitRespository.findAll();
		
	}
	
	
	
	
	/*modifier produit*/
	@PutMapping("/update/{id}")
	public Produit updateProduit(@PathVariable(value="id") int produitId ,@RequestBody Produit produit){
		Optional<Produit> p=produitRespository.findById(produitId);
		Produit pp=p.get();
		pp.setNom(produit.getNom());
		pp.setGenerique(produit.getGenerique());
		pp.setPrixA(produit.getPrixA());
		pp.setQuantite(produit.getQuantite());
		return produitRespository.save(pp);
	}
	
	
	
	
	/*rechercher produit byID*/
	@GetMapping("/find/{id}")
	public Optional<Produit> getProduitById(@PathVariable(value="id") int produitId) {
		return produitRespository.findById(produitId);
	}
	
	
	
	
	
	/*supprimer produit*/
	@DeleteMapping("/delete/{id}")
	public void deleteProduit(@PathVariable(value="id") int produitId){
		produitRespository.deleteById(produitId);
		
	}
	
	
	
	
	
	
}
