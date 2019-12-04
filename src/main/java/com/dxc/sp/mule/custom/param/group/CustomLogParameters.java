package com.dxc.sp.mule.custom.param.group;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class CustomLogParameters {

	@Parameter
	@Optional(defaultValue = "#[correlationId]")
	@DisplayName("Correlation ID")
	@Summary("Correlation UUID")
	@Example("#[correlationId]")
	private String correlationId;

	@Parameter
	@Optional
	@DisplayName("Message")
	@Summary("Message to be logged")
	private String message;

	@Parameter
	@Optional(defaultValue = "#[write(payload, \"application/java\")]")
	@DisplayName("Payload")
	@Summary("Payload to be logged")
	@Example("#[write(payload, \"application/java\")]")
	private String payload;

	@Parameter
	@Optional(defaultValue = "#[write(attributes, \"application/java\")]")
	@DisplayName("Attributes")
	@Summary("Attributes to be logged")
	@Example("#[write(attributes, \"application/java\")]")
	private String attributes;
	
	@Parameter
	@Optional(defaultValue = "#[if (error != null) write(error, \"application/java\") else \"\"]")
	@DisplayName("Exception Details")
	@Summary("Exception to be logged")
	@Example("#[if (error != null) write(error, \"application/java\") else \"\"]")
	private String error;
	
	@Parameter
	@DisplayName("Level")
	@OfValues(StaticLogLevelValueProvider.class)
	@Optional(defaultValue = "INFO")
	private String level;

	@Parameter
	@Optional
	@DisplayName("Category")
	private String category;

	public String getCorrelationId() {
		return correlationId;
	}

	public String getMessage() {
		return message;
	}

	public String getPayload() {
		return payload;
	}

	public String getAttributes() {
		return attributes;
	}

	public String getError() {
		return error;
	}

	public String getLevel() {
		return level.toString();
	}

	public String getCategory() {
		return category;
	}


}
