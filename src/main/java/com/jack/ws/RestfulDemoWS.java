package com.jack.ws;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jack.ws.restmiddle.GenericErrorCode;
import com.jack.ws.restmiddle.RestAPIAccessException;

/**
 * Options web service
 */
@Path("/demo")
public class RestfulDemoWS {

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_XML)
	public EntityWrapper getEntity() {
		List<String> types = Arrays.asList("abc", "def", "ghi");
		return new EntityWrapper(types);
	}

	@GET
	@Path("/world")
	@Produces(MediaType.APPLICATION_XML)
	public EntityWrapper getError() {
		throw new RestAPIAccessException(GenericErrorCode.UnAuthorized);		
	}

}
