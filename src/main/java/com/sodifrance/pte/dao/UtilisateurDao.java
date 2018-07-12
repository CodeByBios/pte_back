package com.sodifrance.pte.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.sodifrance.pte.model.entity.Utilisateur;


public interface UtilisateurDao extends CrudRepository<Utilisateur, Long> {

	public List<Utilisateur> findAll();

	public Optional<Utilisateur> findById(Long id);
	
	public Utilisateur findByNom(String nom);
	
	public Utilisateur findByLogin(String login);

}
