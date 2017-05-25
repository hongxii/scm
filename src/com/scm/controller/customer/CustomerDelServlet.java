package com.scm.controller.customer;

import com.scm.dao.CustomerDao;
import com.scm.dao.SOMainDao;
import com.scm.util.ScmUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 郭老师 on 2017/5/4.
 */
public class CustomerDelServlet extends HttpServlet {
    public String sc(String str) throws UnsupportedEncodingException {
        byte[] b = str.getBytes("ISO-8859-1");
        return new String(b, "utf8");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        //CustomerDao cd = new CustomerDao();
        ScmUtil scm = new ScmUtil();

        String id = sc(req.getParameter("id"));

        if (SOMainDao.getInstance().checkCustomer(id)) {
            System.out.println("客户已有销售单，不可删除");
        } else {
            if (CustomerDao.getInstance().delCustomer(id)){
                //scm.alert(resp,"删除成功");
                resp.sendRedirect(req.getContextPath()+"customer.jsp" );
            }
            else System.out.println("删除失败");
        }
        resp.sendRedirect(req.getContextPath()+"customer.jsp" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
