package com.iktpreobuka.grade_book.controllers.utils;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.security.Views;


public class RESTError {
	@JsonView(Views.Public.class)
	public Integer code;
	@JsonView(Views.Public.class)
	public String message;
	
	public RESTError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RESTError(Integer code, String message) {
		
		super();
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
