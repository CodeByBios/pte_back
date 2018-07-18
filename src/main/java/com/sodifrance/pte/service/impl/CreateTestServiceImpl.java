package com.sodifrance.pte.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.LangageDao;
import com.sodifrance.pte.dao.NiveauDao;
import com.sodifrance.pte.dao.QuestionDao;
import com.sodifrance.pte.dao.TypeQuestionDao;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.Reponse;
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
	
	@Override
	public List<Question> createTest(Long pIdNiveau, Long pIdLangage, Long pIdTypeQuestion, Candidat pCandidat) {
		
		//List<Question> lListQuestions = questionService.getAllQuestion();
		
		List<Question> lListQuestions = questionService.getAllQuestionByNiveauxAndLangagesAndTypeQuestion(niveauDao.findById(pIdNiveau).get(), langageDao.findById(pIdLangage).get(), typeQuestionDao.findById(pIdTypeQuestion).get());
		
		List<Question> lListQuestionsRandom = new ArrayList<Question>();
		
		//TODO rajouter des contraintes sur 20 question
		if(lListQuestions!=null) {
			
			Random lRandomQuestion = new Random();

			//TODO vérifier bien que le random n'a pas de doublonts
			lListQuestionsRandom = lRandomQuestion.ints(1, 0, lListQuestions.size()).mapToObj(i -> lListQuestions.get(i)).collect(Collectors.toList());
			
			pCandidat.setQuestions(lListQuestionsRandom);
			candidatService.createCandidtat(pCandidat);
		}else {
			log.debug("Aucune Question n'exixte");
		}
		
		return lListQuestionsRandom;
		
	}
}
