package com.rjuric.vhs_lab.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Rental extends BaseEntity {
    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH })
    @JoinColumn(name = "vhs_id")
    @JsonIgnore
    private Vhs vhs;

    @Column(nullable = true)
    private Date returnedAt;

    @Transient
    private Long vhsId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH })
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Transient
    private Long userId;

    public Rental(Date startDate, Date endDate, Long userId, Long vhsId) {
        super();

        User user = new User();
        user.setId(userId);

        Vhs vhs = new Vhs();
        vhs.setId(vhsId);

        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.vhs = vhs;
    }

    @PostLoad
    @PostPersist
    public void setTransientFields() {
        this.vhsId = (vhs != null) ? vhs.getId() : null;
        this.userId = (user != null) ? user.getId() : null;
    }
}
