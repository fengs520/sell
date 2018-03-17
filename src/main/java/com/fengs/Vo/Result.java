package com.fengs.Vo;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
   // private T data;//
    //不指定的类型使用 泛型
    private  Object data;
}
