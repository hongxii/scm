package com.scm.controller.sell;

import com.scm.dao.SOMainDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 郭老师 on 2017/5/11.
 */
public class SellReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();

        String year = req.getParameter("year");
        String month= req.getParameter("month");

        session.setAttribute("sell_r_rs", SOMainDao.getInstance().getReport(year, month));
        resp.sendRedirect(req.getContextPath()+"sell_report_rs.jsp" );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
