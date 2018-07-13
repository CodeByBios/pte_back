package com.sodifrance.pte.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sodifrance.pte.model.entity.Niveau;

@Repository
public interface NiveauDao extends JpaRepository<Niveau, Long>  {
	
	Optional<Niveau> findById(Long id);

}
