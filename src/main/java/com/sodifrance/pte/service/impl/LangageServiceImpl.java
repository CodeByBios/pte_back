package com.sodifrance.pte.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.LangageDao;
import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.service.LangageService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional
@Service()
public class LangageServiceImpl implements LangageService {

	@Autowired
	private LangageDao langageDao;
	
	@Override
	public Optional<Langage> findLangageById(Long id) {
		return langageDao.findById(id);
	}
	
	public Set<Langage> findLangageByLibelle(String libelle){
		return langageDao.findByLibelle(libelle);
	}
}
