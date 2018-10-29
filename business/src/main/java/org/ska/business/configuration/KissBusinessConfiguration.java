package org.ska.business.configuration;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.ska.dao.configuration.KissDaoConfiguration;
import org.ska.integration.configuration.KissIntegrationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.ska.business"})
@Import({KissDaoConfiguration.class, KissIntegrationConfiguration.class})
public class KissBusinessConfiguration {
	
	@Bean
    public Mapper dozerMapper(){
        Mapper dozerBeanMapper =  DozerBeanMapperBuilder.buildDefault();
	    return dozerBeanMapper;
    }

}
