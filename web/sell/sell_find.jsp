<%@ page language="java" import="java.util.*,com.scm.model.*,com.scm.dao.*" pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>所有销售单查询</title>
<meta charset="UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../script/common.js" ></script>
<script type="text/javascript" src="../script/po.js" ></script>
<script type="text/javascript" src="../script/vender.js" ></script>
<script type="text/javascript" src="../script/jquery-1.8.1.min.js" ></script>
<link type="text/css" href="../timeutil/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="../timeutil/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script type="text/javascript" src="../timeutil/js/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="../timeutil/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="../timeutil/js/jquery-ui-timepicker-zh-CN.js"></script>

    <script type="text/javascript"  >
        var len = customerTable.rows.length;
        function hiddencDiv() {
            document.getElementById("customerDiv").style.visibility = "hidden";
            //clearTable();
            document.getElementById("formDiv").style.visibility = "visible";
        }
        function showcDiv(){
            document.getElementById("formDiv").style.visibility = "hidden";
            document.getElementById("customerDiv").style.visibility = "visible";

        }

        function choiceCustomer(){
            var l=customerTable.rows.length;
            for (var m=1;m<l-1;m++){
                if(customerTable.rows[m].cells[0].innerHTML=="\u221a") {
                    document.getElementById("customerName").value=customerTable.rows[m].cells[2].innerHTML;
                }
            }
            hiddencDiv();
        }

        /*清空并选择*/
        function selectI(tr) {
            clearcChioce();
            tr.cells[0].innerHTML = "\u221a";
            var tds = tr.cells;
            for (var j = 0; j < tds.length; j += 1) {
                tds[j].style.backgroundColor = "#C1CDD8";
            }
        }
        /*清空*/
        function clearcChioce() {
            var trs = customerTable.rows;
            for (var i = 1; i < trs.length - 1; i += 1) {
                trs[i].cells[0].innerHTML = "";
                var tds = trs[i].cells;
                for (var j = 0; j < tds.length; j += 1) {
                    tds[j].style.backgroundColor = "#fff7e5";
                }
            }
        }
        //明细价格对应总价格
        function setAll() {
            var nums = document.getElementsByName("ytsl");
            var prices = document.getElementsByName("spzl");
            var items=document.getElementsByName("stsl")
            var sums=0;
            for(var i=0;i<nums.length;i++){
                var sum=nums[i].value*prices[i].value;
                items[i].value = sum;
                sums+=sum;
            }
            document.getElementById("total").value = sums;
        }
    </script>
<script type="text/javascript">
    $(function () {
        $(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        });
    });
 </script>
<style type="text/css">
	div.product{
		position: absolute;
		top:2px;
		left: 2px;
		width:100%;
		height: 98%;
		background-color: #fffffe;
	}
	form{
		background-color:#FFF7E5;
		text-align:center;
	}
	form input,select{
		padding:5px;
		margin:3px;
	}
	#venderFrm{
		background-color:#FFF7E5;
		text-align:center;
	}
	#venderFrm input,select{
		padding:5px;
		margin:3px;
	}
</style>

</head>
<div >
</div>
<body onLoad="vender_init()">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：销售管理－－销售单查询</td>
  </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td class="toolbar">&nbsp;</td>
  </tr>
</table>
<div class="formVisiblitly" id="formDiv">
<form action="find" name="mainFrm" method="post">
	销售单编号:<input type="text" name="soID"/><br/>
	开始日期:<input type="text" name="startTime" class="ui_timepicker" value="2017-05-00"><br/>
	截止日期:<input type="text" name="endTime" class="ui_timepicker" value="2017-05-31"><br/>
	客户选择:<input type="text" name="customerName" id="customerName"/>
            <image src='../images/selectDate.gif' onclick="showcDiv()" /><br/>
				<input type="hidden" name="vname"/>
	付款方式:<select name="payType">
				<option></option>
				<option>货到付款</option>
				<option>款到发货</option>
				<option>预付款到发货</option>
			</select><br/>
	处理状态:<select name="status">
				<option></option>
				<option>新订单</option>
				<option>已收货</option>
				<option>已付款</option>
				<option>已了结</option>
				<option>已预付</option>
			</select><br/>
	查询条件:<select name="condition">
				<option>满足上述所有条件</option>
				<option>满足上述任意条件</option>
			</select><br/>
	<input type="submit" value="查询"/>
	<input type="reset" value="重置"/>
	<input type="button" value="关闭" onclick="window.location.href='poSearchAll?currentPage=<%=request.getParameter("currentPage")%>'"/>
</form>
</div>

<!-- div -->
<div id="customerDiv" style="visibility: hidden;" class="customer">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="30px" nowrap="nowrap"="nowrap="nowrap"" class="toolbar">&nbsp;</td>
            <td width="40px" nowrap="nowrap" class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="choiceCustomer()"><img src="../images/confirm.gif">确定</td>
            <td width="20px" nowrap="nowrap" class="toolbar">|</td>
            <td width="40px" nowrap="nowrap" class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="hiddencDiv()"><img src="../images/cancel.gif">取消</td>
            <td align="center" valign="middle" nowrap="nowrap" class="toolbar">&nbsp;
        </tr>
    </table>
    <table width="100%"  border="0" align="center" cellspacing="1" class="a1" id="customerTable">
        <tr>
            <td class="title1">选择</td>
            <td class="title1">客户编号</td>
            <td class="title1">客户名称</td>
            <td class="title1">联系人</td>
        </tr>
        <c:forEach items="${allcustomer}" var="temp" >
            <tr onClick="selectI(this)" onMouseOver="OMO1(event)" onDblClick="choice(1)" align="center">
                <td>&nbsp;</td>
                <td>${temp.customerCode}</td>
                <td>${temp.name}</td>
                <td>${temp.contactor}</td>
            </tr>
        </c:forEach>
        <tr>
            <td class="title2"></td>
        </tr>
    </table>
</div>

</body>
</html>
