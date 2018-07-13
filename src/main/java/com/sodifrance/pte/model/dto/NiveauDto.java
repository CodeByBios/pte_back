package com.sodifrance.pte.model.dto;

import java.io.Serializable;

import com.sodifrance.pte.model.entity.Niveau;

import lombok.Data;

@Data
public class NiveauDto implements Serializable {

	private static final long serialVersionUID = -8927821806754615914L;
	
	private Long id;
	
	private String libelle;
	
    /**
     * Instantiates a new NiveauD dto.
     */
    public NiveauDto() {
        // Constructeur vide
    }
	/**
     * Instantiates a new NiveauD dto.
     * @param pNiveauD
     */
    public NiveauDto(Niveau pNiveau) {
        //super(pLangage);
        super();
    }

}
