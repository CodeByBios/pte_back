package com.sodifrance.pte.model.dto;

import java.util.Set;

import lombok.Data;

@Data
public class CreateTestDto {
	
	private Set<QuestionDto> questions;
	
	private CandidatDto candidat;

}
