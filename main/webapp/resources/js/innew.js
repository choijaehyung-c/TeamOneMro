/**
 * 
 */

//고객사 목록 불러오기
function clientList(){
	
	let f = makeForm("/iyj/mroClientListForm","post");
	document.body.append(f);
	f.submit();
	alert("고객사목록을 불러옵니다.");
	
}

//공급사 목록 불러오기
function supplyList(){
	
	let f = makeForm("/iyj/mroSupplyListForm","post");
	document.body.append(f);
	f.submit();
	alert("공급사목록을 불러옵니다.");
}

/*const ordervue = new Vue({
	el : "#vuezone",
	data:{
		msg : '응답대기',
		orderList : []	
	},
	methods:{
		orderListResponse : function(data){
			this.orderList = data;
		},
		btn:function(){
			this.orderList=(this.orderList)
		}
	}
	
});*/


//mro에서 주문대기리스트 불러오기
function mroOrderList(){
	postAjaxJson('vue2/mroOrderListForm','getWaitOrderListM','j',clientData="");
}

//주문목록 찍어주는 펑션
function getWaitOrderListM(data){
	
	//ordervue.orderListResponse(data);
	
	let space = document.getElementById("mOrderList");
	let HTML = "<div>주문목록</div>";
	
	if(data!=""){
	for(i=0; i<data.length; i++){
		HTML += "<div id='orderList' name='orderList'  onClick='getDetail("+data[i].os_code+")'>"+"[주문날짜 : " + data[i].os_date +"] [주문번호 : " + data[i].os_code+ "] [고객사 : " + data[i].cl_name + "] [상태 : " + data[i].os_state +"]</div>";		
	   }
 	}else{
		HTML = "<div>주문목록이 없습니다.</div>";
	}
	space.innerHTML = HTML;
	
}

//주문번호의 상세정보 불러오기
function getDetail(data){
	
		alert("주문번호 "+ data + "의 상세내역입니다.");
		postAjaxJson('/vue2/mroGetOrderDetail','getOrderDetailM','j',data);
}

function getOrderDetailM(data){
	let space = document.getElementById("mOrderDetail");
	
	let HTML = "";
	for(i=0; i<data.length; i++){
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_prcode + ") 공급사 :"+ data[i].sp_name + "	상품코드 : " + data[i].od_prcode + "	상품이름 : " + data[i].pr_name +"</div>"
	
	}
	space.innerHTML=HTML;
	
}

//mro에서 반품리스트 불러오기
function mroRefundList(){
	postAjaxJson('vue2/mroRefundListForm','getRefundListM','j',clientData="");
	
}

//mro 반품리스트 찍어오는 펑션
function getRefundListM(jsonData){
	//alert(jsonData);
	let space1 = document.getElementById("mOrderList");
	let HTML1 = "<div>반품목록</div>";
	
	for(i=0; i<jsonData.length; i++){
		HTML1 += "<div id='refundList' name='refundList'  onClick='getRefundDetail("+jsonData[i].os_code+")'>"+"[주문날짜 : " + jsonData[i].os_date +"] [주문번호 : " + jsonData[i].os_code+ "] [고객사 : " + jsonData[i].cl_name + "] [상태 : " + jsonData[i].os_state +"]</div>";
				
		space1.innerHTML = HTML1;
}
}

function getRefundDetail(data){
	
	alert("주문번호 "+ data + " 의 반품 상세내역입니다.");
	postAjaxJson('/vue2/mroGetRefundDetail','getRefundDetailListM','j',data);
		
}

function getRefundDetailListM(data){
	let space = document.getElementById("mOrderDetail");
	
	let HTML = "";

	for(i=0; i<data.length; i++){
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_prcode + ") 공급사 :"+ data[i].sp_name + "	상품코드 : " + data[i].od_prcode + "	상품이름 : " + data[i].pr_name +"</div>"
	
	}
 	
	space.innerHTML=HTML;
	
}

//mro에서 교환리스트 불러오기
function mroExchangeList(){
	postAjaxJson('vue2/mroExchangeListForm','getExchageListM','j',clientData="");
}

//mro 교환리스트 찍어오는 펑션
function getExchageListM(data){
	
	let space2 = document.getElementById("mOrderList");
	let contents = "<div>교환목록</div>";
	
	if(data!=""){
	 for(i=0; i<data.length; i++){
		contents += "<div id='orderList' name='orderList'  onClick='getDetail("+data[i].os_code+")'>"+"[주문날짜 : " + data[i].os_date +"] [주문번호 : " + data[i].os_code+ "] [고객사 : " + data[i].cl_name + "] [상태 : " + data[i].os_state +"]</div>";		
	   }
 	}else{
		contents = "<div>교환목록이 없습니다.</div>";
	}
	space2.innerHTML = contents;
}

function getExchangeDetail(data){
	alert("주문번호 "+ data + " 의 교환 상세내역입니다.");
	postAjaxJson('/vue2/mroGetExchangeDetail','getExchangeDetailListM','j',data);
}

function getExchangeDetailListM(data){
	
	let space = document.getElementById("mOrderDetail");
	
	let HTML = "";
	for(i=0; i<data.length; i++){
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_prspcode + ") 공급사 : "+ data[i].sp_name + "		상품코드 : " + data[i].od_prcode + "		상품이름 : " + data[i].pr_name +"</div>"
	
	}
	space.innerHTML= HTML;
	
}


function supplyReceiveRefundListForm(){
	postAjaxJson('supplyReceiveRefundListForm','supplyRefundList','j');
}

function supplyRefundList(data){
	console.log(data);
	let space = document.getElementById("refundSpace");
	
	let html = "<div>반품목록</div>";
	
	for(i=0; i<data.length; i++){
		html += "<div onClick=\"ReceiveRefundDetail('"+data[i].os_code+"','RR')\">주문코드 : "+data[i].os_code+ " 고객사 : "+ data[i].cl_name + " 상태 : "+ data[i].os_state + "  주문날짜 : "+ data[i].os_date +"</div>";
	}
	
	space.innerHTML=html;
}


function ReceiveRefundDetail(oscode,osstate){

	let sendJsonData = {os_code:oscode,os_state:osstate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyReceiveAsDetail','getAsDetailListS','j',clientData);

}


function getAsDetailListS(data){
	let list = document.getElementById("detailSpace");
	let html = "<div><주문코드 : "+data[0].od_oscode+" 반품내역></div>";
	
	for(i=0; i<data.length; i++){
	 html += "<div> 상품코드: "+data[i].od_prcode + "  상품이름: " +data[i].pr_name + "  주문갯수: " + data[i].od_quantity+ "  상태: "+data[i].od_stcode +"</div>";
	}
	
	 html += "<div onClick=\"responseRefund('"+data[0].od_oscode+"','RC')\">수락</div>";
     html += "<div onClick=\"responseRefund('"+data[0].od_oscode+"','FF')\">거절</div>";

	list.innerHTML=html;
}


function responseRefund(oscode, osstate){
	alert(oscode);
	alert(osstate);
	let sendJsonData = {os_code:oscode, os_state:osstate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyResponseRefund','refundResult', 'j', clientData);
	alert(clientData);
	
}







/*const orderList = new Vue({
	el: "#mOrderList",
	data:{
		msg:"ss",
		list:[]
	},
	method:{
		mroOrderList:function(){
			axios.get('iyj/mroOrderListForm')
			.then(result =>{
				this.list = result.data;
			})
		},
		putData:function(data){
			this.list=data
		}
	}
	
});*/

/*function orderDetail(){
	let os = document.getElementsByName("OD_OSCODE")[0].value;
	//orderList.putData= JSON.parse(os);
	console.log(os);

}*/
