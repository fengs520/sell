package com.fengs.service.impl;

import com.fengs.Dto.CartDto;
import com.fengs.dataobject.ProductInfo;
import com.fengs.enums.ProductEnums;
import com.fengs.enums.ResultEnum;
import com.fengs.exception.ShellException;
import com.fengs.repository.ProductInfoRepository;
import com.fengs.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by fengs on 2018/1/28.
 * //
 */
@Service
public class ProductInfoServiceImpl  implements ProductInfoService{
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        return  repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductEnums.Up.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void insertStock(List<CartDto> cartDtos) {
        for (CartDto dto : cartDtos) {
            ProductInfo productInfo = repository.findOne(dto.getProductId());
            if (productInfo == null)//判断商品是否存在
            {
                throw new ShellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + dto.getProductQuantity();
             repository.save(productInfo);}
    }

    @Transactional
    @Override
    public void descrtStock(List<CartDto> cartDtos) {
        for(CartDto dto:cartDtos)
        {
           ProductInfo productInfo= repository.findOne(dto.getProductId());
           if(productInfo==null)//判断商品是否存在
           {
               throw new ShellException(ResultEnum.PRODUCT_NOT_EXIST);
           }
          Integer result= productInfo.getProductStock()-dto.getProductQuantity();
           //库存-商品数量
            if(result<0)
            {
                //库存不足抛出异常
                throw new ShellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            //把商品下单的数量设置到商品数量上
            repository.save(productInfo);//保存商品信息
        }

    }
}
