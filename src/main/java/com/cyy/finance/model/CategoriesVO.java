package com.cyy.finance.model;

public class CategoriesVO {
    private int id;
    private int parentId;
    private String name;
    private long categoriesConfirm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoriesConfirm() {
        return categoriesConfirm;
    }

    public void setCategoriesConfirm(long categoriesConfirm) {
        this.categoriesConfirm = categoriesConfirm;
    }
}
