package com.project.service.google;

import java.io.IOException;

import javax.ejb.Local;

import com.google.maps.errors.ApiException;

@Local
public interface GoogleEjbLocal {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param lat
	 *            Location latitude point
	 * @param lng
	 *            Location longitude point
	 * @param key
	 *            Google API key
	 * @return Google API reverse geocoding latitude and logitude address
	 * @throws ApiException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String reverseGeocoding(Double lat, Double lng, String key) throws ApiException, InterruptedException, IOException;
}
