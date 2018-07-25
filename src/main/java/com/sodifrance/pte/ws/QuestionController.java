package com.sodifrance.pte.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.exceptions.PteParametersException;
import com.sodifrance.pte.model.dto.InfoQuestionDto;
import com.sodifrance.pte.model.dto.QuestionDto;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.service.QuestionService;
import com.sodifrance.pte.transform.QuestionTransform;
import com.sodifrance.pte.validator.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pte")
@CrossOrigin
public class QuestionController {
	
	private static final String PATH_QUESTION = "/v1/questions";
	
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
    
    /**
     * Création de la question
     * @param pQuestionDto
     * @return
     * @throws Exception
     */
	@PostMapping(value = PATH_QUESTION)
	public QuestionDto createQuestion(@RequestBody QuestionDto pQuestionDto) throws Exception {
		log.debug("Creation d'une question : {}.", pQuestionDto);
		
		Question lQuestion = validateAndTransform(pQuestionDto);
		
	    // Creation de de la question
		lQuestion = questionService.createQuestion(lQuestion);
	    return questionTransform.convertToDto(lQuestion);
	}
	
	/**
	 * Modification de la question
	 * @param pQuestionDto
	 * @return
	 * @throws PteParametersException
	 */
	@PutMapping(value = PATH_QUESTION)
	public QuestionDto updateQuestion(@RequestBody QuestionDto pQuestionDto) throws PteParametersException{
		log.debug("Modification de la question et des réponses : {} ", pQuestionDto);
		
		Question lQuestion = validateAndTransform(pQuestionDto);
		
		//Mise à jour de la question et des réponses
		lQuestion = questionService.updateQuestion(lQuestion);
		
		QuestionDto lQuestionDto = questionTransform.convertToDto(lQuestion);
		return lQuestionDto;
	}
	
    /**
     * Récupération de toutes les Questions
     * @return
     * @throws Exception
     */
    @GetMapping(value = PATH_QUESTION + "/{actif}")
    public List<QuestionDto> getAllQuestions(@PathVariable Boolean actif) throws Exception {
        log.debug("Récupération des outils.");
        List<Question> lListQuestions = questionService.getAllQuestionsActives(actif);
        return questionTransform.listEntityToListDto(lListQuestions);
    }
    
    
    /**
     * Récupération de toutes les Questions classées par Niveau
     * @return
     * @throws Exception
     */
    @GetMapping(value = PATH_QUESTION)
    public List<InfoQuestionDto> getAllQuestionsByNiveau() throws Exception {
        log.debug("Récupération des questons par niveaux.");
        List<InfoQuestionDto> lListQuestions = questionService.getAllQuestionsByNiveau();
        return lListQuestions;
    }
    
    /**
     * Suppression d'une question
     * @param pIdQuestion
     * @return
     */
    @DeleteMapping(value = PATH_QUESTION + "/{pIdQuestion}")
    public void deleteQuestion(@PathVariable Long pIdQuestion) throws Exception {
        log.debug("Suppréssion d'une question : {}.", pIdQuestion);
        questionService.deleteQuestion(pIdQuestion);
    }

}
