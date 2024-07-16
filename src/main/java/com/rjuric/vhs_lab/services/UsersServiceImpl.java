package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.repository.UsersRepository;
import com.rjuric.vhs_lab.util.enums.Role;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService  {

    private final UsersRepository repository;

    @Autowired
    public UsersServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(String email, String password, Role role) {
        User user = new User(email, password, role);

        return repository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user.notFound"));
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user.notFound"));
        user.getRentals().size();
        return user;
    }
}
