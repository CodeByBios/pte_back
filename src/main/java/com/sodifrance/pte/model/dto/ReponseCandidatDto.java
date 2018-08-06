package com.sodifrance.pte.model.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ReponseCandidatDto implements Serializable{

private static final long serialVersionUID = -5453615972966648985L;
	
	private Long id;
	
	private String libelle;
	
	private Boolean reponseJuste;

	public ReponseCandidatDto(Long id, String libelle, Boolean reponseJuste) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.reponseJuste = reponseJuste;
	}

	public ReponseCandidatDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
