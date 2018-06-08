package com.project.test.dao.user;

import com.project.test.dao.GenericDao;
import com.project.test.model.TUser;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public interface UserDao extends GenericDao {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param userId
	 *            Unique user ID
	 * @return TUser object entity
	 */
	public TUser getById(Long userId);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            Unique user username
	 * @return TUser object entity
	 */
	public TUser getByUsername(String username);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	public void addUser(TUser user);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	public void updateUser(TUser user);
}