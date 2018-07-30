package com.sodifrance.pte.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.LangageDao;
import com.sodifrance.pte.dao.NiveauDao;
import com.sodifrance.pte.dao.TypeQuestionDao;
import com.sodifrance.pte.exceptions.PteParametersException;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.service.CandidatService;
import com.sodifrance.pte.service.QuestionService;
import com.sodifrance.pte.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("testservice")
public class CreateTestServiceImpl implements TestService {

	@Autowired
	private NiveauDao niveauDao;
	
	@Autowired
	private LangageDao langageDao;
	
	@Autowired
	private TypeQuestionDao typeQuestionDao;
	
	@Autowired
	private CandidatService candidatService;
	
	@Autowired
	private QuestionService questionService;
	
	private int nombreMaxQuestions = 10; //TODO modifier pour 20 questions
	
	@Override
	public List<Langage> getListLangages(){
		return langageDao.findAll();
	}
	
	@Override
	public List<TypeQuestion> getListTypesQuestions(){
		return typeQuestionDao.findAll();
	}
	
	@Override
	public List<Niveau> getListNiveaux() {
		return niveauDao.findAll();
	}
	
	
	public Set<Question> getListQuestionsTest(Long pIdNiveau, List<Long> pIdLangages, Long pIdTypeQuestion){
		
		List<Question> lListQuestions = new ArrayList<Question>();
		
		pIdLangages.forEach(idLangage -> {
			lListQuestions.addAll(questionService.getAllQuestionByNiveauxAndLangagesAndTypeQuestion(niveauDao.findById(pIdNiveau).get(), langageDao.findById(idLangage).get(), typeQuestionDao.findById(pIdTypeQuestion).get()));
		});
		
		List<Question> lListQuestionsActives = lListQuestions.stream().filter(questActif -> questActif.getEtat().equals(Boolean.TRUE)).collect(Collectors.toList());
		
		Set<Question> lListQuestionsRandomSansDoublons = new HashSet<Question>();
		
		if(lListQuestions!=null) {
			Random lRandomQuestion = new Random();
			int cpt = 0;

			//TODO vérifier bien que le random n'a pas de doublonts
			//lListQuestionsRandomSansDoublons = lRandomQuestion.ints(lNombreMaxQuestions, 0, lListQuestionsActives.size()).mapToObj(i -> lListQuestionsActives.get(i)).collect(Collectors.toSet());
			
			for (int i = 0; i < lListQuestionsActives.size(); i++) {
				
		        int randomIndex = lRandomQuestion.nextInt(lListQuestionsActives.size());
		        Question randomElement = lListQuestionsActives.get(i);
		        
		        if(!lListQuestionsRandomSansDoublons.contains(randomElement) && cpt< nombreMaxQuestions) {
		        	lListQuestionsRandomSansDoublons.add(randomElement);
		        	cpt++;
		        }
		    }
		}else {
			throw new PteParametersException("Aucune Question n'exixte");
		}
		return lListQuestionsRandomSansDoublons;
	}
	
	@Override
	public List<Question> createTest(Long pIdNiveau, List<Long> pIdLangages, Long pIdTypeQuestion, Long pIdCandidat) {
		
		List<Question> lConvertSetToListQuestion = new ArrayList<>(getListQuestionsTest(pIdNiveau, pIdLangages, pIdTypeQuestion));
		
			if(lConvertSetToListQuestion != null) {
				Optional<Candidat> lCandidat = candidatService.findCandidatById(pIdCandidat);
				if(lCandidat.isPresent()) {
					//Mise à jour des question dans candidat
					lCandidat.get().setQuestions(lConvertSetToListQuestion);
					candidatService.updateCandidat(lCandidat.get());
				}else {
					throw new PteParametersException("Le candidat n'exixte pas");
				}
			}
		
		return lConvertSetToListQuestion;
		
	}
	
	@Override
	public Boolean countNombresQuestionsTest(Long pIdNiveau, List<Long> pIdLangages, Long pIdTypeQuestion) {
		if(getListQuestionsTest(pIdNiveau, pIdLangages, pIdTypeQuestion).size() == nombreMaxQuestions) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
}
