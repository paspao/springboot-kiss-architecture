package org.ska.integration.beans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ska.integration.configuration.KissIntegrationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 29/10/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = KissIntegrationConfiguration.class)

public class TestGeocodeService {

    @Autowired
    private GeocodeService geocodeService;

    @Test
    public void emptyTest(){

    }
    /* you need an api key
    @Test
    public void testSimple(){
        try {
            GeocodingResult[] res= geocodeService.geocode("1600 Amphitheatre Parkway Mountain View, CA 94043");
            Assert.assertNotNull(res);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }*/
}
