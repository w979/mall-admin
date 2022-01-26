package com.wry.servlet;
import com.alibaba.fastjson.JSON;
import com.wry.utils.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传
 */
@WebServlet("/fileServlet")
@MultipartConfig//该注解表示这个servlet可以接收二进制数据
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置接收到的数据编码格式
        request.setCharacterEncoding("UTF-8");
        // 设置响应内容的字符编码
        response.setContentType("text/html;charset=utf-8");

        // 获得页面中要上传的文件对象
        Part part = request.getPart("uploadFile");
        // 获得上传文件的原文件名
        String disposition = part.getSubmittedFileName();
        System.out.println("原文件名：" + disposition);
        // 获取上传文件后缀名
        String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length());
        System.out.println("文件后缀为:"+suffix);

        // 获取文件输入流
        InputStream is = part.getInputStream();
        // 动态获取服务器的路径，表示将文件上传到服务器的upload目录中
        String serverPath = request.getServletContext().getRealPath("images/goods");

        //判断上传目录是否存在
        File filePath=new File(serverPath);
        if(!filePath.exists()) {
            //upload目录不存在则创建目录
            filePath.mkdir();
        }

        // 创建文件输出对象
        FileOutputStream fos = new FileOutputStream(serverPath + "/" + disposition);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        fos.close();
        is.close();

        File file=new File(serverPath + "/" + disposition);
        if(file.exists()) {
            response.getWriter().write(JSON.toJSONString(new ResponseResult<>(200, "images/goods/"+disposition)));
        }else {
            response.getWriter().write(JSON.toJSONString(new ResponseResult<>(500, "上传失败")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
