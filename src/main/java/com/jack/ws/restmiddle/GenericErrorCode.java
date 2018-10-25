package com.jack.ws.restmiddle;

public enum GenericErrorCode implements RestErrorCode {

	UnAuthenticated(403, 1001, "Not Authencticated"), 
	UnAuthorized(403, 1002, "Not Authorized"), 
	UnknownError(500, 1003, "UnknownError");

	int httpStatus;
	int code;
	String detailedMessage;

	GenericErrorCode(int httpStatus, int code, String detailedMessage) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.detailedMessage = detailedMessage;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return this.name();
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}

	public void setDetailedMessage(String detailedMessage) {
		this.detailedMessage = detailedMessage;
	}
}
