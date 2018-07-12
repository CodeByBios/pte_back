package com.sodifrance.pte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.LangageDao;
import com.sodifrance.pte.dao.NiveauDao;
import com.sodifrance.pte.dao.TypeQuestionDao;
import com.sodifrance.pte.service.CreateTestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("createtestService")
public class CreateTestServiceImpl implements CreateTestService {

	@Autowired
	private NiveauDao niveauDao;
	
	@Autowired
	private LangageDao langageDao;
	
	@Autowired
	private TypeQuestionDao typeQuestionDao;
}
