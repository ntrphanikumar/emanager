package com.ntrphanikumar.emanager.restclient;

import com.ntrphanikumar.emanager.dtos.ErrorDTO;

public class RestClientException extends RuntimeException {
    private static final long serialVersionUID = 8011983768733068837L;

    private final ErrorDTO errorDTO;

    public RestClientException(ErrorDTO errorDTO) {
        super(errorDTO.getErrorMessage());
        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

}
