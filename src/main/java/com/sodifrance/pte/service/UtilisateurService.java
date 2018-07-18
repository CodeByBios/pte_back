package com.sodifrance.pte.service;

import java.util.List;
import java.util.Optional;

import com.sodifrance.pte.model.entity.Utilisateur;

public interface UtilisateurService {

    public List<Utilisateur> getAllUtilisateurs(); 
	
	public Utilisateur newUtilisateur(Utilisateur utilisateur);
	
	public String getConnection(String login, String password);

	Optional<Utilisateur> finfUtilisateurById(Long pId);
}
