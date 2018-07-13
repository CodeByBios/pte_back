package com.sodifrance.pte.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.commun.utils.PteCollectionUtils;
import com.sodifrance.pte.model.dto.CreateTestDto;
import com.sodifrance.pte.model.dto.InformationsTestDto;
import com.sodifrance.pte.model.dto.LangageDto;
import com.sodifrance.pte.model.dto.NiveauDto;
import com.sodifrance.pte.model.dto.QuestionDto;
import com.sodifrance.pte.model.dto.TypeQuestionDto;
import com.sodifrance.pte.service.TestService;
import com.sodifrance.pte.transform.LangageTransform;
import com.sodifrance.pte.transform.NiveauTransform;
import com.sodifrance.pte.transform.TypeQuestionTransform;

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
	
	private static final String PATH_TEST_NIVEAU = "/v1/niveaus";
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
		  return PteCollectionUtils.transformCollectionToList(testService.getListTypesQuestions(), typeQuestion -> typeQuestionTransform.convertEntityToDto(typeQuestion));
	  }
	  
	  /*@GetMapping(value = PATH_TEST_INFO)
	  public InformationsTestDto getInfosTestDto(@RequestBody InformationsTestDto pIformationsTestDto) throws Exception {
		  log.debug("Information sur le test : {}.", pIformationsTestDto);
		  
		  return null;

	  }*/
	
	  @PostMapping(value = PATH_TEST_CREER)
	  public CreateTestDto createTest(@RequestBody CreateTestDto pCreateTestDto) throws Exception {
		  log.debug("Creation d'un Test : {}.", pCreateTestDto);
		  
		  return null;

	  }
}
