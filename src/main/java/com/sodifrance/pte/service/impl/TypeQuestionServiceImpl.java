package com.sodifrance.pte.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.TypeQuestionDao;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.service.TypeQuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service()
public class TypeQuestionServiceImpl implements TypeQuestionService {

	@Autowired
	private TypeQuestionDao typeQuestionDao;
	
	@Override
	public Optional<TypeQuestion> findTypeQuestionById(Long id) {
		return typeQuestionDao.findById(id);
	}
}
