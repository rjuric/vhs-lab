package com.rjuric.vhs_lab.util.responses;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {
    private String message;
    private List<String> fields;
    private int status;
}
