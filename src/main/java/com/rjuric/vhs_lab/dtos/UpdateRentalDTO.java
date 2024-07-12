package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public class UpdateRentalDTO {

    @NotNull(message = "id is required")
    @Positive(message = "id needs to be positive")
    private Long id;

    @NotNull(message = "userId is required")
    @Positive(message = "userId needs to be positive")
    private Long userId;

    @NotNull(message = "vhsId needs to be defined")
    @Positive(message = "vhsId needs to be positive")
    private Long vhsId;

    @NotNull(message = "startDate is required")
    @Future(message = "startDate needs to be in the future")
    private Date startDate;

    @NotNull(message = "endDate is required")
    @Future(message = "endDate needs to be in the future")
    private Date endDate;

    public UpdateRentalDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
