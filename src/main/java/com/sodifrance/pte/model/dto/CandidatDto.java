package com.sodifrance.pte.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Data 
public class CandidatDto {
	
	private Long id;
	
	private Long temps;
	
	private String nom;
	
	private String prenom;
	
	private String note;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime date;
	
	private List<QuestionDto> questionDto;
	
	private UtilisateurDto utilisateurDto;

}
