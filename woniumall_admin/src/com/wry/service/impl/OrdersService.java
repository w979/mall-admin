package com.wry.service.impl;

import com.wry.dao.OrderDetailDao;
import com.wry.dao.OrdersDao;
import com.wry.domain.OrderDetail;
import com.wry.domain.Orders;
import com.wry.service.IOrdersService;
import com.wry.utils.MybatisUtils;

import java.util.Date;
import java.util.List;

/**
 * 订单业务层
 */
public class OrdersService implements IOrdersService {
    /**
     * 多条件查询
     * @param orderno
     * @param startTime
     * @param endTime
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> getOrders(String orderno, Date startTime, Date endTime, Integer status) throws Exception{
        OrdersDao ordersDao = MybatisUtils.getDao(OrdersDao.class);
        return ordersDao.getOrders(orderno, startTime, endTime, status);
    }

    /**
     * 根据id查询订单
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Orders getOrdersById(Integer id) throws Exception {
        OrdersDao ordersDao = MybatisUtils.getDao(OrdersDao.class);
        return ordersDao.selectByPrimaryKey(id);
    }

    /**
     * 删除订单
     * @param orders
     * @return
     * @throws Exception
     */
    @Override
    public int delOrders(Orders orders) throws Exception {
        OrdersDao ordersDao = MybatisUtils.getDao(OrdersDao.class);
        return ordersDao.updateByPrimaryKey(orders);
    }

}
