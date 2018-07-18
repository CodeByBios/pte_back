package com.sodifrance.pte.service;

import java.util.List;

import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;

public interface TestService {

	List<Niveau> getListNiveaux();

	List<Langage> getListLangages();

	List<TypeQuestion> getListTypesQuestions();

	List<Question> createTest(Long pIdNiveau, Long pIdLangage, Long pIdTypeQuestion, Long pIdCandidat);

}
