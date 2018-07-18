package com.sodifrance.pte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.CandidatDao;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.service.CandidatService;
import com.sodifrance.pte.service.UtilisateurService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional
@Service("candidatService")
public class CandidatServiceImpl extends AbstractServiceImpl<Candidat> implements CandidatService {
	
	@Autowired
	private CandidatDao candidatDao;
	
	@Autowired
	private UtilisateurService utilisateurService;

	@Override
	protected JpaRepository<Candidat, Long> getEntityDao() {
		return candidatDao;
	}
	
	@Override
	public Candidat createCandidtat(Candidat pCandidat) {
		
		//TODO penser à mettre l'utilisateur
		
		return super.createEntity(pCandidat);
		
	}
	
}
