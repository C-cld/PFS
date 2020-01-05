package com.cyy.finance.model;

import java.util.Date;

/**
 * 资金变动记录列表
 */
public class RecordVO {
    private String id;
    private String productName;
    private String operation;
    private Float confirm;
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        if (operation == 0) {
            this.operation = "买入";
        } else if (operation == 1) {
            this.operation = "卖出";
        } else {
            this.operation = "参数错误";
        }
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
}
