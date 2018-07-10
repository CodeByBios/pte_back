package com.sodifrance.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Utilisateur {

	@Id
	@GeneratedValue
	Long IdUtilisateur;
	
	String nom;
	
	String prenom;
	
	String login;
	
	String password;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_role")
	Role role;

	public Utilisateur(String nom, String prenom, String login, String password, Role role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
