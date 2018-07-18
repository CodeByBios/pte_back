package com.sodifrance.pte.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.model.dto.ConnectionDto;
import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.Role;
import com.sodifrance.pte.model.entity.Utilisateur;
import com.sodifrance.pte.service.UtilisateurService;


@RestController
//@RequestMapping("/utilisateurs") à remplacer par ceci @RequestMapping("/pte")
@RequestMapping("/utilisateurs")
@CrossOrigin
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;
	
	    //retourne tout les utilisateurs
		@GetMapping
		public List<Utilisateur> getAllUtilisateurs() {
			return utilisateurService.getAllUtilisateurs();
		}
		
		//TODO à mettre le Pahs ("/utilisateurs") ici @PostMapping(value = "/utilisateurs")
		//cree un utilisateur
		@PostMapping
		@ResponseBody
		public Utilisateur newUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
			Utilisateur utilisateur = new Utilisateur();
			
			//TODO penser à faire une Enum pour les Roles
		    Role role = new Role("manager");
			
			utilisateur.setNom(utilisateurDto.getNom());
			utilisateur.setPrenom(utilisateurDto.getPrenom());
			utilisateur.setLogin(utilisateurDto.getLogin());
			utilisateur.setPassword(utilisateurDto.getPassword());
			utilisateur.setRole(role);
			
			return utilisateurService.newUtilisateur(utilisateur);
		}
		
		//teste la connexion
		@PostMapping("/connexion")
		@ResponseBody
		public String getConnection(@RequestBody ConnectionDto connectionDto) {
			return utilisateurService.getConnection(connectionDto.getLogin(), connectionDto.getPassword());
		}
		
}
