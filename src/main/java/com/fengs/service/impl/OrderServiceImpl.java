package com.fengs.service.impl;

import com.fengs.Dto.CartDto;
import com.fengs.Dto.OrderDto;
import com.fengs.dataobject.OrderDetail;
import com.fengs.dataobject.OrderMaster;
import com.fengs.dataobject.ProductInfo;
import com.fengs.enums.OrderMasterEnum;
import com.fengs.enums.PayStutusEunm;
import com.fengs.enums.ResultEnum;
import com.fengs.exception.ShellException;
import com.fengs.repository.OrderDetailRepository;
import com.fengs.repository.OrderMasterRepository;
import com.fengs.service.OrderService;
import com.fengs.service.ProductInfoService;
import com.fengs.utils.KeyUtils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengs on 2018/3/2.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
   private OrderDetailRepository repository;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    public OrderDto create(OrderDto orderDto) {
        List<CartDto> cartDtolist=new ArrayList<>();
        String orderId= KeyUtils.genrandomKey();
        //订单id
        BigDecimal orderbigDecimal=new BigDecimal(0);
        //设置初始化价格0
        //1查询商品 价格 库存
        //创建类型为oderdetail的实体变量,将找到的订单详情的数据集合赋值给订单详情对象
        for(OrderDetail orderDetail:orderDto.getOrderDetailList())
        {
         ProductInfo productInfo=productInfoService.findOne(orderDetail.getProductId());//根据订单详情表查找商品的id 返回商品信息
            if(productInfo==null)
            {
                //如果找不到商品的信息就抛出异常  商品不存在异常
                throw  new ShellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算总价
            //单价*数量(订单总价)
           orderbigDecimal= productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderbigDecimal);
            orderDetail.setDetailId(KeyUtils.genrandomKey());
            //设置订单详情表的主键   ///订单入库
            orderDetail.setOrderId(orderId);
            //设置订单详情表的订单id
            //属性拷贝
            BeanUtils.copyProperties(productInfo,orderDetail);
            repository.save(orderDetail);
            //保存订单详情表
            ///创建Cartdto对象
            CartDto cartDto=new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDtolist.add(cartDto);//加对象添加到集合

        }

        //写入订单总表和订单详情表
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);//zing表设置订单编号
        orderMaster.setOrderAmount(orderbigDecimal);
        orderMaster.setOrderStstus(OrderMasterEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStutusEunm.WARINGPay.getCode());

        //存入数据库
        orderMasterRepository.save(orderMaster);
        //减库存
        productInfoService.descrtStock(cartDtolist);
        return orderDto;
    }

    @Override
    public OrderDto findone(Integer orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto pay(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }
}
