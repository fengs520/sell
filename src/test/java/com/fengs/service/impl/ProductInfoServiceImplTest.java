package com.fengs.service.impl;

import com.fengs.dataobject.ProductInfo;
import com.fengs.enums.ProductEnums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;




/**
 * Created by fengs on 2018/1/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void findOne() throws Exception {
       ProductInfo productInfo= productInfoService.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
       List<ProductInfo> productInfos= productInfoService.findUpAll();
       Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest=new PageRequest(0,2);//第0页 2条数据
        Page<ProductInfo> productInfoPage=productInfoService.findAll(pageRequest);
    }

    @Test
    public void save() throws Exception {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId("123457");
            productInfo.setProductName("tttttttt");
                    productInfo.setProductPrice(new BigDecimal(3.2));
            productInfo.setProductStock(100);
            productInfo.setProductDescription("定点");
                    productInfo.setProductIcon("http://xxxxx.jpg");
            productInfo.setProductStatus(ProductEnums.Down.getCode());
            productInfo.setCategoryType(2);//类目编号

            ProductInfo result = productInfoService.save(productInfo);
            Assert.assertNotNull(result);


        }

}