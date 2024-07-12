package com.rjuric.vhs_lab.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Rental extends BaseEntity {
    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private Long userId;

    @Column
    private Long vhsId;

    public Rental(Date startDate, Date endDate, Long userId, Long vhsId) {
        super();

        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.vhsId = vhsId;
    }

    public Rental() {
        super();
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

    public Long getVhsId() {
        return vhsId;
    }

    public void setVhsId(Long vhsId) {
        this.vhsId = vhsId;
    }
}
