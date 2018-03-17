package com.fengs.Dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by fengs on 2018/3/3.
 */
//购物车
@Data
public class CartDto {
    private String productId;//商品id

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    private Integer productQuantity;//商品数量
}
