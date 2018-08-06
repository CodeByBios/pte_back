package com.sodifrance.pte.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.model.dto.QuestionReponduDto;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.QuestionRepondu;
import com.sodifrance.pte.service.CandidatService;
import com.sodifrance.pte.service.QuestionReponduService;
import com.sodifrance.pte.transform.QuestionReponduTransform;
import com.sodifrance.pte.validator.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pte")
@CrossOrigin
public class QuestionReponduController {

	private static final String PATH_QUESTION = "/v1/questionrepondu";

	@Autowired
	private QuestionReponduService questionReponduService;

	@Autowired
	private QuestionReponduTransform questionReponduTransform;

	@Autowired
	private CandidatService candidatService;

	/**
	 * Execute les validateurs sur le dto.<br>
	 * Converti le dto en Entity.
	 *
	 * @param pQuestionDto
	 *            la question dto
	 * @return la Question entity
	 */
	QuestionRepondu validateAndTransform(QuestionReponduDto pQuestionReponduDto) {
		// Validation du DTO
		DtoValidator.validateDto(pQuestionReponduDto);
		// Conversion du DTO en Entity
		return questionReponduTransform.convertToEntity(pQuestionReponduDto);
	}

	/**
	 * Création des questions repondues pour un candidat
	 * 
	 * @param QuestionReponduDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = PATH_QUESTION)
	public QuestionRepondu createQuestionRepondu(@RequestBody QuestionReponduDto pQuestionDto) throws Exception {
		log.debug("Création des questions repondues pour un candidat : {}.", pQuestionDto);

		QuestionRepondu lQuestion = validateAndTransform(pQuestionDto);
		return questionReponduService.createQuestion(lQuestion);
	}

	/**
	 * Récupération de toutes les Questions
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = PATH_QUESTION + "/{pIdCandidat}")
	public List<QuestionRepondu> getQuestionsReponduByCandidat(@PathVariable Long pIdCandidat) throws Exception {
		log.debug("Récupération des outils.");

		Candidat candidat = candidatService.findCandidatById(pIdCandidat).get();
		return questionReponduService.getAllQuestionsReponduByCandidat(candidat);
	}

}
