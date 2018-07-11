package com.sodifrance.pte.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role {

	@Id
	@GeneratedValue
	Long IdRole;
	
	@NotNull
	@Size(min = 2, max = 255)
	String identite;

	public Role(@NotNull @Size(min = 2, max = 255) String identite) {
		super();
		this.identite = identite;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
