package com.sodifrance.pte.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Question implements Serializable {
	
	private static final long serialVersionUID = 5539470872792087648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotNull
	@Size(min = 2, max = 755)
	String libelle;
	
	@NotNull
	Boolean etat;
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Reponse> reponses = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	Set<Langage> langages = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	Set<Niveau> niveaux = new HashSet<>();
	
	/*@ManyToMany(fetch = FetchType.LAZY)
    List<Candidat> candidats = new ArrayList<Candidat>();*/
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "typequestion_id")
	TypeQuestion typeQuestion;

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Long id, @NotNull @Size(min = 2, max = 755) String libelle, @NotNull Boolean etat,
			Set<Reponse> reponses, Set<Langage> langages, Set<Niveau> niveaux, TypeQuestion typeQuestion) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.etat = etat;
		this.reponses = reponses;
		this.langages = langages;
		this.niveaux = niveaux;
		this.typeQuestion = typeQuestion;
	}
	
	
	
}
