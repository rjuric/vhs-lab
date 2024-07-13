package com.rjuric.vhs_lab.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

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

    public Rental() {
        super();
    }

    @PostLoad
    @PostPersist
    public void setTransientFields() {
        this.vhsId = (vhs != null) ? vhs.getId() : null;
        this.userId = (user != null) ? user.getId() : null;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Vhs getVhs() {
        return vhs;
    }

    public void setVhs(Vhs vhs) {
        this.vhs = vhs;
    }

    public Long getVhsId() {
        return vhsId;
    }

    public void setVhsId(Long vhsId) {
        this.vhsId = vhsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "user=" + user +
                ", vhs=" + vhs +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                "} " + super.toString();
    }
}
