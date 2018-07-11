package com.sodifrance.pte.model.entity;

import java.io.Serializable;

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
public class TypeQuestion implements Serializable {

	private static final long serialVersionUID = 4560437636058348649L;

	@Id
	@GeneratedValue
	Long idTypeQuestion;
	
	@NotNull
	@Size(min = 2, max = 255)
	String libelle;


	public TypeQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeQuestion(Long idTypeQuestion, @NotNull @Size(min = 2, max = 255) String libelle) {
		super();
		this.idTypeQuestion = idTypeQuestion;
		this.libelle = libelle;
	}
}
