package com.fengs.repository;

import com.fengs.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fengs on 2018/3/1.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailrepository;
    @Test
    public void save()
    {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1111");
        orderDetail.setOrderId("222222");
        orderDetail.setProductId("33333");
        orderDetail.setProductIcon("http://ccc.jpg");
        orderDetail.setProductName("号吃hi的");
        orderDetail.setProductPrice(new BigDecimal(3));
        orderDetail.setProductQuantity(3);
        orderDetailrepository.save(orderDetail);


    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList=orderDetailrepository.findByOrderId("222222");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}