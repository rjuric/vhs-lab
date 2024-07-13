package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.entities.User;

public interface AuthService {
    User create(String email, String password) throws AuthException;
    User findByEmailAndPassword(String email, String password) throws AuthException;
}
