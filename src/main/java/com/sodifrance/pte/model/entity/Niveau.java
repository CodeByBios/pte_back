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
public class Niveau implements Serializable {

	private static final long serialVersionUID = -7388563887840282558L;

	@Id
	@GeneratedValue
	Long idNiveau;
	
	@NotNull
	@Size(min = 2, max = 255)
	String libelle;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	Question question;

	public Niveau() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Niveau(Long idNiveau, @NotNull @Size(min = 2, max = 255) String libelle, Question question) {
		super();
		this.idNiveau = idNiveau;
		this.libelle = libelle;
		this.question = question;
	}
}
