package com.rjuric.vhs_lab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column
    private Date createdAt;

    @JsonIgnore
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
}
