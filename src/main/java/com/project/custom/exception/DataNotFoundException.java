package com.project.custom.exception;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Provider
public class DataNotFoundException implements ExceptionMapper<NoResultException> {

	/*
	 * (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(NoResultException e) {
		return Response.status(Status.NOT_FOUND.getStatusCode(), "Entity not found").build();
	}
}