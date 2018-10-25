package com.jack.ws.restmiddle;

public class RestAPIAccessException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RestResponse<?> response;
	RestErrorCode error;
	
	public RestResponse<?> getResponse() {
		return response;
	}

	public RestAPIAccessException(RestResponse<?> response)
	{
		this.response = response;
	}
	
	public RestAPIAccessException(RestErrorCode error)
	{
		this.error =error;
	}

	public RestErrorCode getError() {
		return error;
	}

	public void setError(RestErrorCode error) {
		this.error = error;
	}
}
