package com.sodifrance.pte.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LangageDto implements Serializable {
	
	private static final long serialVersionUID = -4576065398560029513L;

	private Long id;
	
	private String libelle;

}
