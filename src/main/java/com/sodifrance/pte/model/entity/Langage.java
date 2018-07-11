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
public class Langage implements Serializable {

	private static final long serialVersionUID = -891431424813158297L;

	@Id
	@GeneratedValue
	Long idLangage;
	
	@NotNull
	@Size(min = 2, max = 255)
	String libelle;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	Question question;
	

	public Langage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Langage(Long idLangage, @NotNull @Size(min = 2, max = 255) String libelle, Question question) {
		super();
		this.idLangage = idLangage;
		this.libelle = libelle;
		this.question = question;
	}
}
