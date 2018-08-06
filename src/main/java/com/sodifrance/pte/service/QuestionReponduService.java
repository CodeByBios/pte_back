package com.sodifrance.pte.service;

import java.util.List;
import java.util.Optional;

import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.QuestionRepondu;

public interface QuestionReponduService {
	
	QuestionRepondu createQuestion(QuestionRepondu pQuestion);

	Optional<QuestionRepondu> findQuestionById(Long pId);

	List<QuestionRepondu> getAllQuestionsReponduByCandidat(Candidat pCandidat);

	void deleteQuestion(Long pIdQuestion);

}
