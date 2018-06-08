package com.project.test.controller.ntt;

import java.io.Serializable;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class LocationNtt implements Serializable {

	private static final long serialVersionUID = 7596930734059857425L;

	private Double lat;
	private Double lng;


	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}


	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}


	/**
	 * @return the lng
	 */
	public Double getLng() {
		return lng;
	}


	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(Double lng) {
		this.lng = lng;
	}
}