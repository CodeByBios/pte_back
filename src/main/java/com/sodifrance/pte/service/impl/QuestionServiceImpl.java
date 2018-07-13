package com.sodifrance.pte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.QuestionDao;
import com.sodifrance.pte.model.entity.Question;
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
	
	@Override
	protected JpaRepository<Question, Long> getEntityDao() {
		return questionDao;
	}
	
	private Question initQuestion(Question pQuestionUpdate) {
		return null;
	}
	
	@Override
	public Question createQuestion(Question pQuestion) {
		
		pQuestion.getReponses().forEach(reponse ->{
			reponseService.createReponse(reponse);
		});
		
		return super.createEntity(pQuestion);
		
	}
}
