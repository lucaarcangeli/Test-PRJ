package com.project.service.location;

import java.util.List;

import javax.ejb.Local;

import com.project.model.TLocation;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Local
public interface LocationEjbLocal {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param latNE
	 *            North-East bound point latitude
	 * @param lngNE
	 *            North-East bound point logitude
	 * @param latSW
	 *            South-West bound point latitude
	 * @param lngSW
	 *            South-West bound point longitude
	 * @param timeFrom
	 *            Time interval start epoch
	 * @param timeTo
	 *            Time interval end epoch
	 * @return List of TUser object entity
	 */
	public List<TLocation> fetchByArea(Double latNE, Double lngNE, Double latSW, Double lngSW, Long timeFrom, Long timeTo);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param location
	 *            TLocation object entity to update
	 */
	public void insertLocation(TLocation location);
}