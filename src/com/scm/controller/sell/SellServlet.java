package com.scm.controller.sell;

import com.scm.dao.SOMainDao;
import com.scm.model.SOMain;
import com.scm.model.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 郭老师 on 2017/5/9.
 */
public class SellServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();

        ArrayList<SOMain> sos = new ArrayList<SOMain>();
        sos = SOMainDao.getInstance().getAll();
        session.setAttribute("soms",sos);
        System.out.println(sos);

        //分页
        String t = req.getParameter("currentPage");
        Page page=new Page("somain");

        if (t==null)        page.setCurrentPage(0);
        else                page.setCurrentPage(Integer.parseInt(t));

        page=SOMainDao.getSomain(page);
        session.setAttribute("sompage", page);

        resp.sendRedirect(req.getContextPath()+"sell.jsp" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
