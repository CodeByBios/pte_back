package com.sodifrance.pte.service;

import java.util.Optional;

import com.sodifrance.pte.model.entity.Langage;

public interface LangageService {

	Optional<Langage> findLangageById(Long id);
}
