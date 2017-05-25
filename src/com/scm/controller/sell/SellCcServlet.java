package com.scm.controller.sell;


import com.alibaba.fastjson.JSONArray;
import com.scm.dao.SOItemDao;
import com.scm.model.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SellCcServlet extends HttpServlet {
	public String sc(String str) throws UnsupportedEncodingException {
		byte[] b = str.getBytes("ISO-8859-1");
		return new String(b, "utf8");
	}
	/**
	 查询要显示的product数据
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("content-type", "text/html; charset=UTF-8");
		HttpSession session = req.getSession();

		String currentPage=req.getParameter("currentPage");
		Page page=new Page();
		if(currentPage!=null){

            page.setCurrentPage(Integer.parseInt(currentPage));
			page= SOItemDao.getInstance().getProduct(page);
			req.setAttribute("customerpage", page);

			//查询好的数据转换成JSON格式
			String json= JSONArray.toJSONString(page);

			PrintWriter pw = resp.getWriter();
			pw.print(json);
			pw.flush();
			pw.close();

		}else{
			page=SOItemDao.getInstance().getProduct(page);
			req.setAttribute("customerpage", page);
			req.getRequestDispatcher("/sell/sell_add.jsp").forward(req, resp);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
