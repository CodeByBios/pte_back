package com.sodifrance.pte.model.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class CreateTestDto implements Serializable{

	private static final long serialVersionUID = -4622531654360297996L;
	
	private Set<NiveauDto> niveaux;
	
	private Set<TypeQuestionDto> typesQquestions;
	
	private Set<LangageDto> langage;
	
}
