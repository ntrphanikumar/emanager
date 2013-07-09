package com.akrantha.emanager.registration.rest.exceptions;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    AUTHENTICATION_FAILED(401, "Authentication Failed"),
    AUTHORIZATION_FAILED(401, "Authorization Failed"),
    INVALID_USER(401, "User is not activated"),
    MANDATORY_FIELD_EMPTY(400, "A Mandatory Field is Missing"),
    INVALID_FIELD_VALUE(400, "Invalid Field"),
    INVALID_REQUEST(400, "Invalid Request Parameters");

    private final int statusCode;
    private final String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
