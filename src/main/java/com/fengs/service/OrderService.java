package com.fengs.service;

import com.fengs.Dto.OrderDto;
import com.fengs.dataobject.OrderDetail;
import com.fengs.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by fengs on 2018/3/2.
 */
public interface OrderService {
    //创建订单
   OrderDto   create(OrderDto orderDto);
   //查询单个订单
   OrderDto  findone(Integer orderId);
    //查询订单列表\
    Page<OrderDto>  findList(String buyerOpenid, Pageable pageable);
    //取消订单
  OrderDto  cancel(OrderDto orderDto);
    //支付订单
  OrderDto  pay(OrderDto orderDto);
    //完成订单
  OrderDto  finish(OrderDto orderDto);
}
