package com.project.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Singleton
@Startup
public class DaoFactory {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceUnit(unitName = "jpaPersitenceUnit")
	private EntityManagerFactory emf;


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param daoClazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends GenericDao> T getDaoImpl(Class<T> daoClazz) {
		T newInstance = null;
		try {
			Class<?> daoImplClass = Class.forName(daoClazz.getName() + "Impl");
			Constructor<?> constructor = daoImplClass.getConstructor(EntityManagerFactory.class);
			newInstance = (T) constructor.newInstance(emf);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.log(Level.SEVERE, "Cannot istantiate DAO implementation class", e);
		}
		return newInstance;
	}
}