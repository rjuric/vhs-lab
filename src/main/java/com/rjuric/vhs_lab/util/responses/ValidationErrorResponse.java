package com.rjuric.vhs_lab.util.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrorResponse {
    private String message;
    private List<String> fields;
    private int status;
}
