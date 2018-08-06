package com.sodifrance.pte.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.ReponseCandidatDao;
import com.sodifrance.pte.model.entity.ReponseCandidat;
import com.sodifrance.pte.service.ReponseCandidatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ReponseCandidatServiceImpl extends AbstractServiceImpl<ReponseCandidat> implements ReponseCandidatService{
	
	@Autowired
	private ReponseCandidatDao reponseCandidatDao;
	
	@Override
	protected JpaRepository<ReponseCandidat, Long> getEntityDao() {
		return reponseCandidatDao;
	}
	
	
	@Override
	public ReponseCandidat createReponse(ReponseCandidat pReponse) {
		return super.createEntity(pReponse);
	}

	@Override
	public Optional<ReponseCandidat> findReponseById(Long pId) {
		return reponseCandidatDao.findById(pId);
	}

	@Override
	public void deleteReponse(ReponseCandidat pReponse) {
		reponseCandidatDao.delete(pReponse);
	}

	

}
