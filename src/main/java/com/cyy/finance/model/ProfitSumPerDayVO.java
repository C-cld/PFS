package com.cyy.finance.model;

import java.util.Date;

/**
 * 每日总收益曲线图
 */
public class ProfitSumPerDayVO {
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
