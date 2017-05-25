package com.scm.controller.sell;

import com.scm.dao.CustomerDao;
import com.scm.dao.SOMainDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 郭老师 on 2017/5/7.
 */
public class SellFindServlet extends HttpServlet {
    public String sc(String str) throws UnsupportedEncodingException {
        byte[] b = str.getBytes("ISO-8859-1");
        return new String(b, "utf8");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        session.setAttribute("allcustomer", CustomerDao.getInstance().getAll());

        String soid = req.getParameter("soID");
        String startTime= req.getParameter("startTime");
        String endTime= req.getParameter("endTime");
        String customerName= req.getParameter("customerName");
        String payType= req.getParameter("payType");
        String status= req.getParameter("status");
        String condition= req.getParameter("condition");

        //if (condition!=null){
        session.setAttribute("sell_f_rs", SOMainDao.getInstance().getFind(sc(soid), sc(startTime), sc(endTime), sc(customerName), sc(payType), sc(status), sc(condition)));

        //}



        resp.sendRedirect(req.getContextPath()+"sell_find_rs.jsp" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
