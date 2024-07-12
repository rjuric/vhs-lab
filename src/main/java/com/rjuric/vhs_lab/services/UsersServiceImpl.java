package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.repository.UsersRepository;
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
    public User findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password).orElse(null);
    }
}
