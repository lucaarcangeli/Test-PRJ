package com.project.test.dao.location;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.project.test.dao.GenericDaoImpl;
import com.project.test.model.TLocation;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class LocationDaoImpl extends GenericDaoImpl implements LocationDao {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param emf
	 *            EntityManagerfactory object
	 */
	public LocationDaoImpl(EntityManagerFactory emf) {
		super(emf);
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.test.dao.impl.UserDao#getUsersIntoArea(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<TLocation> fetchByArea(Double latNE, Double lngNE, Double latSW, Double lngSW, Long timeFrom, Long timeTo) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<TLocation> query = em.createNamedQuery("TLocation.findByGSArea", TLocation.class);
			query.setParameter("latMin", latSW);
			query.setParameter("latMax", latNE);
			query.setParameter("lngMin", lngSW);
			query.setParameter("lngMax", lngNE);
			query.setParameter("tMin", timeFrom);
			query.setParameter("tMax", timeTo);
			return query.getResultList();
		} finally {
			em.close();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.test.dao.location.LocationDao#insertLocation(com.project.test.model.TLocation)
	 */
	@Override
	public void insertLocation(TLocation location) {
		EntityManager em = emf.createEntityManager();
		try {
			em.persist(location);
		} finally {
			em.close();
		}
	}
}