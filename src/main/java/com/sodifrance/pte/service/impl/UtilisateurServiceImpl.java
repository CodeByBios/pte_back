package com.sodifrance.pte.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodifrance.pte.dao.UtilisateurDao;
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
	public Utilisateur getUtilisateur(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur newUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
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

}
