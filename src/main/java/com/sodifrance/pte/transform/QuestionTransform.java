package com.sodifrance.pte.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sodifrance.pte.model.dto.LangageDto;
import com.sodifrance.pte.model.dto.NiveauDto;
import com.sodifrance.pte.model.dto.QuestionDto;
import com.sodifrance.pte.model.dto.ReponseDto;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.Reponse;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.service.LangageService;
import com.sodifrance.pte.service.NiveauService;
import com.sodifrance.pte.service.ReponseService;
import com.sodifrance.pte.service.TypeQuestionService;


/**
 * Classe de conversion des beans Niveau et NiveauDto.
 */
@Component
public class QuestionTransform {

	public QuestionTransform() {};
	
	@Autowired
	private NiveauService niveauService;
	
	@Autowired
	private LangageService langageService;
	
	@Autowired
	private TypeQuestionService typeQuestionService;
	
	@Autowired
	private ReponseService reponseService;
	
	@Autowired
	private TypeQuestionTransform typeQuestionTransform;
	
	@Autowired
	private NiveauTransform niveauTransform;
	
	@Autowired
	private LangageTransform langageTransform;
	
	@Autowired
	private ReponseTransform reponseTransform;
	
	/*@Autowired
	private ModelMapper modelMapper;*/

	 @PostConstruct
	    public void init() {
	    	
	    	ModelMapper modelMapper = new ModelMapper();
	    	
	        // Pour la transformation Question -> QuestionDto
	        modelMapper.addMappings(new PropertyMap<Question, QuestionDto>() {
	            @Override
	            protected void configure() {
	            }
	        });
	    }
	
	/**
	 * Converti un dto en entité
	 *
	 * @param utilisateurDto
	 *            objet à convertir en entité
	 * @return un utilisateur
	 */
	public Question convertToEntity(QuestionDto pQuestionDto) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		Question lQuestion = modelMapper.map(pQuestionDto, Question.class);
		
		//Question lQuestion = new Question();
		
		if(pQuestionDto !=null) {
			lQuestion.setLibelle(pQuestionDto.getLibelle());
			lQuestion.setEtat(pQuestionDto.getEtat());
			
			//pQuestionDto.setTypeQuestionDto(typeQuestionTransform.convertToDto(lQuestion.getTypeQuestion()));
			
			//pQuestionDto.setTypeQuestionDto(typeQuestionTransform.convertEntityToDto(lQuestion.getTypeQuestion()));
			
			TypeQuestion lTypeQuestion = new  TypeQuestion();
			lTypeQuestion.setId(pQuestionDto.getTypeQuestionDto().getId());
			lTypeQuestion.setLibelle(pQuestionDto.getTypeQuestionDto().getLibelle());
			lQuestion.setTypeQuestion(lTypeQuestion);
			
			List<Langage> lLangages = new ArrayList<Langage>();
			List<Reponse> lReponses = new ArrayList<Reponse>();
			List<Niveau> lNiveaux = new ArrayList<Niveau>();
			
			//pQuestionDto.setLangageDto(langageTransform.convertListEntityToListDto(lQuestion.getLangages()));
			
			pQuestionDto.getLangageDto().forEach(langageDto ->{
				Langage lLangage = new Langage();
				lLangage.setId(langageDto.getId());
				lLangage.setLibelle(langageDto.getLibelle());
				lLangages.add(lLangage);
			});
			
			pQuestionDto.getReponseDto().forEach(reponseDto ->{
				Reponse lReponse = new Reponse();
				lReponse.setId(reponseDto.getId());
				lReponse.setReponseJuste(reponseDto.getReponseJuste());
				lReponse.setLibelle(reponseDto.getLibelle());
				lReponses.add(lReponse);
			});
			
			lQuestion.setLangages(lLangages);
			lQuestion.setReponses(lReponses);
			
			pQuestionDto.getNiveauDto().forEach(niveauDto ->{
				Niveau lNiveau = new Niveau();
				lNiveau.setId(niveauDto.getId());
				lNiveau.setLibelle(niveauDto.getLibelle());
				lNiveaux.add(lNiveau);
			});
			lQuestion.setNiveaux(lNiveaux);
			
			
			 ///BeanUtils.copyProperties(pQuestionDto, lQuestion);
		}
		
		/*if(CollectionUtils.isEmpty(lQuestion.getLangages())) {
			lQuestion.setLangages(null);
		}else {
			lQuestion.setLangages(lQuestion.getLangages().stream().map(langage -> langageService.findLangageById(langage.getId()).get()).collect(Collectors.toSet()));
		}
		if(CollectionUtils.isEmpty(lQuestion.getLangages())) {
			lQuestion.setLangages(null);
		}else {
			lQuestion.setNiveaux(lQuestion.getNiveaux().stream().map(niveau -> niveauService.findNiveauById(niveau.getId()).get()).collect(Collectors.toSet()));
		}
		if(CollectionUtils.isEmpty(lQuestion.getReponses())) {
			lQuestion.setLangages(null);
		}else {
			lQuestion.setReponses(lQuestion.getReponses().stream().map(reponse -> reponseService.findReponseById(reponse.getId()).get()).collect(Collectors.toSet()));
		}*/

		return lQuestion;
	}
	
    /**
     * Converti une liste de dto en une liste d'entités
     * @param pQuestionDto la liste de dto à convertir
     * @return la liste de d'entités correspondants
     */
	public List<Question> convertToListEntity(List<QuestionDto> pQuestionDto) {
		List<Question> lListQuestions = pQuestionDto.stream().filter(question -> question != null).map(quest -> this.convertToEntity(quest)).collect(Collectors.toList());
		return lListQuestions;
		
	}
    
    /**
	 * Converti une entité en dto
	 *
	 * @param user
	 *            l'entité à convertir
	 * @return le dto correspondant
	 */
	public QuestionDto convertToDto(Question pQuestion) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		QuestionDto lQuestionDto = modelMapper.map(pQuestion, QuestionDto.class);
		
		//QuestionDto lQuestionDto = new QuestionDto();

		lQuestionDto.setTypeQuestionDto(typeQuestionTransform.convertToDto(pQuestion.getTypeQuestion()));
		lQuestionDto.setNiveauDto(niveauTransform.convertListEntityToListDto(pQuestion.getNiveaux()));
		lQuestionDto.setLangageDto(langageTransform.convertListEntityToListDto(pQuestion.getLangages()));
		lQuestionDto.setReponseDto(reponseTransform.convertListEntityToListDto(pQuestion.getReponses()));

		/*if (!CollectionUtils.isEmpty(lQuestionDto.getNiveauDto())) {
			lQuestionDto.setNiveauDto(pQuestion.getNiveaux().stream().map(NiveauDto::new).collect(Collectors.toSet()));
		}
		if (!CollectionUtils.isEmpty(lQuestionDto.getLangageDto())) {
			lQuestionDto.setLangageDto(pQuestion.getLangages().stream().map(LangageDto::new).collect(Collectors.toSet()));
		}
		if (!CollectionUtils.isEmpty(lQuestionDto.getReponseDto())) {
			lQuestionDto.setReponseDto(pQuestion.getReponses().stream().map(ReponseDto::new).collect(Collectors.toSet()));
		}*/

		return lQuestionDto;
	}
	
    /**
     * Converti une liste d'entités en liste de dto
     * @param entityList la liste d'entités à convertir
     * @return la liste de dto correspondants
     */
    public List<QuestionDto> listEntityToListDto(List<Question> lListQuestion) {
        List<QuestionDto> dtoList = lListQuestion.stream().filter(elt -> elt != null).map(u -> this.convertToDto(u)).collect(Collectors.toList());
        return dtoList;
    }
    
}
