package com.sodifrance.pte.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sodifrance.pte.dao.ReponseDao;
import com.sodifrance.pte.exceptions.PteParametersException;
import com.sodifrance.pte.model.entity.Question;
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
	
	@Override
	public Optional<Reponse> findReponseById(Long pId) {
		return reponseDao.findById(pId);
	}
	
	
	/**
	 * Convert update dto
	 * @param pReponseUpdate : les nouvelles données de la réponse
	 * @return La réponse à enregistrer
	 */
	private Reponse initUpdateReponse(Reponse pReponseUpdate) {
		
		Reponse lReponse = findReponseById(pReponseUpdate.getId()).get();
		
		if(lReponse.getId() != null) {
			lReponse.setLibelle(pReponseUpdate.getLibelle());
			lReponse.setReponseJuste(pReponseUpdate.getReponseJuste());

		}else {
			throw new PteParametersException("La réponse n'esxiste pas");
		}
		
		return lReponse;
	}
	
	/**
	 * Créationn des réponses
	 * @param pReponse
	 * @return La réponse à enregistrer
	 */
	@Override
	public Reponse createReponse(Reponse pReponse) {
		return super.createEntity(pReponse);
	}
	
	/**
	 * Modification des réponses
	 * @param pReponse
	 * @return
	 */
	@Override
	public Reponse updateReponse(Reponse pReponse) {
		
		Reponse lReponseUpdate = initUpdateReponse(pReponse);
		super.updateEntity(lReponseUpdate);
		
		return lReponseUpdate;
		
	}
	
	@Override
	public void deleteReponse(Reponse reponse) {
		reponseDao.delete(reponse);
	}

}
