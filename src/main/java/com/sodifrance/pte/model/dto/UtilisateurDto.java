package com.sodifrance.pte.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDto {

	private Long id;
	
	String nom;
	
	String prenom;
	
	String login;
	
	String password;
	
	Long roleId;

	public UtilisateurDto(String nom, String prenom, String login, String password, Long roleId, Long id) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.roleId = roleId;
		this.id = id;
	}

	public UtilisateurDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
