package com.cyy.finance.domain;

import java.util.List;
import java.util.Map;

public class Fund {
    private String id; // 基金代码
    private String name; //基金名称
    private int type; // 基金种类
    private List<FundNet> fundNet; //基金净值

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<FundNet> getFundNet() {
        return fundNet;
    }

    public void setFundNet(List<FundNet> fundNet) {
        this.fundNet = fundNet;
    }
}


