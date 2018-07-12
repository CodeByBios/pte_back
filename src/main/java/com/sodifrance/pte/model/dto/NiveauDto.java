package com.sodifrance.pte.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class NiveauDto implements Serializable {

	private static final long serialVersionUID = -8927821806754615914L;
	
	private Long id;
	
	private String libelle;

}
