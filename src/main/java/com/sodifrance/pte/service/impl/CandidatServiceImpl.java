package com.sodifrance.pte.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.CandidatDao;
import com.sodifrance.pte.exceptions.PteParametersException;
import com.sodifrance.pte.model.entity.Candidat;
import com.sodifrance.pte.model.entity.Utilisateur;
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
	
	
	/**
	 * Mise à jour de la note du candidat
	 * @param pIdCandidat
	 * @param lNoteCandidat
	 * @return
	 */
	@Override
	public Candidat saveNoteCandidat(Candidat pCandidatUpdate) {
		Candidat lCandidat = findCandidatById(pCandidatUpdate.getId()).get();
		if(lCandidat != null) {
			lCandidat.setNote(pCandidatUpdate.getNote());
			lCandidat= this.updateCandidat(lCandidat);
		}else
			throw new PteParametersException("Le Candidat n'esxiste pas");
		return lCandidat;
	}
	
	@Override
	public Candidat createCandidtat(Long pIdUtilisateur, Candidat pCandidat) {
		
		//l'utilisateur connecté (Retourner depuis le front)
		Utilisateur lUtlisateur =  utilisateurService.finfUtilisateurById(pIdUtilisateur).get();
		
		if(lUtlisateur != null)
		pCandidat.setUtlisateur(lUtlisateur);
		else 
			throw new PteParametersException("L'utilisateur n'esxiste pas");
		pCandidat.setDate(LocalDateTime.now());
		return super.createEntity(pCandidat);
	}
	
	@Override
	public List<Candidat> getAllCandidats(){
		return candidatDao.findAll();
	}
	
	@Override
	public Optional<Candidat> findCandidatById(Long pId){
		return candidatDao.findById(pId);
	}
	
	@Override
	public Candidat updateCandidat(Candidat pCandidat){
		return super.updateEntity(pCandidat);
	}
	
	@Override
	public void deleteCandidat(Long pIdCandidat) {
		Candidat lCandidat = candidatDao.findById(pIdCandidat).get();
		if(lCandidat.getId() != null) {
			candidatDao.delete(lCandidat);
		}
	}
	
}
