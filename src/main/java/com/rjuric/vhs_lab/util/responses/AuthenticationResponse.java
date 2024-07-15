package com.rjuric.vhs_lab.util.responses;

import com.rjuric.vhs_lab.entities.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    private User user;
    private String token;
}
