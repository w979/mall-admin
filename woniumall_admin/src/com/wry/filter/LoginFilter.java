package com.wry.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断是否登录过,没有登录过就跳到登录页面,登录过放行
 */
@WebFilter(filterName = "loginFilter",urlPatterns = {"/view"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //将登录请求和登录页面放行
        // 获得地址栏中的路径
        String path = request.getRequestURI();
        // 判断该路径中是否包含指定信息，包含则放行
        if (path.contains("/login.jsp") || path.contains("/login") || path.contains("/index.jsp") ||
                path.contains("/welcome.jsp")||
                path.contains("/css/") ||path.contains("/js/") || path.contains("/images/")) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }

            //判断用户的session是否存在，存在就登录过，不存在没有登录过
            if (request.getSession().getAttribute("user") != null) {
                //放行
                filterChain.doFilter(request, response);
            } else {
                //重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            }

    }

    @Override
    public void destroy() {

    }
}
