package com.sodifrance.pte.model.entity;

import java.io.Serializable;

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
public class Role implements Serializable {

	private static final long serialVersionUID = 3117584371962380796L;

	@Id
	@GeneratedValue
	Long idRole;
	
	@NotNull
	@Size(min = 2, max = 255)
	String identite;


	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long idRole, @NotNull @Size(min = 2, max = 255) String identite) {
		super();
		this.idRole = idRole;
		this.identite = identite;
	}
	
}
