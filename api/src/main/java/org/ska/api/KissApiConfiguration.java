package org.ska.api;

import org.ska.business.configuration.KissBusinessConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@Import(KissBusinessConfiguration.class)
@EnableSwagger2
public class KissApiConfiguration {
	
	    @Autowired
	    private Environment env;

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                //.apis(RequestHandlerSelectors.any())
	                .apis(RequestHandlerSelectors.basePackage("org.ska.api.web"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(apiInfo());
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfo(
	                "Contact management REST API",
	                "API",
	                env.getProperty("info.version"),
	                null,
	                new Contact("Pasquale Paola", "https://www.linkedin.com/in/pasqualepaola/", "pasquale.paola@gmail.com"),
	                null, null, Collections.emptyList());
	    }




}

