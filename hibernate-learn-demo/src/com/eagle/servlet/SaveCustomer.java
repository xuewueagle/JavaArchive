package com.eagle.servlet;

import com.eagle.entity.CstCustomerEntity;
import com.eagle.service.CstCustomerService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *  添加客户的控制器
 */
public class SaveCustomer extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收请求参数
        request.setCharacterEncoding("utf-8");
        Map<String,String[]> map = request.getParameterMap();

        // 封装数据，使用BeanUtils工具，导入jar包
        CstCustomerEntity c = new CstCustomerEntity();
        try {
            // 封装数据
            BeanUtils.populate(c,map);
            // 调用业务层
            new CstCustomerService().saveCustomer(c);
            System.out.println("客户添加成功！");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}
