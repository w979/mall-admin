package com.wry.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wry.domain.Orders;
import com.wry.domain.User;
import com.wry.service.impl.UserService;
import com.wry.utils.ResponseResult;
import com.wry.utils.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService = ServiceProxyFactory.getProxy(UserService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        switch (opr){
            case "list":
                //显示用户列表
                findUser(request,response);
                break;
            case "del":
                delUser(request,response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 显示用户列表
     * @param request
     * @param response
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response){
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
            List<User> userList = userService.findUsers();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            //将数据传到页面
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("view/user/user_list.jsp").forward(request, response);
        }catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "显示用户列表时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 删除用户
     * @param request
     * @param response
     */
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        try {
            Integer id = new Integer(request.getParameter("id"));
            int n = userService.delUser(id);
            if (n>0){
                //删除成功
                request.getSession().setAttribute("msg", "删除成功！");
            }else {
                request.getSession().setAttribute("msg", "删除失败！");
            }
            response.sendRedirect("user?opr=list");
        }catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "删除用户时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
