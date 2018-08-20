package com.app.Controller.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="`users`")
public class Users implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
	private int id;
	private String nom;
	private String prenom;
	private String username;
	private String mdp;
	private String email;
	private String resetToken;
	

	
	

	@Column(name = "etat")
    private Boolean etat=true;
	
	
	
	
	public Users(int id, String nom, String prenom, String username, String mdp, String email, String resetToken,
			Boolean etat, Role role, Pharmacie pharmacie) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.mdp = mdp;
		this.email = email;
		this.resetToken = resetToken;
		this.etat = etat;
		this.role = role;
		this.pharmacie = pharmacie;
	}
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Pharmacie getPharmacie() {
		return pharmacie;
	}
	public void setPharmacie(Pharmacie pharmacie) {
		this.pharmacie = pharmacie;
	}

	
	@ManyToOne
	private Role role;
	
	@OneToMany(mappedBy="userAdmin")
	private List<Pharmacie> pharmacies_create ;
	
	
	@ManyToOne
	private Pharmacie pharmacie ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

   
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getResetToken() {
		return resetToken;
	}


	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	
	
	


	
	
}
