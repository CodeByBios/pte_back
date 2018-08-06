package com.sodifrance.pte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.QuestionRepondu;

@Repository
public interface QuestionReponduDao extends JpaRepository<QuestionRepondu, Long>{
	
	List<QuestionRepondu> findByCandidat(Candidat pCandidat);
}
