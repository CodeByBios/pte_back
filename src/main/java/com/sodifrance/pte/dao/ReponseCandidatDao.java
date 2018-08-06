package com.sodifrance.pte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sodifrance.pte.model.entity.ReponseCandidat;

@Repository
public interface ReponseCandidatDao extends JpaRepository<ReponseCandidat, Long>{

}
