package com.jack.ws.restmiddle;

/**
 * Marker interface for error codes
 *
 */
public interface RestErrorCode extends ErrorCode{

	public int getHttpStatus();
	public String getDetailedMessage();
}
