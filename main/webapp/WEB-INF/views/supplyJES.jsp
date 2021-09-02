<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/jes.js"></script>
</head>
<body>
	<div>상품리스트 : ${allprlist} </div>
	<div><input type="text" placeholder="상품을 검색해주세요." onClick="" /></div>
	<div>새상품리스트 : ${newprlist} </div>
	<input type="button" value="등록" onClick="getSuReNewProduct()" />
	<input type="button" value="수정" onClick="supplyReModifyCtl()" />
	<input type="button" value="삭제" onClick="supplyReDeleteCtl()" />
</body>
</html>