package com.project.test.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.project.test.model.TUser;
import com.project.test.service.location.LocationEjb;
import com.project.test.service.user.UserEjb;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Path("/users")
public class UserController {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@EJB
	private UserEjb userEjb;

	@EJB
	private LocationEjb locationEjb;


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            User specific name
	 */
	@POST
	@Path("username/{username}/toggle")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response toggleActive(@PathParam("username") String username) {
		try {
			TUser user = userEjb.getByUsername(username);

			user.setActive(!user.getActive());
			userEjb.updateUser(user);

			logger.log(Level.INFO, "User active property updated succesfully [USER: " + username + ", ACTIVE: " + user.getActive() + "]");

			return Response.status(Status.OK).entity(user).build();
		} catch (NoResultException nre) {
			return Response.status(404, "User not found").build();
		}
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @return DTO formatter object wrapper
	 */
	@GET
	@Path("byarea")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<TUser> getUsersIntoArea(
			@QueryParam("latNE") Double latNE,
			@QueryParam("lngNE") Double lngNE,
			@QueryParam("latSW") Double latSW,
			@QueryParam("lngSW") Double lngSW,
			@QueryParam("timeFrom") Long timeFrom,
			@QueryParam("timeTo") Long timeTo) {

		List<TUser> collect = locationEjb.fetchByArea(latNE, lngNE, latSW, lngSW, timeFrom, timeTo).stream()
				.map(l -> l.getUser())
				.collect(Collectors.toList());

		logger.log(Level.INFO, "Found totally " + collect.size() + " users: NE=[" + latNE + "," + lngNE + "] SW=[" + latSW + "," + lngSW + "] INTERVAL=[" + timeFrom + "," + timeTo + "]");
		
		return collect;
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            User specific name
	 */
	@GET
	@Path("/{userId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public TUser getByUserId(@PathParam("userId") Long userId) {
		return userEjb.getById(userId);
	}
}