package com.sodifrance.pte.model.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class QuestionDto implements Serializable {
	
	private static final long serialVersionUID = 4842302244654606027L;

	private Long id;
	
	private String libelle;
	
	private Boolean etat = Boolean.FALSE;
	
	private Set<LangageDto> langageDto;
	
	private Set<NiveauDto> niveauDto;
	
	private Set<TypeQuestionDto> typeQuestionDto;

}
