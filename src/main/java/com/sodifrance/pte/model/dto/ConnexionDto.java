package com.sodifrance.pte.model.dto;

import com.sodifrance.pte.model.entity.Utilisateur;
import lombok.Data;

@Data 
public class ConnexionDto {

    private Utilisateur utilisateur;
	
	private String jeton;
}
