package com.fengs.converter;

import com.fengs.Dto.OrderDto;
import com.fengs.dataobject.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengs on 2018/3/17.
 */
public class OrderMasterorOrderDto {
    public static OrderDto converter(OrderMaster orderMaster)
    {
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return  orderDto;
    }
    public static List<OrderDto> converter(List<OrderMaster> orderMasterList)
    {
        return  orderMasterList.stream().map(e->converter(e)).collect(Collectors.toList());

    }
}
