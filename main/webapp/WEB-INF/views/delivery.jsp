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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
        <script>
     var gps_use = null; //gps의 사용가능 여부
   	 var gps_lat = null; // 위도
   	 var gps_lng = null; // 경도
   	 var gps_position; // gps 위치 객체

   	 
   	gps_check();
   	 // gps가 이용가능한지 체크하는 함수이며, 이용가능하다면 show location 함수를 불러온다.
   	 // 만약 작동되지 않는다면 경고창을 띄우고, 에러가 있다면 errorHandler 함수를 불러온다.
   	 // timeout을 통해 시간제한을 둔다.
   	 function gps_check(){
   	     if (navigator.geolocation) {
   	         var options = {timeout:60000};
   	         navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);
   	     } else {
   	         alert("GPS_추적이 불가합니다.");
   	         gps_use = false;
   	     }
   	 }


   	 // gps 이용 가능 시, 위도와 경도를 반환하는 showlocation함수.
   	 function showLocation(position) {
   	     gps_use = true;
   	     gps_lat = position.coords.latitude;
   	     gps_lng = position.coords.longitude;
   	 }


   	 // error발생 시 에러의 종류를 알려주는 함수.
   	 function errorHandler(error) {
   	     if(error.code == 1) {
   	         alert("접근차단");
   	     } else if( err.code == 2) {
   	         alert("위치를 반환할 수 없습니다.");
   	     }
   	     gps_use = false;
   	 }
   	 

        </script>
<style type="text/css">
@media screen and (max-width: 768px) {
 body { background-color: lightgreen;width:300px;height:200px; }
 
  
 }
</style>

</head>
<body onLoad="callDeliveryList('')">
	<div id="map" style="width:600px;height:400px; margin:auto; margin-top:10px;"></div>

	
	
	<div id="deliveryVue" style="height: 100%; width: 100%;">
		<div class="container-fluid px-4">

							<h6>&nbsp</h6>
						
							<div
								class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
								<h3>현재위치: {{locationName}}</h3>
								<div class="dataTable-top">
									<button @click="changePage(0), changeHeader('배송대기')" class="btn btn-dark">배송대기</button>
									<button @click="changePage(1), changeHeader('배송중') " class="btn btn-dark">배송중</button>
									<button @click="changePage(2), changeHeader('배송완료') " class="btn btn-dark">배송완료</button>
									<button onClick="insertGPS()" class="btn btn-dark">내 위치 갱신</button>
									
									<div><button onClick="DVLogOut()" class="btn btn-dark">로그아웃</button></div>
									
								</div>
								
								<div class="card mb-4">
									<div class="card-header">{{header}}</div>
									<div class="card-body">
										<table id="datatablesSimple" class="dataTable-table">
											<thead>
												<tr>
													<th style="width: 10%; text-align: center;"><a>배송원</a></th>
													<th style="width: 10%; text-align: center;"><a>주문사</a></th>
													<th style="width: 30%; text-align: center;"><a>배송지</a></th>
													<th style="width: 10%; text-align: center;"><a>전화번호</a></th>
													<th style="width: 10%; text-align: center;"><a>배송상태</a></th>
													<th style="width: 10%; text-align: center;"><a></a></th>
												</tr>
											</thead>

											<tbody v-if="deliveryPage[0].show">
												<tr v-if="dl.dl_dscode==1" v-for="dl in deliveryList">
													<td>{{dl.dv_name}}</td>
													<td>{{dl.sp_name}}</td>
													<td>{{dl.sp_address}}</td>
													<td>{{dl.sp_tel}}</td>
													<td>{{dl.ds_name}}</td>
													
													<td style="text-align: center">
														<button @click="insertsdcode(dl.dl_code, 2)"
																type="button" class="btn btn-dark">배송시작</button>
													</td>
												</tr>
											</tbody>
											
											<tbody v-if="deliveryPage[1].show">
												<tr v-if="dl.dl_dscode==2" v-for="dl in deliveryList">
													<td>{{dl.dv_name}}</td>
													<td>{{dl.sp_name}}</td>
													<td>{{dl.sp_address}}</td>
													<td>{{dl.sp_tel}}</td>
													<td>{{dl.ds_name}}</td>
													
													<td style="text-align: center">
														<button @click="insertsdcode(dl.dl_code, 3)"
																type="button" class="btn btn-dark">배송완료</button>
													</td>
												</tr>
											</tbody>

											<tbody v-if="deliveryPage[2].show">
												<tr v-if="dl.dl_dscode==3" v-for="dl in deliveryList">
													<td>{{dl.dv_name}}</td>
													<td>{{dl.sp_name}}</td>
													<td>{{dl.sp_address}}</td>
													<td>{{dl.sp_tel}}</td>
													<td>{{dl.ds_name}}</td>
													
													<td style="text-align: center">
														
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								</div>
								</div>
								</div>
	
	
 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7d1807c5dd075dd5bbfe97b2c92a0dd1&libraries=services"></script> <!--주소-좌표 변환을 할수 있응 services 라이브러리 불러오기-->
 

	<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/delivery.js"></script>
</body>
</html>