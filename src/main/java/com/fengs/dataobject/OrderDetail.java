package com.fengs.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fengs on 2018/2/28.
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;//订单详情表主键
    private String orderId; //订单id
    private String productId;//商品的id
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
   // private Date createTime;
   // private Data updateTime;
}
