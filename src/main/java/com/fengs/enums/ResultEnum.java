package com.fengs.enums;

import lombok.Getter;

/**
 * Created by fengs on 2018/3/3.
 */
@Getter
public enum ResultEnum {
    //前端页面异常返回
    PRODUCT_NOT_EXIST(999,"商品不存在"),
    PRODUCT_STOCK_ERROR(888,"库存不足"),
    ORDER_MASTER_NOTNULL(000,"订单不存在"),
    ORDER_DETAIL_NOTNULL(1111,"订单详情表不存在"),
    ORDER_STATUS_NOPACK(2222,"订单状态错误"),
    ORDRR_CANNEL_Faild(333,"订单取消失败"),
    ODER_DETAIL_NOTNULL(444,"订单详情为空");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
