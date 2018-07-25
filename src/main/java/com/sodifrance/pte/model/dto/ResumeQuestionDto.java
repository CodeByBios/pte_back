package com.sodifrance.pte.model.dto;

import lombok.Data;

@Data
public class ResumeQuestionDto {
	
	private int nbQuestionValidee;
	private int nbQuestionNonValidee;
	private int nbQuestionTechinque;
	private int nbQuestionLogique;
	private int nbQuestionAnglais;
	private int nbQuestionFancais;

}
