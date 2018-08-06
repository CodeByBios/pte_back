package com.sodifrance.pte.service;

import java.util.Optional;

import com.sodifrance.pte.model.entity.ReponseCandidat;

public interface ReponseCandidatService {

	ReponseCandidat createReponse(ReponseCandidat pReponse);

	Optional<ReponseCandidat> findReponseById(Long pId);
	
	void deleteReponse(ReponseCandidat reponse);
}
