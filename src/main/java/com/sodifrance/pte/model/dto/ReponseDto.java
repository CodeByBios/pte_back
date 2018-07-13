package com.sodifrance.pte.model.dto;

import java.io.Serializable;

import com.sodifrance.pte.model.entity.Langage;
import com.sodifrance.pte.model.entity.Reponse;

import lombok.Data;

@Data
public class ReponseDto implements Serializable {

	
	private static final long serialVersionUID = -5453615972966648985L;
	
	private String libelle;
	
	private Boolean reponseJuste;
	
    /**
     * Instantiates a new Langage dto.
     */
    public ReponseDto() {
        // Constructeur vide
    }
	/**
     * Instantiates a new Langage dto.
     * @param pLangage
     */
    public ReponseDto(Reponse pReponse) {
        //super(pLangage);
        super();
    }

}
