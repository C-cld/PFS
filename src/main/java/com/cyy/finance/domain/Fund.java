package com.cyy.finance.domain;

public class Fund {
    private String id; // 基金代码
    private String name; //基金名称
    private int type; // 基金种类

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
}
