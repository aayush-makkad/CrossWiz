package com.crosswiz.common.errors;

public enum PARSER_ERRORS {

	PARSER_BOOTSTRAP_FAILED("Could not parse the events xml");

	private String message;

	PARSER_ERRORS(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
