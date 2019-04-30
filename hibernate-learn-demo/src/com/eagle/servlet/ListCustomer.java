package com.eagle.servlet;

import com.eagle.entity.CstCustomerEntity;
import com.eagle.service.CstCustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 先获取请求的参数
        request.setCharacterEncoding("UTF-8");
        // 获取到客户的名称
        //String custName = request.getParameter("custName");

        // 查询所有的方法的时候，传入进去
        //List<CstCustomerEntity> list = new CstCustomerService().findAll(custName);
        List<CstCustomerEntity> list = new CstCustomerService().findAll();
        // 存入request
        request.setAttribute("list", list);
        // 转发
        request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
