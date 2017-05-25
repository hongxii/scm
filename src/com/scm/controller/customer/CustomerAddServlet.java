package com.scm.controller.customer;

import com.scm.dao.CustomerDao;
import com.scm.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 郭老师 on 2017/5/3.
 */
public class CustomerAddServlet extends HttpServlet {

    //格式转换方法
    public String sc(String str) throws UnsupportedEncodingException {
        byte[] b = str.getBytes("ISO-8859-1");
        return new String(b, "utf8");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        //CustomerDao cd = new CustomerDao();

        String id = sc(req.getParameter("id"));
        String name = sc(req.getParameter("name"));
        String pin = sc(req.getParameter("pin"));
        String contactor = sc(req.getParameter("contactor"));
        String address = sc(req.getParameter("address"));
        String postcode = sc(req.getParameter("postcode"));
        String tel = sc(req.getParameter("tel"));
        String fax = sc(req.getParameter("fax"));

        if (CustomerDao.getInstance().checkId(id)){
            System.out.println("客户编号重复");
            resp.sendRedirect(req.getContextPath()+"customer_add.jsp" );//失败刷新页面
        }else {
            if (CustomerDao.getInstance().addCustomer(new Customer(id,name,pin,contactor,address,postcode,tel,fax,"default")))
                resp.sendRedirect(req.getContextPath()+"customer.jsp" );//成功到主单
            else resp.sendRedirect(req.getContextPath()+"customer_add.jsp" );//失败刷新
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
