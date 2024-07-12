package com.rjuric.vhs_lab.entities;

import jakarta.persistence.*;

@Entity
@Table(name="vhs")
public class Vhs extends BaseEntity {

    @Column
    private String name;

    @Column
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
}
