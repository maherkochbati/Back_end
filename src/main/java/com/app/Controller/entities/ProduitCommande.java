package com.app.Controller.entities;

import java.io.Serializable;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ProduitCommande implements Serializable{

	
	private int quantiteCom;
	private double prix;
	
	
	@EmbeddedId
	private ProduitCommandeID produitCommandeID;
	
	@ManyToOne
	@JoinColumn(name = "commandeID",referencedColumnName="id", insertable = false, updatable = false)
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name = "produitID",referencedColumnName="code", insertable = false, updatable = false)
	private Produit produit;

	public ProduitCommandeID getProduitCommandeID() {
		return produitCommandeID;
	}

	public void setProduitCommandeID(ProduitCommandeID produitCommandeID) {
		this.produitCommandeID = produitCommandeID;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	
	
}
