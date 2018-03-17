package com.fengs.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fengs.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fengs on 2018/1/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void findOne() throws Exception {
       ProductCategory productCategory= categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
        //希望获得到的值,获得的参数
    }

    @Test
    public void findAll() throws Exception {
     List<ProductCategory> productCategories= categoryService.findAll();
     Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
       List<ProductCategory> productCategories= categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory=new ProductCategory("cccc",10);
        ProductCategory result=categoryService.save(productCategory);
    }

}