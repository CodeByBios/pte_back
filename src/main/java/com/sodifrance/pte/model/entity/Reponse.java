package com.sodifrance.pte.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
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

	public Reponse(@NotNull @Size(min = 2, max = 255) String libelle, @NotNull Boolean reponseJuste,
			Question question) {
		super();
		this.libelle = libelle;
		this.reponseJuste = reponseJuste;
		this.question = question;
	}
}
