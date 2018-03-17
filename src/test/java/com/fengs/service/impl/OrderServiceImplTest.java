package com.fengs.service.impl;

import com.fengs.Dto.OrderDto;
import com.fengs.converter.OrderMasterorOrderDto;
import com.fengs.dataobject.OrderDetail;
import com.fengs.dataobject.OrderMaster;
import com.fengs.enums.OrderMasterEnum;
import com.fengs.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private  final  String BUYER_OPENID="115811578";
    private final  String Order_Id="1520083218883225144";
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
      OrderDto result=  orderService.findOne(Order_Id);

       log.info("查询到result={}", result);
       Assert.assertEquals(Order_Id,result.getOrderId());

   }

    @Test
    public void findList() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<OrderDto> orderDtoPage=orderService.findList(BUYER_OPENID,request);
     //Page<OrderMaster> orderMasters=orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
       //List<OrderDto> orderDtoList= OrderMasterorOrderDto.converter(orderMasters.getContent());
      // return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasters.getTotalElements());
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
       OrderDto orderDto= orderService.findOne(Order_Id);//先找到需要取消的对象
      OrderDto result= orderService.cancel(orderDto);
      Assert.assertEquals(OrderMasterEnum.Cancel.getCode(),result.getOrderStstus());
      //比较订单状态
    }

    @Test
    public void pay() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

}