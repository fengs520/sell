package com.fengs.service;

import com.fengs.Dto.CartDto;
import com.fengs.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Created by fengs on 2018/1/28.
 * 商品
 */
public interface ProductInfoService {
    ProductInfo  findOne(String productId);
    //查询在架商品
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);//管理员查询所有商品
    //保存方法
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void insertStock(List<CartDto> cartDtos);
    //减库存
    void descrtStock(List<CartDto> cartDtos);
}
