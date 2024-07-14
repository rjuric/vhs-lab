package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);

        return usersService.create(email, hashedPassword);
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = usersService.findByEmail(email);

        boolean isMatching = passwordEncoder.matches(password, user.getPassword());

        if (!isMatching) {
            throw new AuthException("auth.incorrectPassword");
        }

        return user;
    }
}
