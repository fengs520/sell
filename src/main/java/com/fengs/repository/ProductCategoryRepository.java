package com.fengs.repository;

import com.fengs.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fengs on 2018/1/23.
 */
public interface  ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);
    //查询类目
}
