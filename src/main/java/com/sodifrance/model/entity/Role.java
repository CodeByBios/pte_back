package com.sodifrance.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	String identite;

	public Role(@NotNull String identite) {
		super();
		this.identite = identite;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
