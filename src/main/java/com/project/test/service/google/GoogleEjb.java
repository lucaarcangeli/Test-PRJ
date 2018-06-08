package com.project.test.service.google;

import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Session Bean implementation class GoogleEjb
 */
@Stateless
@LocalBean
public class GoogleEjb implements GoogleEjbLocal {

	/*
	 * (non-Javadoc)
	 * @see com.project.test.service.google.GoogleEjbLocal#reverseGeocoding(java.lang.Double, java.lang.Double, java.lang.String)
	 */
	@Override
	public String reverseGeocoding(Double lat, Double lng, String key) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();
		GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(lat, lng)).await();
		return results[0].formattedAddress;
	}
}