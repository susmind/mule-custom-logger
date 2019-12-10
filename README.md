# Custom Logging Framework

Custom logging framework for Mule 4 On-Premise 

Presentation Slides can be downloaded from this link: 
https://drive.google.com/open?id=1chHt9MeaV4HFM97-WCVj7T50fGFvxFya

Please change the group id to the org id in Anypoint platform for which you want to publish the asset to Exchange

Please use the below dependency in your project's pom.xml, replace the values of the properties accordingly:

    <!-- Custom Connector -->
    <dependency>
			<groupId>${orgId}</groupId>
			<artifactId>mule-custom-logger</artifactId>
			<version>${custom.logger.version}</version>
			<classifier>mule-plugin</classifier>
		</dependency>

To use the logging framework customize your Log4j2.xml file in your project's resource folder:

Add below section in <Appenders>

		<RollingFile name="JsonFile"
			fileName="${sys:mule.home}${sys:file.separator}logs${sys:file.separator}my-log-test-json.log"
			filePattern="${sys:mule.home}${sys:file.separator}logs${sys:file.separator}my-log-test-json-%d{yyyy-MM-dd}-%i.log"
			append="true">
			<JSONLayout complete="false" compact="true" eventEol="true" objectMessageAsJsonObject="true" />
			<Policies>
				<SizeBasedTriggeringPolicy size="1000 KB" />
			</Policies>
		</RollingFile>

Add the below JNDI in the <Loggers> section the JNDI name should be constant - FILE_JSON_APPENDER :

		<AsyncLogger name="FILE_JSON_APPENDER" level="TRACE"
			additivity="false">
			<AppenderRef ref="JsonFile" />
		</AsyncLogger>


