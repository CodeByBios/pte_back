package com.sodifrance.pte.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.LangageDao;
import com.sodifrance.pte.dao.NiveauDao;
import com.sodifrance.pte.dao.QuestionDao;
import com.sodifrance.pte.dao.TypeQuestionDao;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.model.entity.Question;
import com.sodifrance.pte.model.entity.TypeQuestion;
import com.sodifrance.pte.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("testservice")
public class CreateTestServiceImpl implements TestService {

	@Autowired
	private NiveauDao niveauDao;
	
	@Autowired
	private LangageDao langageDao;
	
	@Autowired
	private TypeQuestionDao typeQuestionDao;
	
	@Override
	public List<Langage> getListLangages(){
		return langageDao.findAll();
	}
	
	@Override
	public List<TypeQuestion> getListTypesQuestions(){
		return typeQuestionDao.findAll();
	}
	
	@Override
	public List<Niveau> getListNiveaux() {
		return niveauDao.findAll();
	}
}
