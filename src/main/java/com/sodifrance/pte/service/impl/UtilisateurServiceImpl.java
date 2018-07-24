package com.sodifrance.pte.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodifrance.pte.dao.UtilisateurDao;
import com.sodifrance.pte.model.entity.Utilisateur;
import com.sodifrance.pte.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
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
	public Utilisateur getConnection(String login, String password) {
		Utilisateur rep = null;
		
		Utilisateur utilisateur = utilisateurDao.findByLogin(login);
		if (utilisateur != null ) {
			if(password.equals(utilisateur.getPassword())) {
				rep = utilisateur;
			}
		}
		
		return rep;
	}

	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		Utilisateur utilisateur = utilisateurDao.findByLogin(login);
		return utilisateur;
		}

	@Override
	public Optional<Utilisateur> finfUtilisateurById(Long pId){
		return utilisateurDao.findById(pId);
	}

}
