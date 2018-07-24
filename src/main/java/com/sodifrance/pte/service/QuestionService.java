package com.sodifrance.pte.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;

public interface QuestionService {

	Question createQuestion(Question pQuestion);

	Optional<Question> findQuestionById(Long id);

	Question updateQuestion(Question pQuestion);

	List<Question> getAllQuestions();

	List<Question> getAllQuestionByNiveauxAndLangagesAndTypeQuestion(Niveau pNiveau, Langage pLangage, TypeQuestion pTypeQuestion);

	List<Question> getAllQuestionsActives(Boolean actif);

	void deleteQuestion(Long pIdQuestion);

	Map<Object, List<Question>> getAllQuestionsByNiveau();

}
