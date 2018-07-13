package com.sodifrance.pte.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TypeQuestionDto implements Serializable {

	private static final long serialVersionUID = 3493374105433107580L;
	
	private Long id;
	
	private String libelle;
}
