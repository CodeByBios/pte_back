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

import com.google.common.collect.Sets;
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
	public List<Question> createTest(Long pIdNiveau, List<Long> pIdLangages, Long pIdTypeQuestion, Long pIdCandidat) {
		
		List<Question> lListQuestions = new ArrayList<Question>();
		
		pIdLangages.forEach(idLangage -> {
			lListQuestions.addAll(questionService.getAllQuestionByNiveauxAndLangagesAndTypeQuestion(niveauDao.findById(pIdNiveau).get(), langageDao.findById(idLangage).get(), typeQuestionDao.findById(pIdTypeQuestion).get()));
		});
		
		List<Question> lListQuestionsActives = lListQuestions.stream().filter(questActif -> questActif.getEtat().equals(Boolean.TRUE)).collect(Collectors.toList());
		
		Set<Question> lListQuestionsRandomSansDoublons = new HashSet<Question>();
		
		List<Question> lConvertSetToListQuestion = new ArrayList<>(lListQuestionsRandomSansDoublons);
		
		if(lListQuestions!=null) {
			//TODO modifier pour 20 questions
			int lNombreMaxQuestions = 10;
			Random lRandomQuestion = new Random();
			int cpt = 0;

			//TODO vérifier bien que le random n'a pas de doublonts
			//lListQuestionsRandomSansDoublons = lRandomQuestion.ints(lNombreMaxQuestions, 0, lListQuestionsActives.size()).mapToObj(i -> lListQuestionsActives.get(i)).collect(Collectors.toSet());
			
			for (int i = 0; i < lListQuestionsActives.size(); i++) {
				
		        int randomIndex = lRandomQuestion.nextInt(lListQuestionsActives.size());
		        Question randomElement = lListQuestionsActives.get(i);
		        
		        if(!lListQuestionsRandomSansDoublons.contains(randomElement) && cpt<lNombreMaxQuestions) {
		        	lListQuestionsRandomSansDoublons.add(randomElement);
		        	cpt++;
		        }
		    }

			if(lListQuestionsRandomSansDoublons != null && lListQuestionsRandomSansDoublons.size()==lNombreMaxQuestions) {
				Optional<Candidat> lCandidat = candidatService.findCandidatById(pIdCandidat);
				if(lCandidat.isPresent()) {
					//Mise à jour des question dans candidat
					lConvertSetToListQuestion.addAll(lListQuestionsRandomSansDoublons);
					lCandidat.get().setQuestions(lConvertSetToListQuestion);
					candidatService.updateCandidat(lCandidat.get());
				}else {
					throw new PteParametersException("Le candidat n'exixte pas");
				}
			}else {
				throw new PteParametersException("Le nombre de question pour passer ce test n'est pas suffisant soit :" + lNombreMaxQuestions+ "." + "Il est égal à :" + lListQuestionsRandomSansDoublons.size());
			}
		}else {
			throw new PteParametersException("Aucune Question n'exixte");
		}
		
		return lConvertSetToListQuestion;
		
	}
}
