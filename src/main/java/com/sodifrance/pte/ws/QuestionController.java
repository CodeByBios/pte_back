package com.sodifrance.pte.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.model.dto.QuestionDto;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.service.QuestionService;
import com.sodifrance.pte.transform.QuestionTransform;
import com.sodifrance.pte.validator.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pte")
public class QuestionController {
	
	private static final String PATH_CREER_QUESTION = "/v1/questions";
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionTransform questionTransform;
	
	/**
     * Execute les validateurs sur le dto.<br>
     * Converti le dto en Entity.
     *
     * @param pQuestionDto la question dto
     * @return la Question entity
     */
    Question validateAndTransform(QuestionDto pQuestionDto) {
        // Validation du DTO
        DtoValidator.validateDto(pQuestionDto);

        // Conversion du DTO en Entity
        return questionTransform.convertToEntity(pQuestionDto);
    }
    
	@PostMapping(value = PATH_CREER_QUESTION)
	public QuestionDto createQuestion(@RequestBody QuestionDto pQuestionDto) throws Exception {
		log.debug("Creation d'une question : {}.", pQuestionDto);
		
		Question lQuestion = questionTransform.convertToEntity(pQuestionDto);
	        // Creation de de la question
		lQuestion = questionService.createQuestion(lQuestion);
	    return questionTransform.convertToDto(lQuestion);
	}

}
