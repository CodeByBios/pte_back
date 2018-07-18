package com.sodifrance.pte.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateTestDto {
	
	private List<QuestionDto> questionsDto;
	
	private CandidatDto candidatDto;

}
