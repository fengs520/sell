package com.fengs.enums;

import lombok.Getter;

/**
 * Created by fengs on 2018/2/28.
 */
@Getter
public enum OrderMasterEnum {
    NEW(0,"新下单"),
    Finished(1,"完成"),
    Cancel(2,"订单取消");

    private Integer code;

    OrderMasterEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;
}
