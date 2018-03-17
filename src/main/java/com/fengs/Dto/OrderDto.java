package com.fengs.Dto;

import com.fengs.dataobject.OrderDetail;
import com.fengs.enums.OrderMasterEnum;
import com.fengs.enums.PayStutusEunm;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fengs on 2018/3/2.
 */
@Data
public class OrderDto {
    private String orderId;//订单号
    private String  buyerName;//买家姓名
    private String  buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String  buyerOpenid;//微信id
    private BigDecimal orderAmount;//总金额
    private Integer   orderStstus;//= OrderMasterEnum.NEW.getCode() ;//订单状态
    private  Integer  payStatus;//= PayStutusEunm.WARINGPay.getCode();//支付状态
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
