package com.cyy.finance.dto;

import java.util.Date;

public class ProfitSumPerDay {
    private Date createDate;
    private Float sum;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
