package com.sodifrance.pte.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.ReponseDao;
import com.sodifrance.pte.model.entity.Reponse;
import com.sodifrance.pte.service.ReponseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("reponseService")
public class ReponseServiceImpl extends AbstractServiceImpl<Reponse> implements ReponseService {
	
	@Autowired
	private ReponseDao reponseDao;
	
	@Override
	protected JpaRepository<Reponse, Long> getEntityDao() {
		return reponseDao;
	}
	
	private Reponse initReponse(Reponse pReponseUpdate) {
		return null;
	}
	
	@Override
	public Reponse createReponse(Reponse pReponse) {
		return super.createEntity(pReponse);
	}

}
