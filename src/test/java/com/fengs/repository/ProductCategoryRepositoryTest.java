package com.fengs.repository;

import com.fengs.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fengs on 2018/1/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository  productCategoryrepository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryrepository.findOne(1);
        System.out.println(productCategory.toString());
    }
    @Test
    @Transactional
    public void  save()
    {
        //创建用户对象
       // ProductCategory productCategory=new ProductCategory("t11tt",2);
        //设置更新需要设置id
       // productCategory.setCategoryId(2);
        //设置参数
       // productCategory.setCategoryName("test233");
        //productCategory.setCategoryType(3);
        //保存对象
       //ProductCategory result= productCategoryrepository.save(productCategory);
       // Assert.assertNotNull(result);
    }
    @Test
    public void testpri()
    {
        List<Integer> list= Arrays.asList(1,2,3,4);
      List<ProductCategory> result= productCategoryrepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }


}