<%--
  Created by IntelliJ IDEA.
  User: 郭老师
  Date: 2017/5/3
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
    <title>customer</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="../script/common.js"></script>
</head>
<body>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td nowrap class="title1">您的位置：销售管理－－客户管理</td>
    </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr align="left">
        <td width="30px" nowrap class="toolbar">&nbsp;</td>
        <td width="40px" nowrap class="toolbar" align="left"><a href="/customer/customer_add.jsp" ><img src="../images/new.gif">新增</a></td>
        <td width="30px" nowrap class="toolbar">&nbsp;</td>
        <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="window.open('th_detail.htm')"></td>
        <td width="20px" nowrap class="toolbar"></td>
        <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="query()"></td>
        <td nowrap class="toolbar">&nbsp;</td>
        <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"></td>
        <td width="20px" nowrap class="toolbar"></td>
        <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"></td>
        <td width="20px" nowrap class="toolbar"></td>
        <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"></td>
        <td width="20px" nowrap class="toolbar"></td>
        <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"></td>
        <td width="20px" nowrap class="toolbar"></td>
    </tr>
</table>
<table id="headTable" width="100%"  border="0" align="center" class="a1">
    <tr>
        <td class="title1">客户编号</td>
        <td class="title1">客户名称</td>
        <td class="title1">联系人</td>
        <td class="title1">地址</td>
        <td class="title1">邮政编码</td>

        <td class="title1">注册日期</td>
        <td class="title1">电话</td>
        <td class="title1">传真</td>
        <td class="title1">操作</td>
    </tr>
    <c:forEach items="${customer}" var="temp">
        <tr>
            <td align="center"><a href="/customer/edit?id=${temp.customerCode}" target="mainFrame">${temp.customerCode}</a></td>
            <td align="center"><a href="/customer/edit?id=${temp.customerCode}" target="mainFrame">${temp.name}</a></td>
            <td align="center">${temp.contactor}</td>
            <td align="center">${temp.address}</td>
            <td align="center">${temp.postcode}</td>

            <td align="center">${temp.createDate}</td>
            <td align="center">${temp.tel}</td>
            <td align="center">${temp.fax}</td>
            <td align="center"><a href="/customer/edit?id=${temp.customerCode}" target="mainFrame">修改</a> <a href="/customer/del?id=${temp.customerCode}" target="mainFrame">删除</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td class="title2"></td>
    </tr>
</table>

</body>
</html>
