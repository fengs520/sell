package com.fengs.utils;

import org.junit.Test;

import java.util.Random;

/**
 * Created by fengs on 2018/3/3.
 */
public class KeyUtils {
        //生成订单唯一主键    时间+随机数
    //线程同步锁机制
        public static synchronized String genrandomKey()
        {
            //生成随机数
            Random random=new Random();
            Integer n=random.nextInt(900000)+100000;
            return  System.currentTimeMillis()+String.valueOf(n);
            //取系统时间到毫秒  随机数6位转换为String类型 返回



        }
        @Test
        public void test1()
        {
            System.out.println(System.currentTimeMillis());
        }
}
