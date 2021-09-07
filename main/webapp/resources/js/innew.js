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
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_prspcode + ") 공급사 :"+ data[i].sp_name + "	상품코드 : " + data[i].od_prcode + "	상품이름 : " + data[i].pr_name + "  갯수 : "+ data[i].od_quantity  +"</div>";
	
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
	HTML += "<div>"+ "(공급사 코드"+ data[i].od_prspcode + ") 공급사 :"+ data[i].sp_name + "	상품코드 : " + data[i].od_prcode + "	상품이름 : " + data[i].pr_name + "  갯수 : "+ data[i].od_quantity + "[상태 - " + data[i].od_stcode + "]" + "</div>";
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
	 HTML += "<div>"+ "(공급사 코드"+ data[i].od_prspcode + ") 공급사 :"+ data[i].sp_name + "	상품코드 : " + data[i].od_prcode + "	상품이름 : " + data[i].pr_name + "  갯수 : "+ data[i].od_quantity + "[상태 - " + data[i].od_stcode + "]" +"</div>";
	
	}
	space.innerHTML= HTML;
	
}

//공급사쪽
function supplyReceiveRefundListForm(){
	postAjaxJson('supplyReceiveRefundListForm','supplyRefundList','j');
}

function supplyRefundList(data){
	console.log(data);
	let space = document.getElementById("refundSpace");
	
	let html = "<div>반품목록</div>";
	
   if(data!=""){	
	for(i=0; i<data.length; i++){
		html += "<div onClick=\"ReceiveRefundDetail('"+data[i].re_code+"','RR')\">주문코드 : "+data[i].re_code+ " 고객사 : "+ data[i].cl_name+" ( "+data[i].cl_hp +" ) " + " 상태 : "+ data[i].re_state + "  주문날짜 : "+ data[i].re_date +"</div>";
	 }
   }else{
		html += "<div>반품신청 목록이 없습니다.</div>";
}	
	
	space.innerHTML=html;
}


function ReceiveRefundDetail(recode,restate){

	let sendJsonData = {re_code:recode,re_state:restate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyReceiveAsDetail','getAsDetailListS','j',clientData);

}


function getAsDetailListS(data){
	let list = document.getElementById("detailSpace");
	let html = "<div><주문코드 : "+data[0].rd_recode+" 반품내역></div>";
	
	for(i=0; i<data.length; i++){
	 html += "<div> 상품코드: "+data[i].rd_prcode + "  상품이름: " +data[i].pr_name + "  주문갯수: " + data[i].rd_quantity+ "  상태: "+data[i].rd_stcode +"</div>";
	}
	
	 html += "<div onClick=\"responseRefund('"+data[0].rd_recode+"','PD')\">수락</div>";
     html += "<div onClick=\"responseRefund('"+data[0].rd_recode+"','FF')\">거절(거절을 누르시면, 구매확정 처리가 됩니다. 그전에 고객사와 통화해주시기 바랍니다.)</div>";

	list.innerHTML=html;
}


function responseRefund(recode, restate){
	//alert(oscode);
	//alert(osstate);
	if(restate=="PD"){ //폐기
	if(confirm("주문번호"+recode+"의 반품요청을 수락하시겠습니까?")){
	let sendJsonData = {re_code:recode, re_state:restate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyResponseRefund','refundResult', 's', clientData);
	}
}else{
	if(confirm("주문번호" + recode+ "의 반품요청을 거절하시겠습니까?")){
	let sendJsonData = {re_code:recode, re_state:restate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyResponseRefund','refundResult', 's', clientData);
	}
}
}

function refundResult(message){

	if(message!=""){
		alert(message);
	}
}

//공급사 교환리스트 
function ReceiveExchangeList(){
	postAjaxJson('supplyReceiveExchangeListForm','supplyExchangeList','j');
}

function supplyExchangeList(data){
	let space = document.getElementById("refundSpace");
	
	let html = "<div>교환목록</div>";
  if(data!=""){	
	for(i=0; i<data.length; i++){
		html += "<div onClick=\"ReceiveExchangeDetail('"+data[i].re_code+"','ER')\">주문코드 : "+data[i].re_code+ " 고객사 : "+ data[i].cl_name +" ( "+ data[i].cl_hp + " ) " +" 상태 : "+ data[i].re_state + "  주문날짜 : "+ data[i].re_date +"</div>";
	  }
   }else {
	    html +="<div>교환신청 목록이 없습니다.</div>";
}
	
	space.innerHTML=html;
}

function ReceiveExchangeDetail(recode,restate){
	let sendJsonData = {re_code:recode,re_state:restate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyReceiveAsDetail','getAsDetailListS2','j',clientData);
}

function getAsDetailListS2(data){
	let list = document.getElementById("detailSpace");
	let html = "<div><주문코드 : "+data[0].rd_recode+" 교환내역></div>";
	
	for(i=0; i<data.length; i++){
	 html += "<div> 상품코드: "+data[i].rd_prcode + "  상품이름: " +data[i].pr_name + "  주문갯수: " + data[i].rd_quantity+ "  상태: "+data[i].rd_stcode +"</div>";
	}
	
	 html += "<div onClick=\"responseExchange('"+data[0].rd_recode+"','EA')\">수락</div>";
     html += "<div onClick=\"responseExchange('"+data[0].rd_recode+"','EE')\">거절 (교환거절을 하시면, 구매확정이 됩니다. 그전에 고객사와 통화해주세요.)</div>";

	list.innerHTML=html;
}

function responseExchange(recode,restate){
	
	if(restate=="EC"){
	if(confirm("주문번호"+ recode+"의 교환요청을 수락하시겠습니까?")){
	let sendJsonData = {re_code:recode, re_state:restate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyResponseExchange','exchangeResult', 's', clientData);
	}
}else{
	if(confirm("주문번호" + recode+ "의 교환요청을 거절하시겠습니까?")){
	let sendJsonData = {re_code:recode, re_state:restate};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplyResponseExchange','exchangeResult', 's', clientData);
	}
  }
}

function exchangeResult(message){

	if(message!=""){
		alert(message);
	}
}

function supplySearch(){
	let word = document.getElementsByName("word")[0].value;
	
	let sendJsonData = {word:word};
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('supplySearchAs','supplySearchResult','j', clientData);
}

function supplySearchResult(data){
	let space = document.getElementById("refundSpace");
	
	let html = "<div>검색목록</div>";
	
	if(data!=""){
	for(i=0; i<data.length; i++){
		if(data[i].re_state =="교환요청"){
		html += "<div onClick=\"ReceiveExchangeDetail('"+data[i].re_code+"','ER')\">주문코드 : "+data[i].re_code+ " 고객사 : "+ data[i].cl_name + " 상태 : "+ data[i].re_state + "  주문날짜 : "+ data[i].re_date +"</div>";
		}else{
		html += "<div onClick=\"ReceiveRefundDetail('"+data[i].re_code+"','RR')\">주문코드 : "+data[i].re_code+ " 고객사 : "+ data[i].cl_name + " 상태 : "+ data[i].re_state + "  주문날짜 : "+ data[i].re_date +"</div>";
		}
	  }
	}else{
		html += "검색결과가 없습니다.";
	}
	
	space.innerHTML=html;
}

function category(){
		postAjaxJson('supplyGetCategory','getCate','j');
}

function getCate(jsonData){
	let cate = document.getElementById("cate");
	
	let html  = "<div>카테고리 목록</div>";
	
	for(i=0; i<jsonData.length; i++){
		html += "<div onClick=\"getProductList('"+jsonData[i].cate+"')\">"+ jsonData[i].cate_name+"</div>";
	}
	
	cate.innerHTML=html;
}

function getProductList(cate){
	//alert(cate);
	let sendData = {cate:cate};
	let clientData = JSON.stringify(sendData);
	postAjaxJson('supplyProductList','getProduct','j',clientData);
}

function getProduct(data){
	let list = document.getElementById("product");
	let html = "<div></div>";
	
	if(data!=""){
		for(i=0; i<data.length; i++){
		html+= "<div> " +" 상품코드 : "+data[i].pr_code+ " 상품 재고 : "+data[i].pr_stock+" 상품 이름 : " +data[i].pr_name+ " 상품정보 : " +data[i].pr_info+" 원산지: " +data[i].pr_origin+ " 상품가격 : " + data[i].pr_price+ "원 상품이미지 : "+ "<img src=\'"+data[i].pr_image+"'\/>" +"</div><br>";
		}
	}else{
		alert("해당 카테고리에 등록된 상품이 없습니다.");
	}
	list.innerHTML = html;
}

function productSearch(){
	let word = document.getElementsByName("word")[1];

	let send = {word:word.value};
	let clientData = JSON.stringify(send);
	postAjaxJson('supplySearchProduct','searchProduct','j',clientData);
	
}

function searchProduct(data){
	let list = document.getElementById("product");
	let html = "<div></div>";
	
	if(data!=""){
		for(i=0; i<data.length; i++){
		html+= "<div> " +" 상품코드 : "+data[i].pr_code+ " 상품 재고 : "+data[i].pr_stock+" 상품 이름 : " +data[i].pr_name+ " 상품정보 : " +data[i].pr_info+" 원산지: " +data[i].pr_origin+ " 상품가격 : " + data[i].pr_price+ "원 상품이미지 : "+ "<img src=\'"+data[i].pr_image+"'\/>" +"</div><br>";
		}
	}else{
		alert("해당 카테고리에 등록된 상품이 없습니다.");
	}
	list.innerHTML = html;
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
