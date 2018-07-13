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
public class Langage implements Serializable {

	private static final long serialVersionUID = -891431424813158297L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotNull
	@Size(min = 2, max = 255)
	String libelle;

	public Langage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Langage(@NotNull @Size(min = 2, max = 255) String libelle) {
		super();
		this.libelle = libelle;
	}
}
