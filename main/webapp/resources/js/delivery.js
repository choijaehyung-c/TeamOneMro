/**
 * 
 */


const deliveryVue = new Vue({
   el:"#deliveryVue",
   data:{
      deliveryPage:[{show:false}, {show:false}, {show:false}],
      deliveryList:[],
	  header:"",
	  locationName:""
   },
   methods:{
      pushData:function(jsondata){
         this.deliveryList = jsondata;
      },
      insertsdcode:function(dlcode, dscode){
         let sendJsonData = {dl_code:dlcode, dl_dscode:dscode};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/Insertsdcode','callDeliveryList', 's', clientData);
      },
	  changePage:function(page){
		for (i = 0; i < this.deliveryPage.length; i++) {
				this.deliveryPage[i].show = false;
			}
			this.deliveryPage[page].show = true;
	  },
	  changeHeader:function(header){
		this.header=header;
	}
      
   }
   });


function callDeliveryList(msg){
	if(msg != ""){
		alert(msg);
	}
   postAjaxJson('vue/DeliveryTest','getDeliveryListPush', 'j');
}

function getDeliveryListPush(jsondata){
   deliveryVue.pushData(jsondata);
   deliveryVue.header="배송대기";
   deliveryVue.changePage(0);
}


function insertGPS(){
   	navigator.geolocation.getCurrentPosition(showLocation);
	let sendJsonData = {lc_x:gps_lat, lc_y:gps_lng};
    let clientData = JSON.stringify(sendJsonData);
    postAjaxJson('vue/InsertGPS','callDeliveryList', 's', clientData);
	
	getAddr(gps_lat,gps_lng);

	var container = document.getElementById('map'); //지도 표시 div
   	var options = {	 
         center: new kakao.maps.LatLng(gps_lat,gps_lng), //지도의 중심좌표
         level: 3 //지도의 확대 레벨
     };
	 
     var map = new kakao.maps.Map(container, options);
     
     // 마커가 표시될 위치
    var markerPosition  = new kakao.maps.LatLng(gps_lat,gps_lng); 

     // 마커 생성
     var marker = new kakao.maps.Marker({
         position: markerPosition
     });

     // 마커가 지도 위에 표시되도록 설정
     marker.setMap(map);

}
  
	






///////////////////////////배송로그인///////////
function sendDeliveryAccessInfo(){
	const ahcode = document.getElementsByName("ah_code")[0];
	const ahpwd = document.getElementsByName("ah_pwd")[0];
	
	let f = makeForm("DeliveryAccess", "post");
	
	f.appendChild(ahcode);
	f.appendChild(ahpwd);

	
	document.body.appendChild(f);
	f.submit();
	
}

function DVLogOut(){
	let f = makeForm("DeliveryLogOut", "post");
	document.body.appendChild(f);
	f.submit();
	
}






//////////////////지도api/

	 var GPS_X= 37.43864640271817;
	 var GPS_Y= 126.67535679686576;

	 var container = document.getElementById('map'); //지도 표시 div
     var options = {	 
         center: new kakao.maps.LatLng(GPS_X,GPS_Y), //지도의 중심좌표
         level: 3 //지도의 확대 레벨
     };
	 

     var map = new kakao.maps.Map(container, options);
     
     // 마커가 표시될 위치
     var markerPosition  = new kakao.maps.LatLng(GPS_X,GPS_Y); 

     // 마커 생성
     var marker = new kakao.maps.Marker({
         position: markerPosition
     });

     // 마커가 지도 위에 표시되도록 설정
     marker.setMap(map);

     // 아래 코드는 지도 위의 마커를 제거하는 코드
     // marker.setMap(null);  



  let lat = 37.43864640271817;
  let lng = 126.67535679686576;
  getAddr(lat,lng);
  function getAddr(lat,lng){
      let geocoder = new kakao.maps.services.Geocoder();

      let coord = new kakao.maps.LatLng(lat, lng);
      let callback = function(result, status) {
          if (status === kakao.maps.services.Status.OK) {
              console.log(result);
			  deliveryVue.locationName = result[0].address.address_name;
          }
      };

      geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
  }

