package com.fengs.enums;

import lombok.Getter;

/**
 * Created by fengs on 2018/3/3.
 */
@Getter
public enum ResultEnum {
    //前端页面异常返回
    PRODUCT_NOT_EXIST(999,"商品不存在"),
    PRODUCT_STOCK_ERROR(888,"库存不足"),;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
