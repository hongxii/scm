package com.scm.controller.customer;

import com.scm.dao.CustomerDao;
import com.scm.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 郭老师 on 2017/5/3.
 */
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        //CustomerDao cd = new CustomerDao();
        ArrayList<Customer> al = CustomerDao.getInstance().getAll();
        session.setAttribute("customer",al);

        resp.sendRedirect(req.getContextPath()+"customer.jsp" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
