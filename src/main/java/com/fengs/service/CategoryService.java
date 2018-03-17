package com.fengs.service;

import com.fengs.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by fengs on 2018/1/27.
 */
public interface CategoryService {
   ProductCategory  findOne(Integer categoryId);
   //查询单个
   List<ProductCategory> findAll();//查询全部
   List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);//根据类目id查找修改更新
    ProductCategory  save(ProductCategory productCategory);//新增
}
