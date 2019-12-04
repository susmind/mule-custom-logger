package com.dxc.sp.mule.custom;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.ObjectMessage;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.dxc.sp.mule.custom.param.group.CustomLogParameters;

import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;

/**
 * This class is a container for custom logging operations, every public method
 * in this class will be taken as an custom logging extension operation.
 */
public class CustomLoggerOperations {

	/**
	 * Example of an operation that uses the configuration and a connection instance
	 * to perform some action.
	 */
	@MediaType(value = ANY, strict = false)
	@DisplayName("Custom File Logger")
	public void customFileLogger(@Config CustomLoggerConfiguration configuration,
			@Connection CustomLoggerConnection connection,
			@ParameterGroup(name = "Log Details") CustomLogParameters logParameters,			
			CompletionCallback<Void, Void> callback) {

	    try
	    {

		Logger logger = connection.getLOGGER();

		System.out.println("Starting Custom Log: " + logger);

		Map<String, Object> logMsg = new HashMap<String, Object>();

		//timestamp
		logMsg.put("@timestamp", Instant.now().toString());
		
		//App context
		logMsg.put("app_name", configuration.getAppName());
		logMsg.put("app_version", configuration.getAppVersion());
		logMsg.put("app_env", configuration.getEnv());
		
		//Message context
		logMsg.put("level", logParameters.getLevel());
		logMsg.put("correlation_id", logParameters.getCorrelationId());
		logMsg.put("category", logParameters.getCategory());
		logMsg.put("message", logParameters.getMessage());
		logMsg.put("payload", logParameters.getPayload());
		logMsg.put("attributes", logParameters.getAttributes());
		logMsg.put("exception", logParameters.getError());
		
		ObjectMessage obj = new ObjectMessage(logMsg);
		logger.log(getLogLevel(logParameters.getLevel()), obj);

		System.out.println("Completed Custom Log: " + logger);
			
		callback.success(Result.<Void,Void>builder().build());
		
	    }
	    catch(Exception e)
	    {
	    	callback.error(e);
	    }
	}

	private static Level getLogLevel(String level) {
		Level returnLevel;

		switch (level) {
		case "INFO":
			returnLevel = Level.INFO;
			break;
		case "DEBUG":
			returnLevel = Level.DEBUG;
			break;
		case "TRACE":
			returnLevel = Level.TRACE;
			break;
		case "WARN":
			returnLevel = Level.WARN;
			break;
		case "ERROR":
			returnLevel = Level.ERROR;
			break;
		case "FATAL":
			returnLevel = Level.FATAL;
			break;
		default:
			returnLevel = Level.INFO;
		}
		return returnLevel;

	}

}
