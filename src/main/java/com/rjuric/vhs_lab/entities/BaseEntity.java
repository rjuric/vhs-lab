package com.rjuric.vhs_lab.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public BaseEntity() {}

    @PrePersist
    private void setDates() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    private void setUpdatedAt() {
        updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
