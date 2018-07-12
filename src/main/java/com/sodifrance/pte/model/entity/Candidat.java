package com.sodifrance.pte.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Candidat implements Serializable {
	
	private static final long serialVersionUID = -758247213719000953L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotNull
	Long temps;
	
	@NotNull
	@Size(min = 2, max = 255)
	String nom;
	
	@NotNull
	@Size(min = 2, max = 255)
	String prenom;
	
	@NotNull
	@Size(min = 2, max = 255)
	String note;
	
	@NotNull
	Timestamp date;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "utilisateur_id")
	Utilisateur utlisateur;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Candidat_Question", 
        joinColumns = { @JoinColumn(name = "candidat_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "question_id") }
    )
    Set<Question> questions = new HashSet<>();

	
	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidat(@NotNull Long temps, @NotNull @Size(min = 2, max = 255) String nom,
			@NotNull @Size(min = 2, max = 255) String prenom, @NotNull @Size(min = 2, max = 255) String note,
			@NotNull Timestamp date, Utilisateur utlisateur, Set<Question> questions) {
		super();
		this.temps = temps;
		this.nom = nom;
		this.prenom = prenom;
		this.note = note;
		this.date = date;
		this.utlisateur = utlisateur;
		this.questions = questions;
	}
}
