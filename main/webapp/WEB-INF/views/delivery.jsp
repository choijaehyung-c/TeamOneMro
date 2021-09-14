<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="resources/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>
<body>
<button onclick="callDeliveryList()">리스트 받아오기</button>
	<div id="deliveryVue" style="height: 100%; width: 100%;">
			<div v-for="dl in deliveryList">
				<h6>&nbsp</h6>
				<div>
					<div>배송원:{{dl.dv_name}}</div>
					<div>주문자:{{dl.sp_name}}</div>
					<div>배송지:{{dl.sp_address}}</div>
					<div>전화번호:{{dl.sp_tel}}</div>
					<div>{{dl.ds_name}}</div>

					<button v-if="dl.dl_dscode==1" @click="insertsdcode(dl.dl_code, 2)"
						type="button" class="btn btn-dark">배송시작</button>

					<button v-if="dl.dl_dscode==2" @click="insertsdcode(dl.dl_code, 3)"
						type="button" class="btn btn-dark">배송완료</button>

				</div>
			</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/delivery.js"></script>
</body>
</html>