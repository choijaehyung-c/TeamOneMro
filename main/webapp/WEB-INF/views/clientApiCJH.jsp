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

Client id<input type="text" value="" name="OS_CLCODE"/><br>
Client pwd<input type="text" value="" name="CL_PWD"/><br>


<button onclick="modalOn()">모달온</button>

<button onclick="sendApiData()" style="width:50px; height:50px;" >submit</button>
<div id="vuezone">{{msg}}{{modalOnOff}}


<div class="out-bg" v-if="modalOnOff=='on'">
	<div class="in-bg">
		<li>test vue modal</li>		
	</div>
</div>




</div>




<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/cjh.js"></script>
</body>
</html>