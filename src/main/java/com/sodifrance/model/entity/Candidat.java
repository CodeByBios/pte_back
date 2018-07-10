package com.sodifrance.model.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Setter
@Getter
public class Candidat {

	@Id
	@GeneratedValue
	Long IdCandidat;
	
	@NotNull
	Long Temps;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Nom;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Prenom;
	
	@NotNull
	@Size(min = 2, max = 255)
	String Note;
	
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

	public Candidat(@NotNull Long temps, @NotNull @Size(min = 2, max = 255) String nom,
			@NotNull @Size(min = 2, max = 255) String prenom, @NotNull Timestamp date, Utilisateur utlisateur) {
		super();
		Temps = temps;
		Nom = nom;
		Prenom = prenom;
		this.date = date;
		this.utlisateur = utlisateur;
	}

	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}
}
