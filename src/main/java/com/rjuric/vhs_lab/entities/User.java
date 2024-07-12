package com.rjuric.vhs_lab.entities;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    public User(String email) {
        super();
        this.email = email;
    }

    public User() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
