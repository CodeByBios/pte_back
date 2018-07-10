package com.sodifrance.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Question {
	
	@Id
	@GeneratedValue
	Long IdQuestion;
	
	@NotNull
	@Size(min = 2, max = 755)
	String Libelle;
	
	@NotNull
	Boolean Etat;
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Reponse> reponses = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Langage> langages = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Niveau> niveaux = new HashSet<>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "typequestion_id")
	TypeQuestion typeQuestion;
	
	@ManyToMany
    private Set<Candidat> Candidats = new HashSet<>();

	public Question(@NotNull @Size(min = 2, max = 255) String libelle, @NotNull Boolean etat, Set<Reponse> reponses,
			Set<Langage> langages, Set<Niveau> niveaux, TypeQuestion typeQuestion) {
		super();
		Libelle = libelle;
		Etat = etat;
		this.reponses = reponses;
		this.langages = langages;
		this.niveaux = niveaux;
		this.typeQuestion = typeQuestion;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
