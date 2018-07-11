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
public class Niveau {

	@Id
	@GeneratedValue
	Long IdNiveau;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Libelle;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	Question question;

	public Niveau(@NotNull @Size(min = 2, max = 255) String libelle) {
		super();
		Libelle = libelle;
	}

	public Niveau() {
		super();
		// TODO Auto-generated constructor stub
	}
}
