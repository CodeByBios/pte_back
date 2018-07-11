package com.sodifrance.pte.model.entity;

import java.io.Serializable;

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
public class Reponse implements Serializable {

	private static final long serialVersionUID = 1791048553759210923L;

	@Id
	@GeneratedValue
	Long ddReponse;
	
	@NotNull
	@Size(min = 2, max = 255)
	String libelle;
	
	@NotNull
	Boolean reponseJuste;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	Question question;

	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reponse(Long ddReponse, @NotNull @Size(min = 2, max = 255) String libelle, @NotNull Boolean reponseJuste,
			Question question) {
		super();
		this.ddReponse = ddReponse;
		this.libelle = libelle;
		this.reponseJuste = reponseJuste;
		this.question = question;
	}
}
