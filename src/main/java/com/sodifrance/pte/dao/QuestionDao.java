package com.sodifrance.pte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {

	List<Question> findByNiveauxAndLangagesAndTypeQuestion(Niveau pNiveau, Langage pLangage, TypeQuestion pTypeQuestion);

}
