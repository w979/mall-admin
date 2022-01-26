package com.wry.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "init", urlPatterns = { "/init" }, loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		context.setAttribute("base", context.getContextPath());
	}
}
