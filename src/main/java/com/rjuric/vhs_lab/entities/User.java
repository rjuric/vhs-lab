package com.rjuric.vhs_lab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rjuric.vhs_lab.util.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="users")
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Rental> rentals;

    @Enumerated(EnumType.STRING)
    private Role[] roles;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.roles = new Role[]{ role };
    }

    // UserDetails

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles).map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
