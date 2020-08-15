package com.crosswiz.common.errors;

public enum KAFKA_ERRORS {
	KAFKA_PROPERTY_READ_FAILURE("Catastrophic failure while initializaing Kafka producer from properties file");

	private String errorMessage;

	KAFKA_ERRORS(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
