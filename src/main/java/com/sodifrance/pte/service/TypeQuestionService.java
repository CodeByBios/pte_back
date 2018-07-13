package com.sodifrance.pte.service;

import java.util.Optional;

import com.sodifrance.pte.model.entity.TypeQuestion;

public interface TypeQuestionService {

	Optional<TypeQuestion> findTypeQuestionById(Long id);

}
