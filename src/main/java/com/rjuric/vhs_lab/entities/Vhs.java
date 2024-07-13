package com.rjuric.vhs_lab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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

    public Vhs() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public String toString() {
        return "Vhs{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rentals=" + rentals +
                "} " + super.toString();
    }
}
