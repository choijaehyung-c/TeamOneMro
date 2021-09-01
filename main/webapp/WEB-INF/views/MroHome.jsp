<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/innew.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/js.js"></script>


</head>



<body onLoad="orderDetail()">


	<div> 공급사 목록 : ${Slist} </div>
	<div> 고객사 목록 : ${Clist} </div>

	<div  id="mOrderList"> <div v-for:"list2 in list" onclick="orderDetail()">{{list}}</div> </div> 	
	<input v-model:"msg" type="text"/>
	<input type="hidden" value="${WorderList}" name="OD_OSCODE" />
	

	  
	
</body>
</html>