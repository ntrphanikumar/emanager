package com.ntrphanikumar.emanager.registration.rest.exceptions;

public class ValidationException extends RestServiceException {

    private static final long serialVersionUID = -762662662576345765L;

    public ValidationException(String exceptionMessage, ErrorCode errorCode) {
        super(exceptionMessage, errorCode);
    }

}
