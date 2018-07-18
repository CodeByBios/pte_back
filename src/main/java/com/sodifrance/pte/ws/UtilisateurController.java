package com.sodifrance.pte.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.model.dto.ConnectionDto;
import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.Utilisateur;
import com.sodifrance.pte.service.UtilisateurService;

@RestController
@RequestMapping("pte/utilisateurs")
@CrossOrigin
public class UtilisateurController {

	@Autowired
	UtilisateurService UtilisateurServiceImpl;

	// retourne tout les utilisateurs
	@GetMapping
	public List<Utilisateur> getAllUtilisateurs() {
		return UtilisateurServiceImpl.getAllUtilisateurs();
	}

	// retourne un utilisateur
	@GetMapping("/{login}")
	public Utilisateur getUtilisateur(@PathVariable String login) {
		return UtilisateurServiceImpl.getUtilisateurByLogin(login);
	}

	// cree un utilisateur
	@PostMapping
	@ResponseBody
	public Utilisateur newUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
		Utilisateur utilisateur = UtilisateurServiceImpl.transformDtoToEntity(utilisateurDto);
		return UtilisateurServiceImpl.newUtilisateur(utilisateur);
	}

	// teste la connexion
	@PostMapping("/connexion")
	@ResponseBody
	public String getConnection(@RequestBody ConnectionDto connectionDto) {
		return UtilisateurServiceImpl.getConnection(connectionDto.getLogin(), connectionDto.getPassword());
	}
	
}
