package com.eagle.servlet;

import com.eagle.entity.CstCustomerEntity;
import com.eagle.entity.CstLinkmanEntity;
import com.eagle.service.CustomerService;
import com.eagle.service.LinkManService;
import com.eagle.service.impl.CustomerServiceImpl;
import com.eagle.service.impl.LinkManServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LinkManServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 设置编码
        //req.setCharacterEncoding("UTF-8"); // 因为web.xml中配置了公共的编码过滤器，所以此处不用再处理了，呵呵
        // 获取请求的方法
        String method = req.getParameter("method");

        if(method == null || method.equals("") || method.equals("add")){
            // 添加联系人
            this.add(req,resp);
        }else if(method.equals("list")){
            // 联系人列表
            this.list(req,resp);
        }else if(method.equals("addSubmit")){
            // 添加联系人提交
            this.addSubmit(req,resp);
        }else if(method.equals("edit")){
            // 修改联系人
            //this.edit(req,resp);
        }else if(method.equals("editSubmit")){
            // 修改联系人提交
            //this.editSubmit(req,resp);
        }else if(method.equals("delete")){
            // 删除联系人
            //this.delete(req,resp);
        }
    }

    /**
     * 联系人列表
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        // List<CstLinkmanEntity> findLinkManList(CstLinkmanEntity cl, int firstResult, int maxResults)
        LinkManService linkManService = new LinkManServiceImpl();

        // 条件查询
        CstLinkmanEntity query_cstLinkMan = new CstLinkmanEntity();
        String lkmName = req.getParameter("lkmName");
        query_cstLinkMan.setLkmName(lkmName);

        // 查询记录总数
        long total = linkManService.findLinkManCount(query_cstLinkMan);

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

        List<CstLinkmanEntity> list = linkManService.findLinkManList(query_cstLinkMan,firstResult,pageSize);

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
        req.getRequestDispatcher("/jsp/linkman/list.jsp").forward(req,resp);

    }

    /**
     * 添加联系人
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        req.getRequestDispatcher("/jsp/linkman/add.jsp").forward(req,resp);
    }

    /**
     * 提交添加联系人
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addSubmit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

        //联系人信息
        String custId = req.getParameter("custId");//所属客户
        String lkmName = req.getParameter("lkmName");
        String lkmPhone = req.getParameter("lkmPhone");
        String lkmGender = req.getParameter("lkmGender");
        lkmGender = (lkmGender.equals("1")) ? "男" : "女";
        String lkmMobile = req.getParameter("lkmMobile");

        CustomerService customerService = new CustomerServiceImpl();
        CstCustomerEntity cc = customerService.findCustomerById(Long.parseLong(custId));

        // 检查关联客户是否存在
        if(cc == null){
            req.getRequestDispatcher("/jsp/linkman/error.jsp").forward(req, resp);
        }else{

            //添加联系人
            CstLinkmanEntity cstLinkman = new CstLinkmanEntity();
            cstLinkman.setLkmName(lkmName);
            cstLinkman.setLkmPhone(lkmPhone);
            cstLinkman.setLkmGender(lkmGender);
            cstLinkman.setLkmMobile(lkmMobile);
            cstLinkman.setLkmCustId(Long.parseLong(custId));

            //调用新的 service接口
            try {
                LinkManService linkmanService = new LinkManServiceImpl();
                linkmanService.insertLinkMan(cstLinkman);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                //失败
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            }
            //成功
            req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
        }

    }
}
