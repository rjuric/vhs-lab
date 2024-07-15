package com.rjuric.vhs_lab.util.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GenericHttpErrorResponse {

    private String message;
    private int status;
}
