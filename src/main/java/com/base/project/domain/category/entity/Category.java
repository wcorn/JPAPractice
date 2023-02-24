package com.base.project.domain.category.entity;


import com.base.project.domain.Item.entity.Item;
import com.base.project.global.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Category extends BaseEntity {
    private String name;
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = ALL)
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void setChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    private void setParent(Category child) {
        this.parent = child;
    }
}
