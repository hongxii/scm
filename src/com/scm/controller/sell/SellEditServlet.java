package com.scm.controller.sell;

import com.scm.dao.CustomerDao;
import com.scm.dao.SOItemDao;
import com.scm.dao.SOMainDao;
import com.scm.model.SOItem;
import com.scm.model.SOMain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by 郭老师 on 2017/5/7.
 */
public class SellEditServlet extends HttpServlet {
    public String sc(String str) throws UnsupportedEncodingException {
        byte[] b = str.getBytes("ISO-8859-1");
        return new String(b, "utf8");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();

        ArrayList al = SOItemDao.getInstance().getAll();//得到所有产品
        ArrayList bl = CustomerDao.getInstance().getAll();//得到所有客户
        session.setAttribute("allproduct",al);
        session.setAttribute("allcustomer",bl);

        //得到主单soid
        String soID = req.getParameter("soID");
        session.setAttribute("somain",SOMainDao.getInstance().getOneById(soID));
        session.setAttribute("soitems", SOItemDao.getInstance().getBySoid(soID));

        resp.sendRedirect(req.getContextPath() + "sell_edit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html; charset=UTF-8");
        HttpSession session = req.getSession();

        ArrayList al = SOItemDao.getInstance().getAll();//得到所有产品
        ArrayList bl = CustomerDao.getInstance().getAll();//得到所有客户
        session.setAttribute("allproduct",al);
        session.setAttribute("allcustomer",bl);

        //得到明细单对象属性
        ArrayList items = new ArrayList();
        String[] codes = req.getParameterValues("spbm");
        String[] nums = req.getParameterValues("ytsl");

        //得到主单属性
        String soID = req.getParameter("soID");
        String createTime = req.getParameter("createTime");//now
        String customerName = req.getParameter("customerName");//转为code
        String account = req.getParameter("account");//${username}

        String tipFee = req.getParameter("tipFee");
        String productTotal = req.getParameter("productTotal");
        String PayType = req.getParameter("PayType");
        String prePayType = req.getParameter("prePayFee");


        for (int i = 0; i < nums.length; i++) {
            items.add(new SOItem(sc(soID), sc(codes[i]), Integer.valueOf(sc(nums[i]))));
        }

        SOMain sm = new SOMain(sc(soID), sc(createTime), sc(customerName), sc(account), Float.valueOf(sc(tipFee)), Float.valueOf(sc(productTotal)), sc(PayType), Float.valueOf(sc(prePayType)));
        System.out.println(SOMainDao.getInstance().updateSOMain(sc(soID),sm,items)?"修改成功":"修改失败");

        resp.sendRedirect(req.getContextPath() + "sell.jsp");
    }
}
