package com.fengs.exception;

import com.fengs.enums.ResultEnum;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by fengs on 2018/3/3.
 */
public class ShellException  extends  RuntimeException{
    private Integer code;
     public ShellException(ResultEnum resultEnum)
    {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();

    }

}
