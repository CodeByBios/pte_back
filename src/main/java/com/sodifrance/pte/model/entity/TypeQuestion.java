package com.sodifrance.pte.model.entity;

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
public class TypeQuestion {

	@Id
	@GeneratedValue
	Long IdTypeQ;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Libelle;

	public TypeQuestion(@NotNull @Size(min = 2, max = 255) String libelle) {
		super();
		Libelle = libelle;
	}

	public TypeQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}
}
