package com.fengs.repository;

import com.fengs.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by fengs on 2018/3/1.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final String  OPENID="12345";
    @Test
    public void test()
    {
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("12345");
        orderMaster.setBuyerName("买手2");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerAddress("江苏南通");
        orderMaster.setBuyerPhone("1391********");
        orderMaster.setOrderAmount(new BigDecimal(8.4));
        orderMasterRepository.save(orderMaster);
    }
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest=new PageRequest(0,1);//第0页 长度1
       Page<OrderMaster> result= orderMasterRepository.findByBuyerOpenid(OPENID,pageRequest);
       System.out.println(result.getTotalElements());

    }

}