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
public class Reponse {

	@Id
	@GeneratedValue
	Long IdReponse;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Libelle;
	
	@NotNull
	Boolean ReponseJuste;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	Question question;

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
