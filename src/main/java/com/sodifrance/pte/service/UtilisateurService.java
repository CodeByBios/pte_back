package com.sodifrance.pte.service;

import java.util.List;
import java.util.Optional;

import com.sodifrance.pte.model.dto.ConnexionDto;
import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.Utilisateur;

public interface UtilisateurService {

    public List<Utilisateur> getAllUtilisateurs(); 
	
	public List<Utilisateur> getUtilisateurs(Long[] id);

	public Optional<Utilisateur> getUtilisateur(Long id); 
	
	public Utilisateur getUtilisateurByLogin(String login);
	

	public Utilisateur newUtilisateur(Utilisateur utilisateur);
	
	public ConnexionDto getConnection(String login, String password);
	
	Optional<Utilisateur> finfUtilisateurById(Long pId);
}
