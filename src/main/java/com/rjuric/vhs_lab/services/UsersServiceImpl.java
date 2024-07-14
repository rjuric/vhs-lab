package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.repository.UsersRepository;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository repository;

    @Override
    public User create(String email, String password) {
        User user = new User(email, password);

        return repository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user.notFound"));
    }
}
