<%--
  Created by IntelliJ IDEA.
  User: 郭老师
  Date: 2017/5/2
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>SCM</title>
  </head>

  <frameset rows="23,10,*" cols="*" frameborder="NO" border="0" framespacing="0" id="controlRv">
    <frame src="title.htm" name="topFrame" scrolling="NO" noresize >
    <frame src="dynamic_bar_h.htm" scrolling="no" name="sidebar_r" noresize>
    <frameset cols="120,10,*" frameborder="NO" border="0" framespacing="0"  id="controlFv">
      <frame src="catalog.htm" name="leftFrame" scrolling="NO" noresize>
      <frame src="dynamic_bar_v.htm" scrolling="no"  name="sidebar_v" noresize>
      <frame src="" name="mainFrame" scrolling="auto">
    </frameset>
  </frameset>
  <noframes></noframes>

</html>
