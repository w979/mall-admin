package com.wry.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ndktools.javamd5.Mademd5;
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
import java.util.Date;
import java.util.List;

/**
 * 管理员数据处理
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private AdminService adminService = ServiceProxyFactory.getProxy(AdminService.class);
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        switch (opr){
            case "list":
                //显示管理员列表
                findAdmin(request,response);
                break;
            case "checkAccount":
                //根据账号查询管理员信息
                checkAccount(request,response);
                break;
            case "showAdd":
                //跳转到添加页面
                request.getRequestDispatcher("view/admin/admin_add.jsp").forward(request, response);
                break;
            case "add":
                //添加管理员
                saveAdmin(request,response);
                break;
            case "del":
                //删除管理员
                delAdmin(request,response);
                break;
            case "showUpdate":
                //显示要修改的管理员信息
                showUpdate(request,response);
                break;
            case "update":
                updateAdmin(request,response);
                break;
            case "showUpdatePwd":
                //跳转到修改管理员页面
                //获取当前管理员
                AdminInfo adminInfo = (AdminInfo) request.getSession().getAttribute("admin");
                request.setAttribute("admin",adminInfo);
                request.getRequestDispatcher("view/user/pwd_update.jsp").forward(request, response);
                break;
            case "updatePwd":
                //修改密码
                updatePwd(request,response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 管理员列表
     * @param request
     * @param response
     */
    public void findAdmin(HttpServletRequest request, HttpServletResponse response){
        try {
            //获得页码
            Integer pageNo=new Integer(1);
            if(request.getParameter("pageNo")!=null && request.getParameter("pageNo")!=""){
                pageNo=  new Integer(request.getParameter("pageNo"));
            }
            //获得每页条数
            Integer pageSize=new Integer(4);
            if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!=""){
                pageSize=  new Integer(request.getParameter("pageSize"));
            }
            PageHelper.startPage(pageNo,pageSize);
            List<AdminInfo> adminInfoList = adminService.findAdmin();
            //将信息封装到PageInfo中
            PageInfo<AdminInfo> pageInfo = new PageInfo<>(adminInfoList);
            //将数据传到列表页面
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("view/admin/admin_list.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 账号唯一性验证
     * @param request
     * @param response
     */
    public void checkAccount(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取账号
            String account = request.getParameter("account");
            AdminInfo adminInfo = adminService.getAdminByAccount(account);
            ResponseResult<Object> responseResult = null;
            if (adminInfo == null){
                //向页面发送成功标志
                responseResult = new ResponseResult<>(200,"OK");
            }else {
                responseResult = new ResponseResult<>(500,"账号已存在！");
            }
            //转为json
            response.getWriter().write(JSON.toJSONString(responseResult));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 管理员添加
     * @param request
     * @param response
     */
    public void saveAdmin(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取页面输入
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            Integer role = new Integer(request.getParameter("role"));
            Integer status = new Integer(request.getParameter("status"));
            //封装到对象中
            AdminInfo adminInfo = new AdminInfo();
            adminInfo.setAccount(account);
            //密码加密
            adminInfo.setPassword(new Mademd5().toMd5(password));
            adminInfo.setRole(role);
            adminInfo.setLastlogintime(new Date());
            adminInfo.setLastloginip(CusAccessObjectUtil.getIpAddress(request));
            adminInfo.setStatus(status);
            //调用方法
            int saveAdmin = adminService.saveAdmin(adminInfo);
            if (saveAdmin > 0){
                //添加成功，刷新列表
                request.getSession().setAttribute("msg", "添加成功");
                response.sendRedirect("admin?opr=list");
            }else {
                //添加失败
                request.setAttribute("msg", "添加失败");
                //回显
                request.setAttribute("adminInfo", adminInfo);
                request.getRequestDispatcher("view/admin/admin_add.jsp").forward(request, response);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除
     * @param request
     * @param response
     */
    public void delAdmin(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取当前要修改的管理员账号
            String account = request.getParameter("account");
            AdminInfo adminInfo = adminService.getAdminByAccount(account);
            //设置锁定状态
             adminInfo.setStatus(2);
            int delAdminById = adminService.delAdminById(adminInfo);
            if (delAdminById > 0){
                //删除成功,刷新列表
                response.getWriter().write("<script>alert('删除成功！');window.location.href='admin?opr=list'</script>");
            }else {
                //删除失败
                request.setAttribute("msg", "删除失败！");
                request.getRequestDispatcher("view/admin/admin_list.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 显示要修改的管理员信息
     * @param request
     * @param response
     */
    public void showUpdate(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取当前要修改的管理员账号
            String account = request.getParameter("account");
            AdminInfo adminInfo = adminService.getAdminByAccount(account);
            if (adminInfo != null){
                //将信息发送到页面
                request.setAttribute("admin",adminInfo);
                request.getRequestDispatcher("view/admin/admin_update.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改管理员信息
     * @param request
     * @param response
     */
    public void updateAdmin(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取当前要修改的管理员
            String account = request.getParameter("account");
            AdminInfo adminInfo = adminService.getAdminByAccount(account);
           //获取修改后的数据
            String password = request.getParameter("password");
            Integer role = new Integer(request.getParameter("role"));
            Integer status = new Integer(request.getParameter("status"));
            //旧密码
            String jpwd = request.getParameter("jpwd");
            if (jpwd.equals(password)){
                //密码一样不加密用旧密码
                adminInfo.setPassword(jpwd);
            }else {
                adminInfo.setPassword(new Mademd5().toMd5(password));
            }
            adminInfo.setStatus(status);
            adminInfo.setRole(role);
            int updateAdmin = adminService.updateAdmin(adminInfo);
            if (updateAdmin > 0){
                request.getSession().setAttribute("msg", "修改成功");
                //修改成功 刷新列表
                response.sendRedirect("admin?opr=list");
            }else {
                //修改失败
                request.setAttribute("msg", "修改失败！");
                request.setAttribute("admin", adminInfo);
                request.getRequestDispatcher("view/admin/admin_update.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改用户密码
     * @param request
     * @param response
     */
    public void updatePwd(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取id和密码
            Integer id = new Integer(request.getParameter("id"));
            String password = request.getParameter("password");
            int n = adminService.updatePwd(id,new Mademd5().toMd5(password) );
            if (n>0){
                //修改成功
                request.setAttribute("msg", "修改成功!");
            }else {
                request.setAttribute("msg", "修改失败!");
            }
            request.getRequestDispatcher("view/user/pwd_update.jsp").forward(request, response);
        }catch (Exception e) {
            try {
                response.getWriter().write(JSON.toJSONString(new ResponseResult<Object>(500, "删除用户时错误", e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
