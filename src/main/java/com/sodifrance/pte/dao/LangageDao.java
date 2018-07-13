package com.sodifrance.pte.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sodifrance.pte.model.entity.Langage;

@Repository
public interface LangageDao extends JpaRepository<Langage, Long> {
	
	Set<Langage> findByLibelle(String libelle);
	
}
