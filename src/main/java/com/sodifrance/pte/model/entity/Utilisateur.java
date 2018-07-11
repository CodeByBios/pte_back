package com.sodifrance.pte.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Utilisateur {

	@Id
	@GeneratedValue
	Long IdUtilisateur;
	
	@NotNull
	@Size(min = 2, max = 255)
	String nom;
	
	@NotNull
	@Size(min = 2, max = 255)
	String prenom;
	
	@NotNull
	@Size(min = 2, max = 255)
	String login;
	
	@NotNull
	@Size(min = 2, max = 255)
	String password;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id")
	Role role;

	public Utilisateur(@NotNull @Size(min = 2, max = 255) String nom, @NotNull @Size(min = 2, max = 255) String prenom,
			@NotNull @Size(min = 2, max = 255) String login, @NotNull @Size(min = 2, max = 255) String password,
			Role role) {
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
