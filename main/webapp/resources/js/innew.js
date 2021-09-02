/**
 * 
 */

//고객사 목록 불러오기
function clientList(){
	
	let f = makeForm("/iyj/mroClientListForm","post");
	document.body.append(f);
	f.submit();
	//alert("고객사목록을 불러옵니다.");
	
}

//공급사 목록 불러오기
function supplyList(){
	
	let f = makeForm("/iyj/mroSupplyListForm","post");
	document.body.append(f);
	f.submit();
	//alert("공급사목록을 불러옵니다.");
}

//mro에서 주문대기리스트 불러오기
function mroOrderList(){
	
	postAjaxJson('vue2/mroOrderListForm','getWaitOrderListM',clientData="");
	
}

//주문목록 찍어주는 펑션
function getWaitOrderListM(data){
	
	let space = document.getElementById("mOrderList");
	let HTML = "<div>주문목록</div>";
	
	for(i=0; i<data.length; i++){
		HTML += "<div id='orderList' name='orderList'  onClick='getDetail("+data[i].os_CODE+")'>"+"[주문날짜 : " + data[i].os_DATE +"] [주문번호 : " + data[i].os_CODE+ "] [고객사 : " + data[i].cl_NAME + "] [상태 : " + data[i].os_STATE +"]</div>";
				
		space.innerHTML = HTML;
	}
}

//주문번호의 상세정보 불러오기
function getDetail(data){
	
		alert("주문번호 "+ data + "의 상세내역입니다.");
		postAjaxJson('/vue2/mroGetOrderDetail','getOrderDetailM',data);
}

function getOrderDetailM(data){
	let space = document.getElementById("mOrderDetail");
	
	let HTML = ""
	for(i=0; i<data.length; i++){
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_PRSPCODE + ") 공급사 :"+ data[i].sp_NAME + "	상품코드 : " + data[i].od_PRCODE + "	상품이름 : " + data[i].pr_NAME +"</div>"
	
	}
	space.innerHTML=HTML;
	
}

//mro에서 반품리스트 불러오기
function mroRefundList(){
	postAjaxJson('vue2/mroRefundListForm','getRefundListM',clientData="");
	
}

//mro 반품리스트 찍어오는 펑션
function getRefundListM(jsonData){
	//alert(jsonData);
	let space1 = document.getElementById("mOrderList");
	let HTML1 = "<div>반품목록</div>";
	
	for(i=0; i<jsonData.length; i++){
		HTML1 += "<div id='refundList' name='refundList'  onClick='getRefundDetail("+jsonData[i].os_CODE+")'>"+"[주문날짜 : " + jsonData[i].os_DATE +"] [주문번호 : " + jsonData[i].os_CODE+ "] [고객사 : " + jsonData[i].cl_NAME + "] [상태 : " + jsonData[i].os_STATE +"]</div>";
				
		space1.innerHTML = HTML1;
}
}

function getRefundDetail(data){
	
	alert("주문번호 "+ data + " 의 반품 상세내역입니다.");
	postAjaxJson('/vue2/mroGetRefundDetail','getRefundDetailListM',data);
		
}

function getRefundDetailListM(data){
	
	let space = document.getElementById("mOrderDetail");
	
	let HTML = ""
	for(i=0; i<data.length; i++){
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_PRSPCODE + ") 공급사 : "+ data[i].sp_NAME  + "		상품코드 : " + data[i].od_PRCODE + "		상품이름 : " + data[i].pr_NAME +"</div>"
	
	}
	space.innerHTML=HTML;
	
}

//mro에서 교환리스트 불러오기
function mroExchangeList(){
	postAjaxJson('vue2/mroExchangeListForm','getExchageListM',clientData="");
}

//mro 교환리스트 찍어오는 펑션
function getExchageListM(data){
	
	let space2 = document.getElementById("mOrderList");
	let contents = "<div>교환목록</div>";
	
	for(i=0; i<data.length; i++){
		contents += "<div id='exchangeList' name='exchangeList' onClick='getExchangeDetail("+data[i].os_CODE+")'>"+"[주문날짜 : " + data[i].os_DATE +"] [주문번호 : " + data[i].os_CODE+ "] [고객사 : " + data[i].cl_NAME + "] [상태 : " + data[i].os_STATE +"]</div>";
				
		space2.innerHTML = contents;
 }
}

function getExchangeDetail(data){
	alert("주문번호 "+ data + " 의 교환 상세내역입니다.");
	postAjaxJson('/vue2/mroGetExchangeDetail','getExchangeDetailListM',data);
}

function getExchangeDetailListM(data){
	
	let space = document.getElementById("mOrderDetail");
	
	let HTML = ""
	for(i=0; i<data.length; i++){
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_PRSPCODE + ") 공급사 : "+ data[i].sp_NAME + "		상품코드 : " + data[i].od_PRCODE + "		상품이름 : " + data[i].pr_NAME +"</div>"
	
	}
	space.innerHTML=HTML;
	
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
