package com.fengs.service.impl;

import com.fengs.Dto.CartDto;
import com.fengs.Dto.OrderDto;
import com.fengs.converter.OrderMasterorOrderDto;
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


import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengs on 2018/3/2.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
   private OrderDetailRepository orderDetailrepository;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
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
            orderDetailrepository.save(orderDetail);
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
    public OrderDto findOne(String orderId) {
       OrderMaster orderMaster= orderMasterRepository.findOne(orderId);
       if(orderMaster ==null){
           throw new ShellException(ResultEnum.ORDER_MASTER_NOTNULL);
       }
       //订单详情表 如果不存在就抛出异常
       List<OrderDetail> orderDetailList=orderDetailrepository.findByOrderId(orderId);
       //注意这里
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new ShellException(ResultEnum.ORDER_DETAIL_NOTNULL);
            //抛异常
        }
       OrderDto orderDto=new OrderDto();
       BeanUtils.copyProperties(orderMaster,orderDto);
       //对象的拷贝
        orderDto.setOrderDetailList(orderDetailList);


            ///注意返回
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMasterorOrderDto.converter(orderMasters.getContent());
        return new PageImpl<OrderDto>(orderDtoList, pageable, orderMasters.getTotalElements());
    }
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster=new OrderMaster();

        //判断订单状态
        if(!orderDto.getOrderStstus().equals(OrderMasterEnum.NEW.getCode()))
        {
            log.error("取消订单 订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStstus());
            throw new ShellException(ResultEnum.ORDER_STATUS_NOPACK);
        }
        orderDto.setOrderStstus(OrderMasterEnum.Cancel.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null)
        {
            log.error("订单取消更新失败 oderMaster={}",orderMaster);
            throw new ShellException(ResultEnum.ORDRR_CANNEL_Faild);
        }
        //修改订单状态
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList()))
        {
            log.error("商品详情表内吴商品信息");
            throw new ShellException(ResultEnum.ODER_DETAIL_NOTNULL);
        }
        //如果已经支付,需要退款
        List<CartDto> cartDtoList=orderDto.getOrderDetailList().stream().map(e->new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.insertStock(cartDtoList);
        if(orderDto.getPayStatus().equals(PayStutusEunm.SuccessPay))
        {
            //TODO
        }
        return orderDto;
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
