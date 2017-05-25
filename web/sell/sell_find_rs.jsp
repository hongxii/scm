<%--
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
    <style type="text/css">
        div.product{
            position: absolute;
            top:2px;
            left: 2px;
            width:100%;
            height: 98%;
            background-color: #fffffe;
        }
    </style>
</head>
<body>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td nowrap class="title1">您的位置：销售管理－－销售单查询--查询结果</td>
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
        <c:forEach items="${sell_f_rs}" var="som" >
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
