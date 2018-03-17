package com.fengs.enums;

import lombok.Getter;

/**
 * Created by fengs on 2018/2/28.
 */
@Getter
public enum PayStutusEunm {
    WARINGPay(0,"等待支付"),
    SuccessPay(1,"完成支付");

    private Integer code;
    private String message;

    PayStutusEunm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
