package org.ska.business.configuration;

import org.ska.integration.configuration.KissIntegrationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.ska.dao.configuration.KissDaoConfiguration;

@Configuration
@ComponentScan(basePackages = {"org.ska.business"})
@Import({KissDaoConfiguration.class, KissIntegrationConfiguration.class})
public class KissBusinessConfiguration {
	
	

}
