<%@ page import="java.util.List" %>
<%@ page import="com.scm.model.SOMain" %><%--
  Created by IntelliJ IDEA.
  User: 郭老师
  Date: 2017/5/5
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
    <title>sell</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../script/common.js" ></script>
    <script type="text/javascript" src="../script/productDiv.js" ></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td nowrap class="title1">您的位置：月度报表－－销售报表--查询结果</td>
    </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>
<form action="" name="mainFrm">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0"><tr></tr></table>

    <table id="headTable" width="100%"  border="0" align="center" class="a1">
        <tr>
            <td class="title1">销售单编号</td>
            <td class="title1">创建时间</td>
            <td class="title1">客户名称</td>
            <td class="title1">创建用户</td>
            <td class="title1">附加费用</td>

            <td class="title1">销售产品总价</td>
            <td class="title1">销售单总价格</td>
            <td class="title1">付款方式</td>
            <td class="title1">最低付款金额</td>
            <td class="title1">处理状态</td>
        </tr>
        <c:forEach items="${sell_r_rs}" var="som" >
            <tr>
                <td align="center">${som.soID }</td>
                <td align="center">${som.createTime }</td>
                <td align="center">${som.customer.name}</td>
                <td align="center">${som.acount}</td>
                <td align="center">${som.tipFee}</td>

                <td align="center">${som.productTotal }</td>
                <td align="center">${som.soTotal }</td>
                <td align="center">${som.payType }</td>
                <td align="center">${som.prePayFee }</td>
                <td align="center">${som.status }</td>
                <td align="center"></td>
            </tr>
        </c:forEach>
        <% List<SOMain> list = (List<SOMain>) session.getAttribute("sell_r_rs");
            int endnum = 0;float sell_m=0;float payed_m=0;
            for (SOMain som:list){
                sell_m += som.getSoTotal();
                if (som.getStatus()==4) endnum += 1;
                if (som.getStatus()==3) payed_m += som.getSoTotal();
            }%>
        <tr>
            <td></td>
            <td>销售单数:</td><td>${sell_r_rs.size()}</td>
            <td>已了结单数:</td><td ><%= endnum%></td>
            <td>销售总金额:</td><td ><%= sell_m%></td>
            <td>付款总金额:</td><td ><%= payed_m%></td>
            <td></td>
        </tr>
    </table>
    <table width="100%"  border="0" align="center" cellspacing="1">
        <tr>
            <td class="title2"></td>
        </tr>
    </table>
</form>
<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
</body>
</html>
