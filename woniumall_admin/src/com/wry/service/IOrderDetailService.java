package com.wry.service;

import com.wry.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderDetailService {

    //根据订单号查询订单明细
    List<OrderDetail> findOrderDetailVById(@Param("orderid") Integer orderid);
}
