package com.cyy.finance.model;

import java.util.Date;

public class SumProfitPerDay {
    private Date createDate;
    private float sum;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
