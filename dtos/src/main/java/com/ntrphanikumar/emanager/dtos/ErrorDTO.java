package com.ntrphanikumar.emanager.dtos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Error")
public class ErrorDTO {

	private int errorCode;

	private String errorMessage;

	private String description;

	@XmlElement(name = "ErrorCode")
	public int getErrorCode() {
		return errorCode;
	}

	public ErrorDTO setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	@XmlElement(name = "ErrorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}

	public ErrorDTO setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	public ErrorDTO setDescription(String description) {
		this.description = description;
		return this;
	}

}
