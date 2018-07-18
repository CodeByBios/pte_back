package com.sodifrance.pte.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.NiveauDao;
import com.sodifrance.pte.model.entity.Niveau;
import com.sodifrance.pte.service.NiveauService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("niveauservice")
public class NiveauServiceImpl implements NiveauService {
	
	@Autowired
	private NiveauDao niveauDao;
	
	@Override
	public Optional<Niveau> findNiveauById(Long id) {
		return niveauDao.findById(id);
	}
}
