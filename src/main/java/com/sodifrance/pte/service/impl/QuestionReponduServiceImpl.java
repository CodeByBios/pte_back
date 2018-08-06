package com.sodifrance.pte.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.QuestionReponduDao;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.QuestionRepondu;
import com.sodifrance.pte.service.QuestionReponduService;
import com.sodifrance.pte.service.ReponseCandidatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class QuestionReponduServiceImpl extends AbstractServiceImpl<QuestionRepondu> implements QuestionReponduService{
	
	@Autowired
	private QuestionReponduDao questionReponduDao;
	
	@Autowired
	private ReponseCandidatService reponseCandidatService;
	
	@Override
	protected JpaRepository<QuestionRepondu, Long> getEntityDao() {
		return questionReponduDao;
	}

	
	@Override
	public QuestionRepondu createQuestion(QuestionRepondu pQuestion) {
		pQuestion.getReponsesCandidat().forEach(reponse -> {
			reponseCandidatService.createReponse(reponse);
		});
		return super.createEntity(pQuestion);
	}

	@Override
	public Optional<QuestionRepondu> findQuestionById(Long pId) {
		return questionReponduDao.findById(pId);
	}

	
	@Override
	public void deleteQuestion(Long pIdQuestion) {
		QuestionRepondu lQuestion = questionReponduDao.findById(pIdQuestion).get();
		if(lQuestion.getReponsesCandidat() != null) {
			lQuestion.getReponsesCandidat().forEach(reponse ->{
				reponseCandidatService.deleteReponse(reponse);
			});
		}
		questionReponduDao.delete(lQuestion);
	}

	@Override
	public List<QuestionRepondu> getAllQuestionsReponduByCandidat(Candidat pCandidat) {
		return questionReponduDao.findByCandidat(pCandidat);
	}

}
