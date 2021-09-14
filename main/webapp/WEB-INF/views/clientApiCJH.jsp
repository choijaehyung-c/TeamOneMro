<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	.out-bg{
		width:100%;height:100%;
		background: rgba(0,0,0,0.5);
		position: fixed;
		padding: 20px; 
	}
		
	.in-bg{
		width:100%; background: #fff;
		border-radius:10px;
		padding: 20px; 
	}
	
</style>
</head>
<body>

Client id<input type="text" value="INC10H" name="os_clcode"/><br>
Client pwd<input type="text" value="" name="cl_pwd"/><br>
<button onclick="sendApiData()">전송</button>

<div id="vuezone">

{{msg}}

</div>

<!-- 주문서 발송 받아서  -->


<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/apiTest.js"></script>
</body>
</html>