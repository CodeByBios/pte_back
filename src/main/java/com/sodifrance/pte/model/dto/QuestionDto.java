package com.sodifrance.pte.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class QuestionDto implements Serializable {
	
	private static final long serialVersionUID = 4842302244654606027L;
	
	private Long id;
	
	private String libelle;
	
	private String code;
	
	private Boolean etat = Boolean.TRUE;
	
	private List<LangageDto> langageDto;
	
	private List<NiveauDto> niveauDto;
	
	private TypeQuestionDto typeQuestionDto;
	
	private List<ReponseDto> ReponseDto;
	
}
