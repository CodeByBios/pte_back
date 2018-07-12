package com.sodifrance.pte.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role implements Serializable {

	private static final long serialVersionUID = 3117584371962380796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotNull
	@Size(min = 2, max = 255)
	String identite;


	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(@NotNull @Size(min = 2, max = 255) String identite) {
		super();
		this.identite = identite;
	}
}
