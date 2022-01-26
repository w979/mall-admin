package com.wry.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wry.domain.Category;
import com.wry.domain.Goods;
import com.wry.service.impl.CategoryService;
import com.wry.service.impl.GoodsService;
import com.wry.utils.RandomCode;
import com.wry.utils.ResponseResult;
import com.wry.utils.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
    private CategoryService categoryService = ServiceProxyFactory.getProxy(CategoryService.class);
    private GoodsService goodsService = ServiceProxyFactory.getProxy(GoodsService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        switch (opr) {
            case "list":
                //多条件查询商品
                findGoods(request, response);
                break;
            case "showAdd":
                //加载添加商品页面
                showAdd(request, response);
                break;
            case "add":
                //添加商品
                saveGoods(request, response);
                break;
            case "showUpdate":
                //加载修改页面数据
                showUpdate(request, response);
                break;
            case "update":
                //修改商品
                updateGoods(request, response);
                break;
            case "downGoods":
                //批量下架商品
                downGoods(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 多条件查询
     *
     * @param request
     * @param response
     */
    public void findGoods(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取条件
            String goodsName = null;
            if (request.getParameter("name") != null && request.getParameter("name") != "") {
                goodsName = request.getParameter("name");
            }
            Integer categoryid = null;
            if (request.getParameter("categoryid") != null && request.getParameter("categoryid") != "") {
                categoryid = new Integer(request.getParameter("categoryid"));
            }
            Integer status = null;
            if (request.getParameter("status") != null && request.getParameter("status") != "") {
                status = new Integer(request.getParameter("status"));
            }
            Integer startstock = null;
            if (request.getParameter("startstock") != null && request.getParameter("startstock") != "") {
                startstock = new Integer(request.getParameter("startstock"));
            }
            Integer endstock = null;
            if (request.getParameter("endstock") != null && request.getParameter("endstock") != "") {
                endstock = new Integer(request.getParameter("endstock"));
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
            List<Goods> goodsList = goodsService.getGoods(goodsName, categoryid, status, startstock, endstock);
            //封装到PageInfo对象中
            PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
            //显示商品类别列表
            List<Category> categoryList = categoryService.getCategoryByStatus(1);
            //将数据发送到页面
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("categoryList", categoryList);
            //回显查询条件
            request.setAttribute("name", goodsName);
            request.setAttribute("categoryid", categoryid);
            request.setAttribute("status", status);
            request.setAttribute("startstock", startstock);
            request.setAttribute("endstock", endstock);
            request.getRequestDispatcher("view/goods/goods_list.jsp").forward(request, response);

        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "显示商品列表时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 加载添加商品页面
     *
     * @param request
     * @param response
     */
    public void showAdd(HttpServletRequest request, HttpServletResponse response) {
        try {
            //显示商品类别列表
            List<Category> categoryList = categoryService.getCategoryByStatus(1);
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("view/goods/goods_add.jsp").forward(request, response);
        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "加载添加商品时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 添加商品
     *
     * @param request
     * @param response
     */
    public void saveGoods(HttpServletRequest request, HttpServletResponse response) {
        try {
            Goods goods = new Goods();
            //获得要添加的商品
            goods.setName(request.getParameter("name"));
            //商品编码
            goods.setGoodsno(RandomCode.getGoodsNo());
            goods.setAuthor(request.getParameter("author"));
            goods.setPublisher(request.getParameter("publisher"));
            //获得出版时间
            String publishtime=request.getParameter("publishtime").replaceAll("/", "-");
            publishtime+=" 00:00:00";
            goods.setPubtime(publishtime);
            goods.setCategoryid(new Integer(request.getParameter("categoryid")));
            goods.setDescription(request.getParameter("description"));
            goods.setImage(request.getParameter("image"));
            goods.setStock(new Integer(request.getParameter("stock")));
            goods.setMarketprice(new BigDecimal(request.getParameter("marketprice")));
            goods.setSalesprice(new BigDecimal(request.getParameter("salesprice")));
            //上架时间
            goods.setUptime(new Date());
            //状态
            goods.setStatus(1);
            //调用添加方法
            int n=goodsService.saveGoods(goods);
            if(n>0){
                request.getSession().setAttribute("msg", "添加商品成功。。。");
                response.sendRedirect("goods?opr=list");
            }else{
                //失败
                request.setAttribute("goods", goods);
                request.getRequestDispatcher("view/goods/goods_add.jsp").forward(request, response);
            }
        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "添加商品时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 加载修改商品页面
     *
     * @param request
     * @param response
     */
    public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取id
            Integer id = new Integer(request.getParameter("id"));
            Goods goods = goodsService.getGoodById(id);
            List<Category> categoryList = categoryService.getCategoryByStatus(1);
            //将数据显示到修改页面
            request.setAttribute("goods", goods);
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("view/goods/goods_update.jsp").forward(request, response);

        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "加载修改商品时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 修改商品
     * @param request
     * @param response
     */
    public void updateGoods(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获得id
            Integer id = new Integer(request.getParameter("id"));
            //获得当前修改信息的对象
            Goods goods = goodsService.getGoodById(id);
            //获取页面的数据并修改
            goods.setName(request.getParameter("name"));
            goods.setAuthor(request.getParameter("author"));
            goods.setPublisher(request.getParameter("publisher"));
            //出版时间
            String publishtime=request.getParameter("publishtime").replaceAll("/", "-");
            publishtime+=" 00:00:00";
            goods.setPubtime(publishtime);
            goods.setCategoryid(new Integer(request.getParameter("categoryid")));
            goods.setDescription(request.getParameter("description"));
            String newimg = request.getParameter("newimg");
            String pic = request.getParameter("pic");
            if (newimg == null || newimg.equals(pic)){
                //用原图片覆盖
                goods.setImage(pic);
            }else {
                //用新图片覆盖
                goods.setImage(newimg);
            }
            goods.setStock(new Integer(request.getParameter("stock")));
            goods.setMarketprice(new BigDecimal(request.getParameter("marketprice")));
            goods.setSalesprice(new BigDecimal(request.getParameter("salesprice")));
            int n = goodsService.UpdateGoods(goods);
            if (n>0){
                //修改成功
                request.getSession().setAttribute("msg", "修改成功！");
                response.sendRedirect("goods?opr=list");
            }else {
                request.setAttribute("msg", "修改失败！");
                //回显
                List<Category> categoryList = categoryService.getCategoryByStatus(1);
                request.setAttribute("goods", goods);
                request.setAttribute("categoryList", categoryList);
                request.getRequestDispatcher("view/goods/goods_update.jsp").forward(request, response);
            }
        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "修改商品时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 批量下架商品
     * @param request
     * @param response
     */
    public void downGoods(HttpServletRequest request, HttpServletResponse response){
        try {
            //获得选中的id
            String id = request.getParameter("id");
            //切割字符串
            String[] ids = id.split(",");
            //将字符串转为集合
            List<String> list = Arrays.asList(ids);
            //调用方法
            int n = goodsService.downGoods(list);
            if(n>0){
                request.getSession().setAttribute("msg", "下架成功");

            }else{
                request.getSession().setAttribute("msg", "下架失败");
            }
            response.sendRedirect("goods?opr=list");
        } catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "下架商品时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
