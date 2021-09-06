<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
<meta charset="UTF-8">

<title>Insert title here</title>
 <script src="${pageContext.request.contextPath}/resources/js/nsb.js"></script>
</head>
<body>
	<div>
	<input type = "button" onClick="receiveOrderControll()" value = "발주 접수 관리"></input>
	</div>
	<div id = "orderList"></div>
	<div id = "orderListD"></div>
	
	<input type = "button" onClick="receiveOrderC()" value = "발주 접수 완료 리스트"></input>
	<div id = "orderListC"></div>
	<div id = "orderListCD"></div>
	
	<input type = "button" onClick="delivery()" value = "배송관리"></input>
	<div id = "DLlist"></div>
	
		
</body>
</html>