<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Client id<input type="text" value="" name="OS_CLCODE"/><br>
Client pwd<input type="text" value="" name="CL_PWD"/><br>



<button onclick="sendApiData()" style="width:50px; height:50px;" >submit</button>
<div id="vuezone">{{msg}}</div>


<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/cjh.js"></script>
</body>
</html>