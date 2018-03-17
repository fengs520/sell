package com.fengs.utils;

import com.fengs.Vo.Result;

/**
 * Created by fengs on 2018/2/9.
 */
public class ResultVoUtil {
    //工具类
    public static Result sucess(Object object)
    {
        Result result=new Result();
        result.setData(object);
        result.setCode(0);
        result.setMsg("成功");
        return  result;
    }
    public static Result success()
    {
        return  success();
    }
    public static Result error(Integer code, String msg)
    {
            Result result=new Result();
            result.setCode(code);
            result.setMsg(msg);
            return  result;
    }
}
