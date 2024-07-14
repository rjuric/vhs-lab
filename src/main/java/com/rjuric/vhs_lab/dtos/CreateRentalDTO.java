package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public class CreateRentalDTO {

    @NotNull(message = "{userId.notNull}")
    @Positive(message = "{userId.positive}")
    private Long userId;

    @NotNull(message = "{vhsId.notNull}")
    @Positive(message = "{vhsId.positive}")
    private Long vhsId;

    @NotNull(message = "{startDate.notNull}")
    @Future(message = "{startDate.future")
    private Date startDate;

    @NotNull(message = "{endDate.notNull}")
    @Future(message = "{endDate.future}")
    private Date endDate;

    public CreateRentalDTO() {}

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
