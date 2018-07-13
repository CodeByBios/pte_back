package com.sodifrance.pte.service;

import java.util.Optional;

import com.sodifrance.pte.model.entity.Niveau;

public interface NiveauService {

	Optional<Niveau> findNiveauById(Long id);

	Niveau createNiveau(Niveau pNiveau);

}
