package com.rjuric.vhs_lab.entities;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="vhs")
public class Vhs extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

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

    @Override
    public String toString() {
        return "Vhs{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
