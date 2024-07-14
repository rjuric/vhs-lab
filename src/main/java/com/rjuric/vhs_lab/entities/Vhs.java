package com.rjuric.vhs_lab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="vhs")
public class Vhs extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "vhs")
    private List<Rental> rentals;

    public Vhs(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }
}
