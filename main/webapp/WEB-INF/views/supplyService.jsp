<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/nsb.js"></script>
</head>
<body>

<input type="button" class="teamBtn" id="left" value="${waitOrderlist}" onClick="sendRecode('${waitOrderlist}')" />
</body>
</html>