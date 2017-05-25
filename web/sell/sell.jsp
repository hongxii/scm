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
        <td nowrap class="title1">您的位置：工作台面－－销售管理</td>
    </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>
<form action="" name="mainFrm">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="30px" nowrap class="toolbar">&nbsp;</td>
            <td width="70px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="window.location.href='/sell/add'"><img src="../images/new.gif">新增</td>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="window.location.href='po_search_new.jsp?currentPage=${sompage.currentPage}'"><img src="../images/search.gif">查询</td>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.dy.click()"><img src="../images/reset.gif">打印</td>
            <td nowrap class="toolbar"></td>
        </tr>
    </table>

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
            <td class="title1">操作</td>
        </tr>
        <c:forEach items="${sompage.pageMessage}" var="som" >
            <tr>
                <td align="center">
                    <a href="#" onclick="window.open('/sell/edit?soID=${som.soID}','_blank','width=200，height=100')">${som.soID }</a>
                </td>
                <td align="center">${som.createTime }</td>
                <td align="center">${som.customer.name}</td>
                <td align="center">${som.acount}</td>
                <td align="center">${som.tipFee}</td>

                <td align="center">${som.productTotal }</td>
                <td align="center">${som.soTotal }</td>
                <td align="center">${som.payType }</td>
                <td align="center">${som.prePayFee }</td>
                <td align="center">${som.status}</td>
                <td align="center">
                    <a href="/sell/edit?soID=${som.soID}&currentPage=${sompage.currentPage}">修改</a>
                    <a href="#" onclick="if(confirm('确定删除吗？'))window.location.href='/sell/del?soID=${som.soID }&currentPage=${sompage.currentPage }'">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="p" align="center">

        <input type="button" value="首页" onclick="window.location.href='/sell/sell?currentPage=0'">
        <input type="button" value="上一页" onclick="window.location.href='/sell/sell?currentPage=${sompage.currentPage-1}'">
        <input type="button" value="下一页" onclick="window.location.href='/sell/sell?currentPage=${sompage.currentPage+1}'">
        <input type="button" value="末页" onclick="window.location.href='/sell/sell?currentPage=${sompage.allPage-1}'">
        当前第${sompage.allPage!=0?sompage.currentPage+1:0} 页    共${sompage.allPage} 页

    </div>

    <table width="100%"  border="0" align="center" cellspacing="1">
        <tr>
            <td class="title2"></td>
        </tr>
    </table>
    <br>
    <div align="center">
        <input type="button" id="mx" value="新增" onClick="window.location.href='/sell/add'"/>
        <input type="button" id="bc" value="查询" onClick="window.location.href='po_search_new.jsp?currentPage=${sompage.currentPage}'"/>
        <input type="button" id="dy" value="打印" onClick="window.print()"/>
    </div>
    <!-- div -->
</form>
<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
</body>
</html>
