package com.app.Controller.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="`role`")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
	private int id;
	
	@Column(name = "role")
    
	private String libelle;
	
	@Column(name="etat")
    private Boolean etat=true;
	
	@OneToMany(mappedBy="role")
	private List<Users> users;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Role(int id,  String libelle) {
		super();
		this.id=id;
		this.libelle = libelle;
	}
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
