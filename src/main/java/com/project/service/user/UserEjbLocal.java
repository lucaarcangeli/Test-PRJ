package com.project.service.user;

import javax.ejb.Local;

import com.project.model.TUser;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Local
public interface UserEjbLocal {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            Unique user username
	 * @return TUser object entity
	 */
	public TUser getByUsername(String username);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param user
	 *            TUser object entity to insert
	 */
	public void addUser(TUser user);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param user
	 *            TUser object entity to update
	 */
	public void updateUser(TUser user);
}
