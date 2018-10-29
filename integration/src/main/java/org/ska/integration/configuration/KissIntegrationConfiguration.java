package org.ska.integration.configuration;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 28/09/18.
 */
@Configuration
@ComponentScan(basePackages = {"org.ska.integration.beans"})
public class KissIntegrationConfiguration {


    @Bean
    public GeoApiContext geocoder(){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("Your apikey")
                .build();
        return context;
    }

}
