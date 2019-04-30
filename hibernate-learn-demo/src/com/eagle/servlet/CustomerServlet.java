package com.eagle.servlet;

import com.eagle.entity.CstCustomerEntity;
import com.eagle.service.CustomerService;
import com.eagle.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        //req.setCharacterEncoding("UTF-8"); // 因为web.xml中配置了公共的编码过滤器，所以此处不用再处理了，呵呵
        // 获取请求的方法
        String method = req.getParameter("method");

        if(method == null || method.equals("") || method.equals("add")){
            // 添加客户
            this.add(req,resp);
        }else if(method.equals("list")){
            // 客户列表
            this.list(req,resp);
        }else if(method.equals("addSubmit")){
            // 添加客户提交
            this.addSubmit(req,resp);
        }else if(method.equals("edit")){
            // 修改客户
            this.edit(req,resp);
        }else if(method.equals("editSubmit")){
            // 修改客户提交
            this.editSubmit(req,resp);
        }else if(method.equals("delete")){
            // 删除客户
            this.delete(req,resp);
        }
    }

    /**
     * 添加客户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/jsp/customer/add.jsp").forward(req, resp);
    }

    /**
     * 添加客户提交
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 客户信息
        String custName = req.getParameter("custName");
        String custLevel = req.getParameter("custLevel");
        String custSource = req.getParameter("custSource");
        String custLinkman = req.getParameter("custLinkman");
        String custPhone = req.getParameter("custPhone");
        String custMobile = req.getParameter("custMobile");
        String custIndustry = req.getParameter("custIndustry");

        CustomerService customerService = new CustomerServiceImpl();
        CstCustomerEntity c = new CstCustomerEntity();
        c.setCustName(custName);
        c.setCustLevel(custLevel);
        c.setCustMobile(custMobile);
        c.setCustIndustry(custIndustry);
        c.setCustLinkman(custLinkman);
        c.setCustSource(custSource);
        c.setCustPhone(custPhone);

        // 调用service接口
        try{
            customerService.insertCustomer(c);
        }catch (Exception e){
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/error.jsp").forward(req,resp);
        }

        req.getRequestDispatcher("/jsp/success.jsp").forward(req,resp);

    }

    /**
     * 客户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();

        // 条件查询
        CstCustomerEntity query_cstCustomer = new CstCustomerEntity();
        String custName = req.getParameter("custName");
        query_cstCustomer.setCustName(custName);

        // 查询记录总数
        long total = customerService.findCustomerCount(query_cstCustomer);

        // 分页
        // 每页显示个数
        String pageSizeString = req.getParameter("pageSize");
        int pageSize = Integer.parseInt(pageSizeString == null?"15":pageSizeString);

        // 计算总页数
        Double num = Math.ceil(total*1.0/pageSize);
        int totalPage = num.intValue();

        // 当前页码
        String pageString = req.getParameter("page");
        int page = Integer.parseInt(pageString == null||pageString.equals("")?"1":pageString);
        if(page<=0){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }

        // 根据分页参数计算出起始记录下标
        int firstResult = pageSize * (page - 1);

        List<CstCustomerEntity> list = customerService.findCustomerList(query_cstCustomer,firstResult,pageSize);

        // 当前页码
        req.setAttribute("page", page);
        // 总页数
        req.setAttribute("totalPage", totalPage);
        // 每页显示个数
        req.setAttribute("pageSize", pageSize);
        // 总数
        req.setAttribute("total", total);
        // 列表
        req.setAttribute("list", list);
        // 成功
        req.getRequestDispatcher("/jsp/customer/list.jsp").forward(req,resp);


    }

    /**
     * 修改客户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收请求
        Long custId = Long.parseLong(req.getParameter("custId"));

        //调用service查询客户信息
        CustomerService customerService = new CustomerServiceImpl();

        //查询客户基本信息
        CstCustomerEntity cstCustomer = customerService.findCustomerById(custId);

        // 存储到request域在页面展示
        req.setAttribute("customer",cstCustomer);

        // 跳转到编辑页面
        req.getRequestDispatcher("/jsp/customer/edit.jsp").forward(req, resp);
    }

    /**
     * 修改客户提交
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //客户id
        Long custId = Long.parseLong(req.getParameter("custId"));

        // 客户信息
        String custName = req.getParameter("custName");
        String custLevel = req.getParameter("custLevel");
        String custSource = req.getParameter("custSource");
        String custLinkman = req.getParameter("custLinkman");
        String custPhone = req.getParameter("custPhone");
        String custMobile = req.getParameter("custMobile");
        String custIndustry = req.getParameter("custIndustry");

        CustomerService customerService = new CustomerServiceImpl();
        CstCustomerEntity c = new CstCustomerEntity();
        c.setCustName(custName);
        c.setCustLevel(custLevel);
        c.setCustMobile(custMobile);
        c.setCustIndustry(custIndustry);
        c.setCustLinkman(custLinkman);
        c.setCustSource(custSource);
        c.setCustPhone(custPhone);

        try {
            customerService.updateCustomer(custId,c);
        }catch (Exception e){
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/error.jsp").forward(req,resp);
        }

        //req.getRequestDispatcher("/jsp/customer/list.jsp").forward(req,resp);
        resp.sendRedirect("/hibernate_learn_demo/customer?method=list");
    }

    /**
     * 删除客户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //接收请求
        String custId = req.getParameter("custId");

        try{
            //调用service删除客户信息
            CustomerService customerService = new CustomerServiceImpl();
            customerService.deleteCustomer(Long.parseLong(custId));
        }catch (Exception e){
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/error.jsp").forward(req,resp);
        }


        //成功
        resp.sendRedirect("/hibernate_learn_demo/customer?method=list");
    }


}
