package com.ntrphanikumar.emanager.registration.rest.exceptionmapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.impl.WebApplicationExceptionMapper;

import com.ntrphanikumar.emanager.registration.rest.exceptions.ErrorCode;
import com.ntrphanikumar.emanager.registration.rest.exceptions.RestServiceException;
import com.ntrphanikumar.emanager.registration.rest.exceptions.responsebuilder.ErrorResponseBuilder;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

    private static final String EXCEPTION_OCCURRED = "Exception Occurred in ";
    private final ErrorResponseBuilder errorResponseBuilder;
    private ExceptionToCodeMapper exceptionToCodeMapper;
    private final WebApplicationExceptionMapper webApplicationExceptionMapper;

    @Context
    private MessageContext messageContext;

    public CustomExceptionMapper(final ErrorResponseBuilder errorResponseBuilder,
            WebApplicationExceptionMapper webApplicationExceptionMapper,
            ExceptionToCodeMapper exceptionToCodeMapper) {
        this.errorResponseBuilder = errorResponseBuilder;
        this.webApplicationExceptionMapper = webApplicationExceptionMapper;
        this.exceptionToCodeMapper = exceptionToCodeMapper;
    }

    public Response toResponse(Throwable throwable) {
        if (throwable instanceof WebApplicationException) {
            return webApplicationExceptionMapper.toResponse((WebApplicationException) throwable);
        } else {
            return buildApplicationExceptionResponse(throwable);
        }
    }

    private Response buildApplicationExceptionResponse(Throwable throwable) {
        ErrorCode errorCode = getErrorCodeFromException(throwable);
        StringBuilder msgBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(throwable.getMessage())) {
            msgBuilder.append(throwable.getMessage());
        } else {
            msgBuilder.append(EXCEPTION_OCCURRED)
                    .append(throwable.getStackTrace()[0].getMethodName()).append(".");
        }
        return errorResponseBuilder.buildFor(msgBuilder.toString(), errorCode, messageContext);
    }

    private ErrorCode getErrorCodeFromException(Throwable throwable) {
        if (throwable instanceof RestServiceException) {
            return ((RestServiceException) throwable).getErrorCode();
        } else {
            return exceptionToCodeMapper.getErrorCodeByException(throwable);
        }
    }

}
