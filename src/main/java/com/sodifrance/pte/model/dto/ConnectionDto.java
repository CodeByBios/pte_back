package com.sodifrance.pte.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionDto {

	String login;
	
	String password;

	public ConnectionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConnectionDto(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

}
