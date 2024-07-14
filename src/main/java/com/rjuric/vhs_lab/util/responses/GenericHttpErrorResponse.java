package com.rjuric.vhs_lab.util.responses;

import lombok.Data;

@Data
public class GenericHttpErrorResponse {

    private String message;
    private int status;
}
