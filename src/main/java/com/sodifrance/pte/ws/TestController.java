package com.sodifrance.pte.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.commun.utils.PteCollectionUtils;
import com.sodifrance.pte.model.dto.CandidatDto;
import com.sodifrance.pte.model.dto.LangageDto;
import com.sodifrance.pte.model.dto.NiveauDto;
import com.sodifrance.pte.model.dto.QuestionDto;
import com.sodifrance.pte.model.dto.TypeQuestionDto;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.service.CandidatService;
import com.sodifrance.pte.service.TestService;
import com.sodifrance.pte.transform.CandidatTransform;
import com.sodifrance.pte.transform.LangageTransform;
import com.sodifrance.pte.transform.NiveauTransform;
import com.sodifrance.pte.transform.QuestionTransform;
import com.sodifrance.pte.transform.TestTransform;
import com.sodifrance.pte.transform.TypeQuestionTransform;
import com.sodifrance.pte.validator.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pte")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private NiveauTransform niveauTransform;
	
	@Autowired
	private LangageTransform langageTransform;
	
	@Autowired
	private TypeQuestionTransform typeQuestionTransform;
	
	@Autowired
	private CandidatTransform candidatTransform;
	
	@Autowired
	private QuestionTransform questionTransform;
	
	@Autowired
	private TestTransform testTransform; 
	
	private static final String PATH_TEST_NIVEAU = "/v1/niveaux";
	private static final String PATH_TEST_lANGAGE = "/v1/langages";
	private static final String PATH_TEST_TYPE_QUESTION = "/v1/typesquestions";
	private static final String PATH_TEST_CREER = "/v1/test/creer";
	
	
	@GetMapping(value = PATH_TEST_NIVEAU)
	public List<NiveauDto> getNiveaux() throws Exception {
		log.debug("Récupération des niveaux du test");
		return PteCollectionUtils.transformCollectionToList(testService.getListNiveaux(), niveau -> niveauTransform.convertEntityToDto(niveau));
	}

	@GetMapping(value = PATH_TEST_lANGAGE)
	public List<LangageDto> getLangages() throws Exception {
		log.debug("Récupération des langages du test");
		return PteCollectionUtils.transformCollectionToList(testService.getListLangages(), langage -> langageTransform.convertEntityToDto(langage));
	}

	@GetMapping(value = PATH_TEST_TYPE_QUESTION)
	public List<TypeQuestionDto> getTypesQuetions() throws Exception {
		log.debug("Récupération des types de questions du test");
		return PteCollectionUtils.transformCollectionToList(testService.getListTypesQuestions(), typeQuestion -> typeQuestionTransform.convertToDto(typeQuestion));
	}

	
	/**
     * Execute les validateurs sur le dto.<br>
     * Converti le dto en Entity.
     *
     * @param pCandidatDto la question dto
     * @return la Candidat entity
     */
    Candidat validateAndTransform(CandidatDto pCandidatDto) {
        // Validation du DTO
        DtoValidator.validateDto(pCandidatDto);

        // Conversion du DTO en Entity
        return candidatTransform.convertToEntity(pCandidatDto);
    }
    
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
    
    
	@PostMapping(value = PATH_TEST_CREER + "/{idNiveau}" + "/{idLangage}"+ "/{idTypeQuestion}")
	public List<QuestionDto> createTest(@PathVariable Long idNiveau, @PathVariable Long idLangage, @PathVariable Long idTypeQuestion, @RequestBody CandidatDto pCandidatDto) throws Exception {
		log.debug("Creation d'un Test : {}.", pCandidatDto);

		Candidat lCandidat = validateAndTransform(pCandidatDto);
		
		//Creation du candidat et du Test
		List<Question> lListQuestions = testService.createTest(idNiveau, idLangage, idTypeQuestion, lCandidat);
		candidatTransform.convertToDto(lCandidat);
		//testTransform.convertListEntityToListDto(lListQuestion, lCandidat);
		
		return PteCollectionUtils.transformCollectionToList(lListQuestions, question -> questionTransform.convertToDto(question));

	}
}
