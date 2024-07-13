package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.User;

public interface UsersService {
    User create(String email, String password);
    User findByEmail(String email);
}
