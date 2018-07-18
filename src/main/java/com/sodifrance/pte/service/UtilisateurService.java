package com.sodifrance.pte.service;

import java.util.List;
import java.util.Optional;

import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.Utilisateur;

public interface UtilisateurService {

    public List<Utilisateur> getAllUtilisateurs(); 
	
	public List<Utilisateur> getUtilisateurs(Long[] id);

	public Optional<Utilisateur> getUtilisateur(Long id); 
	
	public Utilisateur getUtilisateurByLogin(String login);
	
	public Utilisateur newUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur deleteUtilisateur(Long id);
	
	public List<Utilisateur> findUtilisateurs(String nom);
	
	public String getConnection(String login, String password);
	
	public Utilisateur transformDtoToEntity(UtilisateurDto utilisateurDto);
}
