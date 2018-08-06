package com.sodifrance.pte.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionReponduDto {

	Long id;
	
	Long idQuestion;
	
	Long idCandidat;
	
	private List<ReponseCandidatDto> ReponseCandidatDto;

	public QuestionReponduDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionReponduDto(Long id, Long idQuestion, Long idCandidat,
			List<com.sodifrance.pte.model.dto.ReponseCandidatDto> reponseCandidatDto) {
		super();
		this.id = id;
		this.idQuestion = idQuestion;
		this.idCandidat = idCandidat;
		ReponseCandidatDto = reponseCandidatDto;
	}


}
