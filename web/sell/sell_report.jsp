<%@ page language="java" import="java.util.*,com.scm.model.*,com.scm.dao.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>采购单月度报表查询</title>
<meta charset="UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
    function init(){
	var year=document.getElementsByName("year")[0];
	var op;
	for(var i=2030;i>=2018;i--){
		op=document.createElement("option");
		op.innerHTML=i;
		year.appendChild(op);
	}

        for(var i=2016;i>=2000;i--){
            op=document.createElement("option");
            op.innerHTML=i;
            year.appendChild(op);
        }

	var month=document.getElementsByName("month")[0];
	for(var i=1;i<=12;i++){
		op=document.createElement("option");
		if(i<10)
			op.innerHTML="0"+i;
		else
			op.innerHTML=i;
		month.appendChild(op);
	}
}
 </script>
<style type="text/css">
	form{
		background-color:#FFF7E5;
		text-align:center;
	}
	form select{
		padding:5px;
		margin:3px;
	}
</style>
</head>
<div >
</div>
<body onload="init()">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：月度报表－－销售报表</td>
  </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td class="toolbar">&nbsp;</td>
  </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>
<form action="report" name="mainFrm" method="post">
	采购单年份:<select name="year">
				<option selected>2017</option>
				</select><br/>
	采购单月份:<select name="month" >
				<option></option>
				</select><br/>
	<input type="submit" value="查询"/>
	<input type="button" onclick="window.location.href='sell'" value="关闭"/>
</form>
<!-- div -->
</body>
</html>
