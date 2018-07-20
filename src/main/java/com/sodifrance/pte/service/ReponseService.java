package com.sodifrance.pte.service;

import java.util.Optional;

import com.sodifrance.pte.model.entity.Reponse;

public interface ReponseService {

	Reponse createReponse(Reponse pReponse);

	Optional<Reponse> findReponseById(Long pId);

	Reponse updateReponse(Reponse pReponse);
	
	void deleteReponse(Reponse reponse);

}
