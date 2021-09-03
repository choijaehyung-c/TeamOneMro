<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/hsm.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
</head>
<body onLoad="callModifyRequestList('')">

<div id="RequestProductListBox">
</div>


<div id="DetailModal" style="display:none; width:100%; heigth:100%;">
	<div style="width:500px; heigth:500px">
	<div onClick="modalClose()" style="float:right; display:inline;">X</div>
	<div id="ProductDetailInfo"></div>
	</div>
</div>
</body>
</html>