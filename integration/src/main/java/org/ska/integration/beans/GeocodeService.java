package org.ska.integration.beans;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 26/10/18.
 */
@Service
public class GeocodeService {

    @Autowired
    private GeoApiContext geoApiContext;

    public GeocodingResult[] geocode(String address) throws InterruptedException, ApiException, IOException {

        GeocodingResult[] results =  GeocodingApi.geocode(geoApiContext,
                address).await();
        return results;

    }
}
