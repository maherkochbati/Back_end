package com.app.Controller.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Commande implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private Date dateLivraison ;
	private int quantite;
	
	@ManyToOne
	private Pharmacie pharmacie;
	
	@OneToMany(mappedBy = "commande")
	private List<ProduitCommande> produitCommandes;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Commande(int id, Date dateLivraison, int quantite) {
		super();
		this.id = id;
		this.dateLivraison = dateLivraison;
		this.quantite = quantite;
	}
	
	
	
}
