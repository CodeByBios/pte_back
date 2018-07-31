package com.sodifrance.pte.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
	
	String code;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Reponse> reponses = new ArrayList<Reponse>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	List<Langage> langages = new ArrayList<Langage>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	List<Niveau> niveaux = new ArrayList<Niveau>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "typequestion_id")
	TypeQuestion typeQuestion;

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Long id, @NotNull @Size(min = 2, max = 755) String libelle, @NotNull Boolean etat,
			String code, List<Reponse> reponses, List<Langage> langages, List<Niveau> niveaux, TypeQuestion typeQuestion) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.etat = etat;
		this.code = code;
		this.reponses = reponses;
		this.langages = langages;
		this.niveaux = niveaux;
		this.typeQuestion = typeQuestion;
	}
	
	public String getNiveau() {
		//Set<Niveau> lListNiveaux = new HashSet<Niveau>(getNiveaux());
		List<Niveau> lNiveaux = getNiveaux(); //new ArrayList<>(lListNiveaux);
		String lLibelleNiveau = "";
		Niveau lNiveau = new Niveau();
		
		lNiveaux.forEach(niveau -> {
			lNiveau.setLibelle(niveau.getLibelle());
		});
		return lLibelleNiveau = lNiveau.getLibelle();
	}
	
	
	
}
