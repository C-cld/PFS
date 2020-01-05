package com.cyy.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Record {
    private String id;
    private String productId; // 基金代码
    private Float confirm; // 确认金额
    private Date createDate;
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getConfirm() {
        return confirm;
    }

    public void setConfirm(Float confirm) {
        this.confirm = confirm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
