package com.ng.tm.web.jersey;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ng.tm.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements
		ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(final BusinessException exception) {
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage())
				.build();
	}

}
