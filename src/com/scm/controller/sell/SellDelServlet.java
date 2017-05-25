package com.scm.controller.sell;

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
public class SellDelServlet extends HttpServlet {
    public String sc(String str) throws UnsupportedEncodingException {
        byte[] b = str.getBytes("ISO-8859-1");
        return new String(b, "utf8");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();

        String soID = sc(req.getParameter("soID"));
        System.out.println(SOMainDao.getInstance().delSOMain(soID));

        resp.sendRedirect(req.getContextPath()+"sell" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
