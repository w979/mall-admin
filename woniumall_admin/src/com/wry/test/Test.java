package com.wry.test;

import com.wry.dao.GoodsDao;
import com.wry.dao.OrderDetailDao;
import com.wry.dao.OrdersDao;
import com.wry.domain.Goods;
import com.wry.domain.OrderDetail;
import com.wry.domain.Orders;
import com.wry.utils.MybatisUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParseException {
//        GoodsDao goodsDao = MybatisUtils.getDao(GoodsDao.class);
//        List<Goods> goodsList = goodsDao.getGoods("T",null,null,null,null);
//        System.out.println(goodsList.get(0).getCategory().getName());
//        OrdersDao ordersDao =  MybatisUtils.getDao(OrdersDao.class);
//        List<Orders> ordersList = ordersDao.getOrders(null, new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-19"), null, null);
//        System.out.println(ordersList);
        OrderDetailDao orderDetailDao = MybatisUtils.getDao(OrderDetailDao.class);
        List<OrderDetail> orderDetailList = orderDetailDao.findOrderDetailVById(1);
        System.out.println(orderDetailList);

    }
}
