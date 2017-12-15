package com.Temple.NutriBuddi.UserManagement.validation;

import org.springframework.http.HttpStatus;

public class ValidationResponse {
	
	private String responseBody;
	private HttpStatus status;
	
	public ValidationResponse(String r, HttpStatus s) {
		this.responseBody = r;
		this.status = s;
	}
	
	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String toString(){
		return responseBody + " " + status.toString();
	}
}
