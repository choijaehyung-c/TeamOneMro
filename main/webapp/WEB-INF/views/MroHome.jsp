<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



</head>



<body onLoad="supplyReceiveRefundListForm()">
	
	<input type="text" name="word"/><button onClick="supplySearch()"> 검 색 </button>

	<div style="margin-top:50px;"> 공급사 목록 : ${Slist} </div>
	<div> 고객사 목록 : ${Clist} </div>


<!--  	<div  id="mOrderList"> <div v-for:"list2 in list" onclick="orderDetail()">{{list}}</div> </div> 	
	<input v-model:"msg" type="text"/>-->
	
	
	<div id="refundSpace" style="margin-top:50px;"></div>
	<input type="button" name="exchangeList" onClick="ReceiveExchangeList()" value="교환목록 불러오기"/>
	
	
	<div id="detailSpace" style="margin-top:50px;"></div>
	  
	<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/innew.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
</body>
</html>