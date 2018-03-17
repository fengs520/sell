package com.fengs.repository;

import com.fengs.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fengs on 2018/3/1.
 */
public interface OrderDetailRepository  extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderId(String orderId);
    //根据订单表查询订单详情表

}
