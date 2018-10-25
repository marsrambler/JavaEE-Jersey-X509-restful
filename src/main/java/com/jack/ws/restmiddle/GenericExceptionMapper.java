package com.jack.ws.restmiddle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

	@Override
	public Response toResponse(Throwable e) {
		logger.error("Exception:", e);
		RestResponse<String> response = new RestResponse<>();
		response.setType(RestResponse.ResponseType.failure);
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setErrorCode(GenericErrorCode.UnknownError.getCode());
		response.setMessage(e.getMessage());

		return Response.status(response.getHttpStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
