package com.wry.servlet;

import com.alibaba.fastjson.JSON;
import com.wry.domain.AdminInfo;
import com.wry.service.impl.AdminService;
import com.wry.utils.CusAccessObjectUtil;
import com.wry.utils.ResponseResult;
import com.wry.utils.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    //获得adminService代理工厂
    private AdminService adminService = ServiceProxyFactory.getProxy(AdminService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        switch (opr){
            case "dologin":
                //管理员登录验证
                getLogin(request,response);
                break;
            case "out":
                //管理员退出
                request.getSession().invalidate();
                //重定向到登录页面
                response.sendRedirect("view/login.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 管理员登录验证
     * @param request
     * @param response
     */
    public void getLogin(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取页面账号和密码
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            //获得本机ip
            String ip = CusAccessObjectUtil.getIpAddress(request);
            //调用登录验证方法 并更新最后登录ip
            AdminInfo adminInfo = adminService.getLogin(account, password,ip);
            //统一消息处理
            ResponseResult<Object> responseResult = null;
            if (adminInfo!=null){
                if (adminInfo.getStatus() == 1){
                    //存储管理员信息
                    request.getSession().setAttribute("admin", adminInfo);
                    //发送状态
                    responseResult = new ResponseResult<>(200,"OK");
                }else {
                    responseResult = new ResponseResult<>(403,"账号已被锁定,请联系管理员！");
                }
            }else {
                //登录失败
                responseResult = new ResponseResult<>(500,"账号或密码错误！");
            }
            //序列化为json
            response.getWriter().write(JSON.toJSONString(responseResult));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
