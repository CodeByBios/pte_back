package com.sodifrance.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Reponse {

	@Id
	@GeneratedValue
	Long IdReponse;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Libelle;
	
	@NotNull
	Boolean ReponseJuste;

	public Reponse(@NotNull String libelle, @NotNull Boolean reponseJuste) {
		super();
		Libelle = libelle;
		ReponseJuste = reponseJuste;
	}

	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
