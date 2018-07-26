package com.sodifrance.pte.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.sodifrance.pte.dao.UtilisateurDao;
import com.sodifrance.pte.model.dto.ConnexionDto;
//import com.sodifrance.pte.model.entity.Role;
import com.sodifrance.pte.model.entity.Utilisateur;
import com.sodifrance.pte.security.JwtTokenProvider;
import com.sodifrance.pte.service.UtilisateurService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

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
	public ConnexionDto getConnection(String login, String password) {

		/*
		 * Utilisateur rep = null; 
		 * Utilisateur utilisateur =
		 * utilisateurDao.findByLogin(login); if (utilisateur != null ) {
		 * if(password.equals(utilisateur.getPassword())) { rep = utilisateur; } }
		 * return rep;
		 */
		
		ConnexionDto rep = new ConnexionDto();
		Set<String> listRole = new HashSet<String>();
		Utilisateur user = utilisateurDao.findByLogin(login);
		
		if(user != null) {
			listRole.add(user.getRole().getAuthority());
		}

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
			String token = jwtTokenProvider.createToken(login, listRole);
			rep.setJeton(token);
			rep.setUtilisateur(user);
		} catch (AuthenticationException e) {
			log.error(e.getMessage());
		}
		
		return rep;
	}

	
	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		Utilisateur utilisateur = utilisateurDao.findByLogin(login);
		return utilisateur;
	}

	@Override
	public Optional<Utilisateur> finfUtilisateurById(Long pId) {
		return utilisateurDao.findById(pId);
	}

}
