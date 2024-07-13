package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);

        return usersService.create(email, hashedPassword);
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = usersService.findByEmail(email);

        boolean isMatching = passwordEncoder.matches(password, user.getPassword());

        if (!isMatching) {
            throw new AuthException("incorrect password");
        }

        return user;
    }
}
