package com.project.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "LOCATION")
@NamedQueries({
	@NamedQuery(name = "TLocation.findById", query = "SELECT l FROM TLocation l WHERE l.id = :id")
})

public class TLocation {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "UUID")
	private TUser user;

	@Column(name = "LAT")
	private Double lat;

	@Column(name = "LNG")
	private Double lng;

	@Column(name = "INSDATE")
	private Date insdate;


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
	public Date getInsdate() {
		return insdate;
	}


	/**
	 * @param insdate
	 *            the insdate to set
	 */
	public void setInsdate(Date insdate) {
		this.insdate = insdate;
	}
}