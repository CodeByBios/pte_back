package com.sodifrance.pte.model.dto;

import java.io.Serializable;

import com.sodifrance.pte.model.entity.Langage;

import lombok.Data;

@Data
public class LangageDto implements Serializable {
	
	private static final long serialVersionUID = -4576065398560029513L;

	private Long id;
	
	private String libelle;
	
    /**
     * Instantiates a new Langage dto.
     */
    public LangageDto() {
        // Constructeur vide
    }
	/**
     * Instantiates a new Langage dto.
     * @param pLangage
     */
    public LangageDto(Langage pLangage) {
        //super(pLangage);
        super();
    }

}
