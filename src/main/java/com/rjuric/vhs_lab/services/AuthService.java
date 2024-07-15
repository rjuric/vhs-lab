package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.util.responses.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse create(String email, String password) throws AuthException;
    AuthenticationResponse findByEmailAndPassword(String email, String password) throws AuthException;
}
