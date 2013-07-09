package com.akrantha.emanager.registration.rest.responsebuilder;

import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.MessageContextImpl;
import org.apache.cxf.message.Message;

import com.akrantha.emanager.dtos.ErrorDTO;
import com.akrantha.emanager.registration.rest.exceptions.ErrorCode;

public class ErrorResponseBuilder {

    private final Marshaller errorDTOXMLMarshaller;

    public ErrorResponseBuilder(Marshaller errorDTOXMLMarshaller) throws JAXBException {
        this.errorDTOXMLMarshaller = errorDTOXMLMarshaller;
    }

    public Response buildFor(String reason, ErrorCode errorCode, Message message) {
        return buildFor(reason, errorCode, new MessageContextImpl(message));
    }

    public Response buildFor(String reason, ErrorCode errorCode, MessageContext messageContext) {
        ErrorDTO error = createError(reason, errorCode);
        try {
            ResponseBuilder responseBuilder = Response.status(errorCode.getStatusCode()).type(
                    MediaType.APPLICATION_XML_TYPE);
            StringWriter errorXMLWriter = new StringWriter();
            errorDTOXMLMarshaller.marshal(error, errorXMLWriter);
            return responseBuilder.entity(errorXMLWriter.toString()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ErrorDTO createError(String message, ErrorCode fmErrorCode) {
        ErrorDTO error = new ErrorDTO();
        error.setErrorCode(fmErrorCode.getStatusCode());
        error.setErrorMessage(fmErrorCode.getMessage());
        error.setDescription(message);
        return error;
    }

}
