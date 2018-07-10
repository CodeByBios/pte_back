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
public class Langage {

	@Id
	@GeneratedValue
	Long IdLangage;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Libelle;
	
	public Langage(@NotNull @Size(min = 2, max = 255) String libelle) {
		super();
		Libelle = libelle;
	}

	public Langage() {
		super();
		// TODO Auto-generated constructor stub
	}
}
