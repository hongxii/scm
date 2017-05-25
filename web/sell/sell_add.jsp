<%@ page import="com.scm.util.OrderNumberUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 郭老师
  Date: 2017/5/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>

    <title>采购单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="../script/common.js" ></script>
    <script type="text/javascript" src="../script/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="../script/productDiv.js" ></script>
    <script type="text/javascript" src="../script/customerDiv.js" ></script>

    <script type="text/javascript"  >
        var len = customerTable.rows.length;
        function hiddencDiv() {
            document.getElementById("customerDiv").style.visibility = "hidden";
            //clearTable();
        }
        function showcDiv(){
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

        /*最低预付款金额校验*/
        function prePay() {
            var PayType = document.getElementById("PayType");
            var prePayFee = document.getElementById("prePayFee");

            if (PayType.value=='货到付款') {alert(1);prePayFee.readonly=false;}
            else if (PayType.value=='款到发货')  {alert(2);prePayFee.readonly=false;}
            else if (PayType.value=='预付款发货') {alert(3);prePayFee.readonly=true;}
        }


    </script>
    <%--AJAX分页用--%>
    <script type="text/javascript">
        //ajax分页
        //当前页
        var currentPage=0;
        function pageSplit(obj){
            if(obj==0){
                currentPage=0;
            }
            currentPage=currentPage+obj;
            $("#currentPage").text(currentPage); //把当前也的数据放在页面上

            $.ajax({
                type:'GET',
                url:'/sell/choiceproduct',
                data:{currentPage:currentPage},
                dataType:'json',
                success:function(data){

                    //清空表格数据
                    $("#context").children().remove();
                    //将数据添加到表格中
                    $.each(data.pageMessage,function(ci,c){ //ci是索引 c是遍历数组的对象

                        $("#context").append("<tr onClick='selectItem(this)' onMouseOver='OMO1(event)' onDblClick='choice("+(ci+1)+")' align='center'><td>&nbsp;</td><td>"+c['productCode']+"</td><td>"+c['name']+"</td><td>"+c['unitName']+"</td><td>"+c['price']+"</td></tr>");

                    });
                    $("#allPage").text(data.allPage);   //修改总页数
                }
            });
        }
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
        div.customer{
            position: absolute;
            top:2px;
            left: 2px;
            width:100%;
            height: 98%;
            background-color: #fffffe;
        }

    </style>

    <script language="javascript">
        function test(){
            document.getElementById("con").style.display="block";
        }
    </script>
</head>
<body onLoad="init()">
<form action="/sell/add" method="post">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td nowrap class="title1">您的位置：工作台面－－销售管理－－销售单添加</td>
    </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>
<form action="" name="mainFrm">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="30px" nowrap class="toolbar">&nbsp;</td>
            <td width="70px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.mx.click()"><img src="../images/add.gif">增加明细</td>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick=""><img src="../images/save.gif">保存</td>
            <%--<td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.bc.click()"><img src="../images/save.gif">保存</td>--%>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.ts.click()"><img src="../images/send.gif">提审</td>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.dy.click()"><img src="../images/search.gif">打印</td>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.cz.click()"><img src="../images/reset.gif">重置</td>
            <td width="20px" nowrap class="toolbar">|</td>
            <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="document.mainFrm.gb.click()"><img src="../images/cancel.gif">关闭</td>
            <td nowrap class="toolbar">&nbsp;</td>
        </tr>
    </table>

    <table id="headTable" width="100%"  border="0" align="center" class="a1">
        <tr align="justify">
            <% String ssoid=OrderNumberUtil.getInstance().getOrderNumber();%>
            <% Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
            %>
            <td>销售单编号</td>
            <td><input type="text" value="<%= "S0"+ssoid %>" size="15" name="soID" />
                <span class="requred_symbol">*</span></td>

            <td>创建时间</td>
            <td><input type="text" size="15" name="createTime" id="time" value="<%= time %>" />
                <span class="requred_symbol">*</span></td>
            <td>客户名称</td>
            <td><input type="text" size="15" onBlur="test()" name="customerName" id="customerName"/>
                <span class='LL'><image src='../images/selectDate.gif' onclick="showcDiv()" /></span>
                <span class="requred_symbol">*</span>
                <div style="border:#3FF 1px solid; height:50px; display:none; position:absolute;  width:120px; background-color:#090;" id="con">
                </div>
            </td>
            <td>创建用户</td>

            <td><input name="account" type="text" size="15" value="${username}S0001" readonly />
                <span class="requred_symbol">*</span>
            </td>
        </tr>
        <tr align="justify">
            <td>附加费用</td>
            <td><input type="text" size="15" name="tipFee" value="0" />
                <span class="requred_symbol">*</span></td>
            <td>产品总价</td>
            <td>
                <input type="text" size="15" name="productTotal" id="total" value="0" readonly/>
            </td>
            <td>付款方式</td>
            <td >
                <select name="PayType" id="PayType" >
                    <option>货到付款</option>
                    <option>预付款发货</option>
                    <option>款到发货</option>
                </select>
            </td>
            <td>最低预付金额</td>
            <td><input name="prePayFee" onclick="prePay()" id="prePayFee" type="text" size="15" value="0" readonly="readonly" /></td>
        </tr>
        <tr>
            <td class="title2"></td>
        </tr>
    </table>
    <table width="100%"  border="0" align="center" cellspacing="1" id="detailTable" onclick="setAll()">
        <tr>
            <td class="toolbar">&nbsp;</td>
            <td class="toolbar"> 商品编码 </td>
            <td class="toolbar"> 商品名称 </td>
            <td class="toolbar"> 产品数量 </td>
            <td class="toolbar">数量单位</td>
            <td class="toolbar">销售单价</td>
            <td class="toolbar">销售明细总价</td>
            <td class="toolbar">&nbsp;</td>
        </tr>
    </table>
    <table width="100%"  border="0" align="center" cellspacing="1">
        <tr>
            <td class="title2"></td>
        </tr>
    </table>
    <br>
    <div align="center">
        <input type="button" id="mx" value="增加明细" onClick="addItem()"/>
        <input type="submit" id="bc" value="保存"/>
        <input type="button" id="dy" value="打印" onClick="window.print()"/>
        <input name="reset"  id="cz" value="重置" type="reset"/>
        <input type="button" id="gb" value="关闭" onClick="window.close()"/>
    </div>

<!-- div 产品 -->

    <div id="productDiv" style="visibility: hidden;" class="product">
        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="30px" nowrap="nowrap"="nowrap="nowrap"" class="toolbar">&nbsp;</td>
                <td width="40px" nowrap="nowrap" class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="choiceAnonymous('productDiv')"><img src="../images/confirm.gif">确定</td>
                <td width="20px" nowrap="nowrap" class="toolbar">|</td>
                <td width="40px" nowrap="nowrap" class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)" onClick="hiddenDiv()"><img src="../images/cancel.gif">取消</td>
                <td align="center" valign="middle" nowrap="nowrap" class="toolbar">&nbsp;</td>
            </tr>
        </table>
        <table width="100%"  border="0" align="center" cellspacing="1" class="a1" id="spxxTable">
            <tr>
                <td class="title1">选择</td>
                <td class="title1">商品编码</td>
                <td class="title1">商品名称</td>
                <td class="title1">商品单位</td>
                <td class="title1">商品单价</td>
            </tr>
            <tbody id="context">
            <c:forEach items="${productpage.pageMessage}" var="temp" varStatus="t">
            <tr onClick="selectItem(this)" onMouseOver="OMO1(event)" onDblClick="choice(${t.index})" align="center">
                <td>&nbsp;</td>
                <td>${temp.productCode}</td>
                <td>${temp.name}</td>
                <td>${temp.unitName}</td>
                <td>${temp.price}</td>
                <td>&nbsp;</td>
            </tr>
            </c:forEach>
            </tbody>
            <tr>
                <td class="title2"></td>
            </tr>
        </table>
        <div align="center" style="margin-top: 20px;"><%--AJAX--%>
            <input type="button"  value="首页" onclick="pageSplit(0)"/>
            <input type="button"  value="上一页" onclick="pageSplit(-1)"/>
            <input type="button"  value="下一页" onclick="pageSplit(1)"/>
            <input type="button"  value="末页" onclick="pageSplit(${productpage.allPage-1})"/>
            共<span id="allPage">${productpage.allPage}</span>页
            第<span id="currentPage">${productpage.currentPage+1}</span>页
        </div>
    </div>

<%--div-客户--%>

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


</form>

<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>

</form>
</body>
</html>
