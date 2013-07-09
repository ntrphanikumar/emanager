package com.akrantha.emanager.registration.rest.exceptions;

public class RestServiceException extends RuntimeException {

    private static final long serialVersionUID = -7398306193060059399L;

    private final ErrorCode errorCode;

    public RestServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
