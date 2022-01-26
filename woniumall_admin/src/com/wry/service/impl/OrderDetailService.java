package com.wry.service.impl;

import com.wry.dao.OrderDetailDao;
import com.wry.domain.OrderDetail;
import com.wry.service.IOrderDetailService;
import com.wry.utils.MybatisUtils;

import java.util.List;

/**
 * 订单明细业务类
 */
public class OrderDetailService implements IOrderDetailService {
    /**
     * 查询订单明细
     * @param orderid
     * @return
     */
    @Override
    public List<OrderDetail> findOrderDetailVById(Integer orderid) {
        OrderDetailDao orderDetailDao = MybatisUtils.getDao(OrderDetailDao.class);
        return orderDetailDao.findOrderDetailVById(orderid);
    }
}
