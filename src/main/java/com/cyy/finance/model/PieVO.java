package com.cyy.finance.model;

import java.util.List;

public class PieVO {
    private List<CategoriesVO> parents;
    private List<CategoriesVO> children;

    public List<CategoriesVO> getParents() {
        return parents;
    }

    public void setParents(List<CategoriesVO> parents) {
        this.parents = parents;
    }

    public List<CategoriesVO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoriesVO> children) {
        this.children = children;
    }
}
