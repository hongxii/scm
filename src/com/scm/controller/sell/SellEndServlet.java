package com.scm.controller.sell;

import com.scm.dao.SOMainDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 郭老师 on 2017/5/7.
 */
public class SellEndServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();

        String pt = req.getParameter("pt");
        String status = req.getParameter("status");
        String id = req.getParameter("id");

        System.out.println(pt+status);

        session.setAttribute("endlist",SOMainDao.getInstance().getEndList(pt,status));
        if (id!=null)       SOMainDao.getInstance().end(id,"操作员");


        resp.sendRedirect(req.getContextPath()+"sell_end.jsp" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
