package com.fengs.service.impl;

import com.fengs.Dto.OrderDto;
import com.fengs.dataobject.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengs on 2018/3/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private  final  String BUYER_OPENID="115811578";
    @Test
    public void create() throws Exception {
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerAddress("江苏南通");
        orderDto.setBuyerName("测试");
        orderDto.setBuyerPhone("123456789777777");
        orderDto.setBuyerOpenid(BUYER_OPENID);
       //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderDto.setOrderDetailList(orderDetailList);
         OrderDto result=orderService.create(orderDto);
         log.info("创建订单 result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findone() throws Exception {
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void pay() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

}