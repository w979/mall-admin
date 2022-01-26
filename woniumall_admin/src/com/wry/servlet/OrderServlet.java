package com.wry.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wry.domain.OrderDetail;
import com.wry.domain.Orders;
import com.wry.service.impl.OrderDetailService;
import com.wry.service.impl.OrdersService;
import com.wry.utils.ResponseResult;
import com.wry.utils.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrdersService ordersService = ServiceProxyFactory.getProxy(OrdersService.class);
    private OrderDetailService orderDetailService = ServiceProxyFactory.getProxy(OrderDetailService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String opr = request.getParameter("opr");
        switch (opr){
            case "list":
                //获取订单列表 多条件查询
                getOrders(request,response);
                break;
            case "del":
                //删除订单
                delOrders(request,response);
                break;
            case "queryDetail":
                findOrderDetails(request,response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 多条件查询
     */
    public void getOrders(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取查询条件
            String orderno = null;
            if (request.getParameter("orderno") != null && request.getParameter("orderno") != ""){
                orderno =  request.getParameter("orderno");
            }
            Date startTime = null;
            if (request.getParameter("startTime") != null && request.getParameter("startTime") != ""){
                startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(request.getParameter("startTime")+" 00:00:00");
            }

            Date endTime = null;
            if (request.getParameter("endTime") != null && request.getParameter("endTime") != ""){
                endTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(request.getParameter("endTime")+" 23:59:59");
            }
            Integer status = null;
            if (request.getParameter("status") != null && request.getParameter("status") != ""){
                status = new Integer(request.getParameter("status"));
            }
            //分页
            //获得页码
            Integer pageNo = new Integer(1);
            if (request.getParameter("pageNo") != null && request.getParameter("pageNo") != "") {
                pageNo = new Integer(request.getParameter("pageNo"));
            }
            //获得每页条数
            Integer pageSize = new Integer(5);
            if (request.getParameter("pageSize") != null && request.getParameter("pageSize") != "") {
                pageSize = new Integer(request.getParameter("pageSize"));
            }
            PageHelper.startPage(pageNo, pageSize);
            //调用方法
            List<Orders> ordersList = ordersService.getOrders(orderno, startTime, endTime, status);
            //封装
            PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
            //将数据传到页面
            request.setAttribute("pageInfo",pageInfo);
            //回显查询条件
            request.setAttribute("orderno",orderno);
            request.setAttribute("startTime",startTime);
            request.setAttribute("endTime",endTime);
            request.setAttribute("status",status);

            request.getRequestDispatcher("view/order/order_list.jsp").forward(request, response);

        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "显示商品列表时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 删除订单
     * @param request
     * @param response
     */
    public void delOrders(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取要删除的id
            Integer id = new Integer(request.getParameter("id"));
            //获取要删除的对象
            Orders orders = ordersService.getOrdersById(id);
            //修改为删除
            orders.setIsdel("y");
            int n = ordersService.delOrders(orders);
            if (n>0){
                //删除成功
                request.getSession().setAttribute("msg", "删除成功！");
            }else {
                request.getSession().setAttribute("msg", "删除失败！");
            }
            response.sendRedirect("order?opr=list");
        }catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "删除商品时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 查询订单明细
     */
    public void findOrderDetails(HttpServletRequest request, HttpServletResponse response){
        try {
            //分页
            //获得页码
            Integer pageNo = new Integer(1);
            if (request.getParameter("pageNo") != null && request.getParameter("pageNo") != "") {
                pageNo = new Integer(request.getParameter("pageNo"));
            }
            //获得每页条数
            Integer pageSize = new Integer(5);
            if (request.getParameter("pageSize") != null && request.getParameter("pageSize") != "") {
                pageSize = new Integer(request.getParameter("pageSize"));
            }
            PageHelper.startPage(pageNo, pageSize);
            Integer orderid = new Integer(request.getParameter("orderid"));
            List<OrderDetail> orderDetailList = orderDetailService.findOrderDetailVById(orderid);
            PageInfo<OrderDetail> pageInfo = new PageInfo<>(orderDetailList);
            //显示到页面
            request.setAttribute("orderid",orderid);
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("view/order/order_view.jsp").forward(request, response);
        }catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "显示订单信息时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
