package com.cyy.finance.model;

/**
 * 总收益表格
 */
public class ConfirmSumVO {
    private String productName;
    private Float sumConfirm;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getSumConfirm() {
        return sumConfirm;
    }

    public void setSumConfirm(Float sumConfirm) {
        this.sumConfirm = sumConfirm;
    }
}
