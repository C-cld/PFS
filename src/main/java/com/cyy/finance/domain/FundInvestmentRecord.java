package com.cyy.finance.domain;

import java.util.Date;

public class FundInvestmentRecord {
    private String id;
    private String productId; // 基金代码
    private Float share; // 确认份额
    private Float net; // 确认净值
    private Float confirm; // 确认金额
    private Float serviceFee; // 手续费
    private Date createDate;

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

    public Float getShare() {
        return share;
    }

    public void setShare(Float share) {
        this.share = share;
    }

    public Float getNet() {
        return net;
    }

    public void setNet(Float net) {
        this.net = net;
    }

    public Float getConfirm() {
        return confirm;
    }

    public void setConfirm(Float confirm) {
        this.confirm = confirm;
    }

    public Float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
