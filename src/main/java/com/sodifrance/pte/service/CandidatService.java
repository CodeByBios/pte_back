package com.sodifrance.pte.service;

import java.util.List;
import java.util.Optional;

import com.sodifrance.pte.model.entity.Candidat;

public interface CandidatService {

	Candidat createCandidtat(Long pIdUtilisateur, Candidat pCandidat);

	List<Candidat> getAllCandidats();

	Optional<Candidat> findCandidatById(Long pId);

	Candidat updateCandidat(Candidat pCandidat);
	
	Candidat saveNoteCandidat(Candidat pCandidatUpdate);

}
