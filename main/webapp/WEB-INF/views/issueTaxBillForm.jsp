<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/nsb.js"></script>
</head>
<body onLoad = "spInfo('${spcode}')">

<div id = "spInfo">
	<div id = ${"spcode"} ></div>
	<div id = ${"spname"} ></div>
	<div id = ${"spaddress"} ></div>
	<div id = ${"spbtype"} ></div>
	<div id = ${"spbkind"} ></div>
	<div id = ${"sptel"} ></div>
	<div id = ${"spcorpnum"} ></div>
</div>
<div> <input type = "button" onClick="choiceTaxCL()" value = "고객사찾기"></input></div>
<div id = "clList"></div>
<div id = "clInfo">
	<div id = ${"clcode"} ></div>
	<div id = ${"clname"} ></div>
	<div id = ${"claddress"} ></div>
	<div id = ${"clbtype"} ></div>
	<div id = ${"clbkind"} ></div>
	<div id = ${"clhp"} ></div>
	<div id = ${"clcorpnum"} ></div>
</div>
<div> <input type = "button" onClick="choiceTaxDill()" value = "거래목록"></input></div>
<div id = "dillList"></div>
<div id = "dillInfo">
	<div id = ${"prname"} ></div>
	<div id = ${"rdquantity"} ></div>
	<div id = ${"prprice"} ></div>
	<div id = ${"prtax"} ></div>
</div>

</body>
</html>