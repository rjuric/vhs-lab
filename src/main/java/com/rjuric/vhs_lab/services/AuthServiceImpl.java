package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.util.enums.Role;
import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.util.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse create(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User savedUser = usersService.create(email, hashedPassword, Role.USER);
        String token = jwtService.generateToken(savedUser);

        return AuthenticationResponse.builder().token(token).user(savedUser).build();
    }

    public AuthenticationResponse findByEmailAndPassword(String email, String password) {
        User user = usersService.findByEmail(email);

        boolean isMatching = passwordEncoder.matches(password, user.getPassword());

        if (!isMatching) {
            throw new AuthException("auth.incorrectPassword");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder().user(user).token(token).build();
    }
}
