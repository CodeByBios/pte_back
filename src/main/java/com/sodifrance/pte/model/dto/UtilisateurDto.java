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
	
	private RoleDto roleDto;

	public UtilisateurDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtilisateurDto(Long id, String nom, String prenom, String login, String password, RoleDto roleDto) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.roleDto = roleDto;
	}
}
