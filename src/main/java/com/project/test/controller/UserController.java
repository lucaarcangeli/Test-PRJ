package com.project.test.controller;

import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.security.enterprise.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.jpa.jpql.Assert;

import com.project.test.controller.dto.AuthTokenDto;
import com.project.test.controller.ntt.LocationNtt;
import com.project.test.controller.ntt.UserNtt;
import com.project.test.model.TLocation;
import com.project.test.model.TUser;
import com.project.test.service.location.LocationEjb;
import com.project.test.service.user.UserEjb;
import com.project.test.util.JwtUtils;

import io.jsonwebtoken.Claims;

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
		TUser user = userEjb.getByUsername(username);

		user.setActive(!user.getActive());
		userEjb.updateUser(user);

		logger.log(Level.INFO, "User active property updated succesfully [USER: " + username + ", ACTIVE: " + user.getActive() + "]");

		return Response.status(Status.OK).entity(user).build();
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

		logger.log(Level.INFO, "Found totally " + collect.size() + " users: NE=[" + latNE + ", " + lngNE + "] SW=[" + latSW + ", " + lngSW + "] INTERVAL=[" + timeFrom + ", " + timeTo + "]");

		return collect;
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            User specific name
	 */
	@POST
	@Path("/auth")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response auth(UserNtt userNtt) {

		try {
			Assert.isNotNull(userNtt.getUsername(), "Username cannot be null or empty");
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST.getStatusCode(), "Invalid or missing service mandatory JSON request fields").build());
		}

		TUser user = userEjb.getByUsername(userNtt.getUsername());

		AuthTokenDto authTokenDto = new AuthTokenDto();
		authTokenDto.setToken(JwtUtils.getAuthToken(user));

		logger.log(Level.INFO, "Auth JWT for user " + userNtt.getUsername() + " generated succesfully");

		return Response.status(Status.OK).entity(authTokenDto).build();
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            User specific name
	 * @throws AuthenticationException
	 */
	@POST
	@Path("/location")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response auth(@HeaderParam("Authorization") String authorization,
			LocationNtt locationNtt) throws AuthenticationException {

		try {
			Assert.isNotNull(locationNtt.getLat(), "User location latitude cannot be null or empty");
			Assert.isNotNull(locationNtt.getLng(), "User location logintude cannot be null or empty");
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST.getStatusCode(), "Invalid or missing service mandatory JSON request fields").build());
		}

		try {
			Assert.isNotNull(authorization, "Authorization header param cannot be null or empty");
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(Status.UNAUTHORIZED.getStatusCode(), "Invalid user authentication schema").build());
		}

		String[] authDetails = authorization.split(" ");
		if (authorization == null || authDetails.length != 2 || !StringUtils.equalsIgnoreCase(authDetails[0], "Bearer")) {
			throw new WebApplicationException(Response.status(Status.UNAUTHORIZED.getStatusCode(), "Invalid user authentication schema").build());
		}

		Long userId = null;
		try {
			Claims parseToken = JwtUtils.parseToken(authDetails[1]);
			userId = parseToken.get("userId", Long.class);
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(Status.UNAUTHORIZED.getStatusCode(), "Invalid user authentication token").build());
		}

		TLocation location = new TLocation();
		location.setUser(userEjb.getById(userId));
		location.setLat(locationNtt.getLat());
		location.setLng(locationNtt.getLng());
		location.setInsdate(Instant.now().toEpochMilli() / 1000);

		locationEjb.insertLocation(location);

		logger.log(Level.INFO, "New location point for authenticated user " + location.getUser().getUsername() + " inserted succesfully LATLNG=[" + locationNtt.getLat() + ", " + locationNtt.getLng() + "]");

		return Response.status(Status.OK).entity("").build();
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
