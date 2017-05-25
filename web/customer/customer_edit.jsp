<%--
  Created by IntelliJ IDEA.
  User: 郭老师
  Date: 2017/5/4
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>custmoeredit</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="../script/common.js"></script>
</head>
<body>
<form action="/customer/edit" method="post">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td nowrap class="title1">您的位置：销售管理－－客户管理－－修改客户</td>
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
            <td align="center"><input size="15" type="text" name="id" value="${ce.customerCode}" readonly/></td>
            <td align="center"><input size="15" type="text" name="name" value="${ce.name}"/></td>
            <td align="center"><input size="15" type="password" name="pin" value="${ce.password}" /></td>
            <td align="center"><input size="15" type="text" name="contactor" value="${ce.contactor}" /></td>
            <td align="center"><input size="15" type="text" name="address" value="${ce.address}"></td>
            <td align="center"><input size="15" type="text" name="postcode" value="${ce.postcode}" /></td>

            <td align="center"><input size="15" type="text" name="tel" value="${ce.tel}/"></td>
            <td align="center"><input size="15" type="text" name="fax" value="${ce.fax}"></td>
            <td align="center"><input type="submit" value="修改"/></td>
        </tr>
        <tr>
            <td class="title2"></td>
        </tr>

    </table>
</form>

</body>
</html>
