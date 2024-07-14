package com.rjuric.vhs_lab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="vhs")
public class Vhs extends BaseEntity {

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "vhs")
    private List<Rental> rentals;
}
