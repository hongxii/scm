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
 * Created by 郭老师 on 2017/5/4.
 */
public class CustomerEditServlet extends HttpServlet {
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
        Customer customer = CustomerDao.getInstance().getOne(id);
        session.setAttribute("ce",customer);

        String name = req.getParameter("name");
        String pin = req.getParameter("pin");
        String contactor = req.getParameter("contactor");
        String address = req.getParameter("address");
        String postcode = req.getParameter("postcode");
        String tel = req.getParameter("tel");
        String fax = req.getParameter("fax");

        if (name==null){
            resp.sendRedirect(req.getContextPath()+"customer_edit.jsp" );
        }else {
            if (CustomerDao.getInstance().editCustomer(new Customer(id, sc(name), sc(pin), sc(contactor), sc(address), sc(postcode), sc(tel), sc(fax),"default"))) {
                System.out.println("修改成功");
                resp.sendRedirect(req.getContextPath()+"customer.jsp" );
            }else {
                System.out.println("修改失败");
                resp.sendRedirect(req.getContextPath()+"customer_edit.jsp" );
            }
        }
       // resp.sendRedirect(req.getContextPath()+"customer_edit.jsp" );
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
