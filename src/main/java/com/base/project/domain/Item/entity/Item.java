package com.base.project.domain.Item.entity;

import com.base.project.domain.category.entity.Category;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.entity.BaseEntity;
import com.base.project.global.common.exception.CustomException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@DiscriminatorColumn(name = "dtype")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseEntity {
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * quantity 증가
     *
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * quantity 감소
     *
     * @param quantity
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new CustomException(ErrorCode.ITEM_NOT_EXIST);
        }
        this.stockQuantity = restStock;
    }

}
