<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



</head>



<body onLoad="supplyReceiveRefundListForm()">


	<div> 공급사 목록 : ${Slist} </div>
	<div> 고객사 목록 : ${Clist} </div>


<!--  	<div  id="mOrderList"> <div v-for:"list2 in list" onclick="orderDetail()">{{list}}</div> </div> 	
	<input v-model:"msg" type="text"/>-->
	
	<div onClick="orderDetail()">주문 목록 : ${WorderList }</div>
	<div id="getWaitList" name="getWaitList"></div>
	<input type="hidden" value="${WorderList}" name="OD_OSCODE" />
	
	
	<div id="refundSpace"></div>
	<div name="exchangeList" onClick="ReceiveExchangeDetail()"> 공급사 교환목록 : ${exchangeList} </div>
	
	
	<div id="detailSpace" style="margin-top:50px;"></div>
	  
	<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/innew.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
</body>
</html>