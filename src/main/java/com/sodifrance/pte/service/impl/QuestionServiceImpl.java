package com.sodifrance.pte.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.QuestionDao;
import com.sodifrance.pte.dao.ReponseDao;
import com.sodifrance.pte.exceptions.PteParametersException;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.service.LangageService;
import com.sodifrance.pte.service.QuestionService;
import com.sodifrance.pte.service.ReponseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("questionService")
public class QuestionServiceImpl extends AbstractServiceImpl<Question> implements QuestionService {

	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private ReponseService reponseService;
	
	@Autowired
	private LangageService langageService;
	
	@Autowired
	private ReponseDao reponseDao;
	
	@Override
	protected JpaRepository<Question, Long> getEntityDao() {
		return questionDao;
	}
	
	@Override
	public Optional<Question> findQuestionById(Long id) {
		return questionDao.findById(id);
	}
	
	/**
	 * Convert update dto
	 * @param pQuestionUpdate : les nouvelles données de la question
	 * @return La question à enregistrer
	 */
	private Question initUpdateQuestion(Question pQuestionUpdate) {
		
		Question lQuestion = findQuestionById(pQuestionUpdate.getId()).get();
		
		if(lQuestion != null) {
			
			if(pQuestionUpdate.getEtat() != null) {
				if(lQuestion.getEtat().equals(Boolean.TRUE)) {
					lQuestion.setEtat(pQuestionUpdate.getEtat());
				}else {
					lQuestion.setEtat(pQuestionUpdate.getEtat());
					lQuestion.setLibelle(pQuestionUpdate.getLibelle());
					lQuestion.setLangages(pQuestionUpdate.getLangages());
					lQuestion.setNiveaux(pQuestionUpdate.getNiveaux());
					lQuestion.setTypeQuestion(pQuestionUpdate.getTypeQuestion());
					lQuestion.setReponses(pQuestionUpdate.getReponses());
				}
			}
		}else throw new PteParametersException("La question n'esxiste pas");
		
		return lQuestion;
	}
	
	/**
	 * Créationn de la Question avec les Réponses
	 * @param pQuestion
	 * @return La question à enregistrer
	 */
	@Override
	public Question createQuestion(Question pQuestion) {
		
		pQuestion.getReponses().forEach(reponse ->{
			reponseService.createReponse(reponse);
		});
		
		return super.createEntity(pQuestion);
	}
	
	/**
	 * Modification de la Question avec les Réponses
	 * @param pQuestion
	 * @return La question à enregistrer avec les réponses
	 */
	@Override
	public Question updateQuestion(Question pQuestion) {
		
		Question lQuestionUpdate = initUpdateQuestion(pQuestion);
		
		lQuestionUpdate.getReponses().forEach(updateReponse ->{
			reponseService.updateReponse(updateReponse);
		});
		
		super.updateEntity(lQuestionUpdate);
		
		return lQuestionUpdate;
		
	}
	
	@Override
	public List<Question> getAllQuestions(){
		return questionDao.findAll();
	}
	
	@Override
	public List<Question> getAllQuestionByNiveauxAndLangagesAndTypeQuestion(Niveau pNiveau, Langage pLangage, TypeQuestion pTypeQuestion){
		return questionDao.findByNiveauxAndLangagesAndTypeQuestion(pNiveau, pLangage, pTypeQuestion);
	}
	
	@Override
	public List<Question> getAllQuestionsActives(Boolean actif){
		List<Question> lListQuestions = this.getAllQuestions();
		if(actif == null) {
			return lListQuestions;
		}else {
			return lListQuestions.stream().filter(quest -> actif ? quest.getEtat() : !quest.getEtat()).collect(Collectors.toList());
		}
	}
	
	//TODO Vérifier que la question supprimer n'est pas encore utiliser par un candidat
	@Override
	public void deleteQuestion(Long pIdQuestion) {
		Question lQuestion = findQuestionById(pIdQuestion).get();
		if(lQuestion.getId() != null) {
			if(lQuestion.getReponses() != null) {
				lQuestion.getReponses().forEach(reponse ->{
					reponseDao.delete(reponse);
				});
			}
			questionDao.delete(lQuestion);
		}
	}

	@Override
	public List<Question> getAllQuestionsByNiveau(Niveau pNiveau) {
		//return questionDao.findAndGroupBy(pNiveau);
		return null;
	}
}
