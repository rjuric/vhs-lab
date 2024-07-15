package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.util.enums.Role;

public interface UsersService {
    User create(String email, String password, Role role);
    User findByEmail(String email);
}
