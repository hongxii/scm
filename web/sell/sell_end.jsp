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
    <script type="text/javascript">
        /* function test(){
         document.getElementById("con").style.display="block";
         } */
        function skipPOList(){
            var v=document.getElementsByName("skip_poList")[0].value;
            if(/^[1-9][0-9]*$/.test(v))
                window.location.href="poList?currentPage="+(v-1);
            else
                alert("输入格式错误,正确格式为1，10，34等！");
        }
        function skipSearch(){
            var v=document.getElementsByName("skip_search")[0].value;
            if(/^[1-9][0-9]*$/.test(v))
                window.location.href="poSearch?currentPage="+(v-1);
            else
                alert("输入格式错误,正确格式为1，10，34等！");
        }
    </script>
</head>
<body>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td nowrap class="title1">您的位置：工作台面－－销售管理－－销售单新增</td>
    </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>

    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="30px" nowrap class="toolbar">&nbsp;</td>
            <td nowrap class="toolbar"></td>
        </tr>
    </table>

    <table id="headTable" width="100%"  border="0" align="center" class="a1">
        <div class="p" align="left">
            &nbsp;<a href="/sell/end">所有</a></td>&nbsp;|&nbsp;
            <a href="/sell/end?pt=货到付款&status=3">货到付款</a>&nbsp;|&nbsp;
            <a href="/sell/end?pt=款到发货&status=2">款到发货</a>&nbsp;|&nbsp;
            <a href="/sell/end?pt=预付款到发货&status=3">预付款到发货</a>

        </div>
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
        <c:forEach items="${endlist}" var="som" >
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
                <td align="center">${som.status}</td>
                <td align="center"><a href="/sell/end?id=${som.soID }">了结</a></td>
            </tr>
        </c:forEach>
    </table>

    <table width="100%"  border="0" align="center" cellspacing="1">
        <tr>
            <td class="title2"></td>
        </tr>
    </table>
    <br>


<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
</body>
</html>
