package com.dxc.sp.mule.custom;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(CustomLoggerOperations.class)
@ConnectionProviders(CustomLoggerConnectionProvider.class)
public class CustomLoggerConfiguration {

	@Parameter
	@DisplayName("Application Name")
	@Summary("Name of the Mule Application")
	@Optional(defaultValue = "${app.name}")
	private String appName;

	@Parameter
	@DisplayName("Application Version")
	@Summary("Version of the Mule Application")
	@Optional(defaultValue = "${app.version}")
	private String appVersion;

	@Parameter
	@DisplayName("Environment")
	@Summary("Mule Application Environment")
	@Optional(defaultValue = "${env}")
	private String env;

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public String getEnv() {
		return env;
	}
	
	
}
