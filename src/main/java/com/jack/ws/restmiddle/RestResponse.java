package com.jack.ws.restmiddle;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Type of result contained by this response
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResponse<T> {

	public static enum ResponseType {
		success, failure
	};

	HttpStatus httpStatus = HttpStatus.OK;

	/**
	 * Id assigned by StrataSync as outlined in section Request Log This is
	 * present for all success response but for error response it may or may not
	 * be there depending on the error condition. If error is due to business
	 * validation then this field is present.
	 */
	String requestId;

	/**
	 * success or failure
	 */

	ResponseType type;
	/**
	 * <T> Type of result contained by this response
	 */
	T result;
	/**
	 * Available only when type is error
	 */
	Integer errorCode;
	/**
	 * short message description about success or failure
	 */
	String message;
	/**
	 * Detailed message about success or failure.
	 */
	String detailMessage;
	/**
	 * provides suggestions to improve search based on feature
	 */
	String suggestion;

	/* number of results skipped - for pagination usage */
	Integer skip;
	/* requested size of result set - for pagination usage */
	Integer limit;
	/* actual result set size - for pagination usage */
	Integer size;

	public String getServerTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		return sdf.format(new Date());
	}

	public ResponseType getType() {
		return type;
	}

	public void setType(ResponseType type) {
		this.type = type;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public T getResult() {
		return result;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public int getHttpStatus() {
		return httpStatus.value();
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
