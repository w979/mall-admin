package com.wry.service;

import com.wry.domain.OrderDetail;
import com.wry.domain.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IOrdersService {
    //多条件查询
    List<Orders> getOrders(String orderno, Date startTime, Date endTime, Integer status) throws Exception;

    //根据id查询订单信息
    Orders getOrdersById(Integer id) throws Exception;

    //删除订单
    int delOrders(Orders orders) throws Exception;


}
