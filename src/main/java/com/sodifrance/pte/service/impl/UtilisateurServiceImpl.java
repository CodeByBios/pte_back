package com.sodifrance.pte.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodifrance.pte.dao.UtilisateurDao;
import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.Role;
import com.sodifrance.pte.model.entity.Utilisateur;
import com.sodifrance.pte.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	UtilisateurDao utilisateurDao;
	
	@Override
	public List<Utilisateur> getAllUtilisateurs() {
	    List<Utilisateur> utilisateurs = utilisateurDao.findAll();
		return utilisateurs;
	}

	@Override
	public List<Utilisateur> getUtilisateurs(Long[] id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Utilisateur> getUtilisateur(Long id) {
		Optional<Utilisateur> utilisateur = utilisateurDao.findById(id);
		return utilisateur;
	}

	@Override
	public Utilisateur newUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.save(utilisateur);
	}

	@Override
	public Utilisateur deleteUtilisateur(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findUtilisateurs(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConnection(String login, String password) {
		String rep = "refuse";
		Utilisateur utilisateur = utilisateurDao.findByLogin(login);
		if (utilisateur != null ) {
			if(password.equals(utilisateur.getPassword())) {
				rep = utilisateur.getRole().getIdentite();
			}
		}
		return rep;
	}
	
	@Override
	public Utilisateur transformDtoToEntity(UtilisateurDto utilisateurDto){
		Utilisateur utilisateur = new Utilisateur();
		Role role = new Role("rh"); //utilisateurDto.getRoleId()
		role.setId(utilisateurDto.getRoleId());

		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setLogin(utilisateurDto.getLogin());
		utilisateur.setPassword(utilisateurDto.getPassword());
		utilisateur.setRole(role);
		
		return utilisateur;
	}

	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		Utilisateur utilisateur = utilisateurDao.findByLogin(login);
		return utilisateur;
	}

}
