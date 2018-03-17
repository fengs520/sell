package com.fengs.enums;

import lombok.Getter;

/**
 * Created by fengs on 2018/1/28.
 */
@Getter
public enum ProductEnums {
    Up(0,"在架"),
    Down(1,"下架");

   private Integer code;
    private String message;

    ProductEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
