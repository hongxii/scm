<%--
  Created by IntelliJ IDEA.
  User: 郭老师
  Date: 2017/5/3
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addcustomer</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="../script/common.js"></script>
</head>
<body>
<form action="/customer/add" method="post">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td nowrap class="title1">您的位置：销售管理－－客户管理－－添加客户</td>
    </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr><td width="30px" nowrap class="toolbar">&nbsp;</td></tr>
</table>
    <table id="headTable" width="100%"  border="0" align="center" class="a1">
    <tr>
        <td class="title1">客户编号</td>
        <td class="title1">客户名称</td>
        <td class="title1">密码</td>
        <td class="title1">联系人</td>
        <td class="title1">地址</td>
        <td class="title1">邮政编码</td>

        <td class="title1">电话</td>
        <td class="title1">传真</td>
        <td class="title1">操作</td>
    </tr>

    <tr>
        <td align="center"><input size="15" type="text" name="id"></td>
        <td align="center"><input size="15" type="text" name="name"></td>
        <td align="center"><input size="15" type="password" name="pin"></td>
        <td align="center"><input size="15" type="text" name="contactor"></td>
        <td align="center"><input size="15" type="text" name="address"></td>
        <td align="center"><input size="15" type="text" name="postcode"></td>

        <td align="center"><input size="15" type="text" name="tel"></td>
        <td align="center"><input size="15" type="text" name="fax"></td>
        <td align="center"><input type="submit" value="添加"/></td>
    </tr>
    <tr>
        <td class="title2"></td>
    </tr>

</table>
</form>
</body>
</html>
