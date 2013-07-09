package com.akrantha.emanager.registration.rest.exceptionmapper;

import org.codehaus.jackson.map.JsonMappingException;

import com.akrantha.emanager.registration.rest.exceptions.ErrorCode;

public class ExceptionToCodeMapper {

    /**
     * This enumeration maps each Exception from the core to the appropriate
     * FMErrorCode in REST Basic Assumption is a one-one unique mapping between
     * the Exception and the FMErrorCode
     */
    protected enum ExceptionToCode {
        ILLEGAL_ARGUMENT_EXCEPTION(IllegalArgumentException.class, ErrorCode.INVALID_REQUEST), JSON_MAPPING_EXCEPTION(
                JsonMappingException.class, ErrorCode.INVALID_REQUEST);

        private Class<? extends Exception> exceptionClass;
        private ErrorCode fmErrorCode;

        private ExceptionToCode(Class<? extends Exception> exceptionClass, ErrorCode fmErrorCode) {
            this.exceptionClass = exceptionClass;
            this.fmErrorCode = fmErrorCode;
        }

        public Class<? extends Exception> getExceptionClass() {
            return exceptionClass;
        }

        public ErrorCode getFmErrorCode() {
            return fmErrorCode;
        }
    }

    public ErrorCode getErrorCodeByException(Throwable ex) {
        for (ExceptionToCode excepCode : ExceptionToCode.values()) {
            if (ex.getClass().equals(excepCode.getExceptionClass())) {
                return excepCode.getFmErrorCode();
            }
        }
        return ErrorCode.INTERNAL_SERVER_ERROR;
    }
}
