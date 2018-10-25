package com.jack.ws.restmiddle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class RestApiExceptionMapper implements ExceptionMapper<RestAPIAccessException> {
	private static final Logger logger = LoggerFactory.getLogger(RestApiExceptionMapper.class);

	@Override
	public Response toResponse(RestAPIAccessException exception) {
		logger.error("Exception:", exception);

		if (exception.getResponse() == null) {
			RestResponse<String> response = new RestResponse<>();
			response.setErrorCode(exception.getError().getCode());
			response.setHttpStatus(HttpStatus.valueOf(exception.getError().getHttpStatus()));
			response.setMessage(exception.getError().getMessage());
			response.setDetailMessage(exception.getError().getDetailedMessage());
			return Response.status(response.getHttpStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
		} else {

			return Response.status(exception.getResponse().getHttpStatus()).entity(exception.getResponse()).type(MediaType.APPLICATION_JSON).build();

		}
	}

}
