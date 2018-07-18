package com.sodifrance.pte.transform;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.CandidatDto;
import com.sodifrance.pte.model.dto.UtilisateurDto;
import com.sodifrance.pte.model.entity.Utilisateur;

/**
 * Classe de conversion des beans Utilisateur et UtilisateurDto.
 */
@Component
public class UtilisateurTransform {
	
	 @PostConstruct
	    public void init() {
	    	
	    	ModelMapper modelMapper = new ModelMapper();
	    	
	        // Pour la transformation Utilisateur -> UtilisateurDto
	        modelMapper.addMappings(new PropertyMap<Utilisateur, UtilisateurDto>() {
	            @Override
	            protected void configure() {
	            }
	        });
	    }
	    
	    
		/**
		 * Converti un dto en entité
		 *
		 * @param pUtilisateurtDto
		 *            objet à convertir en entité
		 * @return un Utilisateur
		 */
		public Utilisateur convertToEntity(UtilisateurDto pUtilisateurDto) {
			ModelMapper modelMapper = new ModelMapper();
			Utilisateur lUtilisateur = modelMapper.map(pUtilisateurDto, Utilisateur.class);
			
			//TODO Role à gérer
			
			return lUtilisateur;
		}
		
		/**
		 * Converti une entité en dto
		 *
		 * @param user
		 *            l'entité à convertir
		 * @return le dto correspondant
		 */
		public UtilisateurDto convertToDto(Utilisateur pUtilisateur) {
			ModelMapper modelMapper = new ModelMapper();
			UtilisateurDto lUtilisateurDto = modelMapper.map(pUtilisateur, UtilisateurDto.class);
			
			//TODO Role à gérer
			
			return lUtilisateurDto;
		}

}
