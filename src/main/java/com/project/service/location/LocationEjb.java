package com.project.service.location;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.project.dao.DaoFactory;
import com.project.dao.location.LocationDao;
import com.project.model.TLocation;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Stateless
@LocalBean
public class LocationEjb implements LocationEjbLocal {

	@EJB
	private DaoFactory daoFactory;

	private LocationDao dao = null;


	@PostConstruct
	public void postConstruct() {
		dao = daoFactory.getDaoImpl(LocationDao.class);
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.service.location.LocationEjbLocal#getIntoArea(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<TLocation> fetchByArea(Double latNE, Double lngNE, Double latSW, Double lngSW, Long timeFrom, Long timeTo) {
		return dao.fetchByArea(latNE, lngNE, latSW, lngSW, timeFrom, timeTo);
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.service.location.LocationEjbLocal#insertLocation(com.project.model.TLocation)
	 */
	@Override
	public void insertLocation(TLocation location) {
		dao.insertLocation(location);
	}
}