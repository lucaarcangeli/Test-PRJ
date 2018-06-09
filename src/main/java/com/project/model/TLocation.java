package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Entity
@Table(name = "location")
@NamedQueries({
		@NamedQuery(name = "TLocation.findByGSArea", query = "SELECT l FROM TLocation l WHERE (l.lat BETWEEN :latMin AND :latMax) AND (l.lng BETWEEN :lngMin AND :lngMax) AND (l.insdate BETWEEN :tMin AND :tMax) ORDER BY l.insdate")
})

public class TLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "uid")
	private TUser user;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lng")
	private Double lng;

	@Column(name = "insdate")
	private Long insdate;

	@Column(name = "geocoded")
	private String geocoded;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the user
	 */
	public TUser getUser() {
		return user;
	}


	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(TUser user) {
		this.user = user;
	}


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


	/**
	 * @return the insdate
	 */
	public Long getInsdate() {
		return insdate;
	}


	/**
	 * @param insdate
	 *            the insdate to set
	 */
	public void setInsdate(Long insdate) {
		this.insdate = insdate;
	}


	/**
	 * @return the geocoded
	 */
	public String getGeocoded() {
		return geocoded;
	}


	/**
	 * @param geocoded
	 *            the geocoded to set
	 */
	public void setGeocoded(String geocoded) {
		this.geocoded = geocoded;
	}
}