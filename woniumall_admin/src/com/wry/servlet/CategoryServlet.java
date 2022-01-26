package com.wry.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wry.domain.Category;
import com.wry.service.impl.CategoryService;
import com.wry.utils.ResponseResult;
import com.wry.utils.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品类别数据处理
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private CategoryService categoryService = ServiceProxyFactory.getProxy(CategoryService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        switch (opr) {
            case "list":
                //查询所有商品类别
                findCategory(request,response);
                break;
            case "checkName":
                //根据类别名查询商品类别名
                getCategoryByName(request,response);
                break;
            case "showAdd":
                request.getRequestDispatcher("view/category/category_add.jsp").forward(request, response);
                break;
            case "add":
                //添加商品类别
                saveCategory(request,response);
                break;
            case "showUpdate":
                getCategoryById(request,response);
                break;
            case "update":
                updateCategory(request,response);
                break;
            case "del":
                delCategory(request,response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 显示所有商品类别
     * @param request
     * @param response
     */
    public void findCategory(HttpServletRequest request, HttpServletResponse response){
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
            //调用方法
            List<Category> categoryList = categoryService.findCategory();
            //将数据封装到 pageInfo中
            PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
            //发送到页面
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("view/category/category_list.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据商品类别名查询商品类别
     */
    public void getCategoryByName(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取id
           String name = request.getParameter("name");
            Category category = categoryService.getCategoryByName(name);
            ResponseResult<Object> responseResult = null;
            if (category == null){
                //商品类别不存在，可以添加
                responseResult = new ResponseResult<>(200,"OK");
            }else {
                //商品类别存在
                responseResult = new ResponseResult<>(500,"商品类别已存在");
            }
            //转为JSON发送到前台
            response.getWriter().write(JSON.toJSONString(responseResult));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 添加商品
     */
    public void saveCategory(HttpServletRequest request, HttpServletResponse response){
        try {
            Category category = new Category();
            //获取页面输入
            String name = request.getParameter("name");
            String navable = request.getParameter("navable");
            Integer status = new Integer(request.getParameter("status"));
            category.setName(name);
            category.setNavable(navable);
            category.setStatus(status);
            //调用方法
            int saveCategory = categoryService.saveCategory(category);
            if (saveCategory >0){
                //添加成功刷新列表
                response.getWriter().write("<script>alert('添加成功！');window.location.href='category?opr=list'</script>");
               // response.sendRedirect("category?opr=list");
            }else {
                //删除失败
                request.setAttribute("msg", "添加失败！");
                request.getRequestDispatcher("view/category/category_add.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 显示要修改的商品类信息
     * @param request
     * @param response
     */
    public void getCategoryById(HttpServletRequest request, HttpServletResponse response){
        try {
          Integer id = new Integer(request.getParameter("id"));
            Category category = categoryService.getCategoryById(id);
            //将数据显示到页面
            request.setAttribute("category",category);
            request.getRequestDispatcher("view/category/category_update.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改商品类别
     * @param request
     * @param response
     */
    public void updateCategory(HttpServletRequest request, HttpServletResponse response){
        try {
            Integer id = new Integer(request.getParameter("id"));
            //要修改的对象
            Category category = categoryService.getCategoryById(id);
            //修改
            category.setName(request.getParameter("name"));
            category.setNavable(request.getParameter("navable"));
            category.setStatus(new Integer(request.getParameter("status")));
            int n = categoryService.updateCategory(category);
            if (n>0){
                //修改成功
                request.getSession().setAttribute("msg", "修改成功！");
                response.sendRedirect("category?opr=list");
            }else {
                //删除失败
                request.setAttribute("msg", "修改失败！");
                request.getRequestDispatcher("view/category/category_update.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param request
     * @param response
     */
    public void delCategory(HttpServletRequest request, HttpServletResponse response){
        try {
            Integer id = new Integer(request.getParameter("id"));
            //删除的对象
            Category category = categoryService.getCategoryById(id);
            //设置状态
            category.setStatus(2);
            int n = categoryService.delCategory(category);
            if (n >0){
                //删除成功
                request.getSession().setAttribute("msg", "删除成功！");
                response.sendRedirect("category?opr=list");

            }else {
                request.setAttribute("msg", "删除失败！");
                request.getRequestDispatcher("view/category/category_list.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
