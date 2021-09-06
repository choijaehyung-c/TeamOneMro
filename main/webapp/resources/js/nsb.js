function receiveOrderControll(){
	
	postAjaxJson('getSupplyReceiveWaitOrderList','getReceiveList');
}

function receiveOrderC(){
	
	postAjaxJson('getSupplyReceiveClearOrderList','getReceiveListC');
}

function getReceiveList(jsonData){
	
	console.log(jsonData);
		let orderHtml = "<div>주문 접수 리스트</div>";
		let orderList = document.getElementById("orderList");		
	
		for(i=0; i<jsonData.length; i++){
			orderHtml += "<div class = 'orderlist'>" +"주문코드:"+jsonData[i].re_code+"고객사코드:"+ jsonData[i].re_clcode+ "공급사코드:"+jsonData[i].re_spcode+ "주문날짜:"+jsonData[i].re_date+ "주문상태:"+jsonData[i].re_state+"</div>";			
			orderHtml += "<div class='recode' onclick='callReceiveListD(\""+jsonData[i].re_code+"\")'>상세보기</div>";		
		}
		orderList.innerHTML = orderHtml;
}

function getReceiveListC(jsonData){
	
	console.log(jsonData);
		let orderHtml = "<div>주문 접수 완료 리스트</div>";
		let orderList = document.getElementById("orderListC");		
	
		for(i=0; i<jsonData.length; i++){
			orderHtml += "<div class = 'orderlist'>" +"주문코드:"+jsonData[i].re_code+"고객사코드:"+ jsonData[i].re_clcode+ "공급사코드:"+jsonData[i].re_spcode+ "주문날짜:"+jsonData[i].re_date+ "주문상태:"+jsonData[i].re_state+"</div>";			
			orderHtml += "<div class='recode' onclick='callReceiveListCD(\""+jsonData[i].re_code+"\")'>상세보기</div>";		
		}
		orderList.innerHTML = orderHtml;
}

function callReceiveListD(recode){
		
		let sendJsonData = [];
		sendJsonData.push({re_code:recode});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson('getSupplyReceiveWaitOrderListD','getReceiveListD',clientData);
		
}

function callReceiveListCD(recode){
		
		let sendJsonData = [];
		sendJsonData.push({re_code:recode});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson('getSupplyReceiveClearOrderListD','getReceiveListCD',clientData);
		
}


function getReceiveListD(jsonData){
		let orderHtml = "<div>상세내역</div>";
		let orderList = document.getElementById("orderListD");	
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		orderHtml += "<div class = 'orderlist'>" +"공급사코드:"+jsonData[i].rd_prspcode+ " 주문코드:"+ jsonData[i].rd_recode+ "상품코드:"+jsonData[i].pr_code+ "주문수량:"+jsonData[i].rd_quantity+ "주문상태:"+jsonData[i].rd_stcode+"</div>";
		}		
		orderHtml += "<input type = 'button' onclick ='responseOrder(\""+jsonData[0].rd_recode+"\")' value='주문접수확인' />";		
		
		orderList.innerHTML = orderHtml;
}

function getReceiveListCD(jsonData){
		let orderHtml = "<div>상세내역</div>";
		let orderList = document.getElementById("orderListCD");	
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		orderHtml += "<div class = 'orderlist'>" +"공급사코드:"+jsonData[i].rd_prspcode+ " 주문코드:"+ jsonData[i].rd_recode+ "상품코드:"+jsonData[i].pr_code+ "주문수량:"+jsonData[i].rd_quantity+ "주문상태:"+jsonData[i].rd_stcode+"</div>";	
		}			
		
		orderList.innerHTML = orderHtml;
}

function responseOrder(recode){
	
	let sendJsonData = [];
		sendJsonData.push({rd_recode:recode});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson2('responseOrder','receiveOrderControll2',clientData);
}// 접수확인 클릭 시 주문서 상태 상품준비중 업데이트 -> 상품준비중 리스트 출력 후 배송시작 버튼 클릭= dl 테이블 인서트

function receiveOrderControll2(message){
	
	if(message!= ""){
		alert(message);
		//spcode 다시 보내기
		
	}else{
		alert("접수등록 실패");
		}	
}

function delivery(){
		postAjaxJson('getDLlist','getDLlist');
}

function getDLlist(jsonData){
	console.log(jsonData);
		let deliveryHtml = "<div>배송 대기 리스트</div>";
		let deliveryList = document.getElementById("DLlist");		
	
		for(i=0; i<jsonData.length; i++){
			deliveryHtml += "<div class = 'DLlist'>" +"배송코드:"+jsonData[i].dl_code+"주문코드:"+ jsonData[i].dl_oscode+ "배송상태:"+jsonData[i].ds_name+ "배송기사:"+jsonData[i].dv_name+ "위치:"+jsonData[i].dl_lccode+"</div>";			
			deliveryHtml +="<div>"+"<select id='driver' class='box'>"+
						"<option>기사선택</option>"+
						"<option value='IYJ032'>인유정</option>"+
						"</select>"+"</div>"+"<div class='chooseDV' onclick='ChoiceDV(\""+jsonData[i].dl_code+"\")'>배송기사 배정</div>";
		}
		
		deliveryList.innerHTML = deliveryHtml;
}


function ChoiceDV(dlcode){
	let driver = document.getElementById("driver");
	let sendJsonData = [];
		sendJsonData.push({dl_code:dlcode, dl_dvcode:driver.value});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson2('choiceDV','choiceDVControll',clientData);
}

function choiceDVControll(message){
	
	if(message!= ""){
		alert(message);
		//spcode 다시 보내기
		
	}else{
		alert("배정 실패");
		}	
}

function issueTaxBillForm(){
	let f = makeForm("issueTaxBillForm", "get");
	document.body.appendChild(f);
	f.submit();
}

function spInfo(){
	//세금계산서 폼 출력 및 session으로 서플라이업체 정보 셀렉해오기
	let sendJsonData = [];
		sendJsonData.push({sp_code:"KR001D"});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson('getChoiceSPInfo','inputSPInfo',clientData);
}

function inputSPInfo(jsonData){
	let inputHtml = "";
	let inputList = document.getElementById("spInfo");
			
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		
		inputHtml += "<div id = 'spname'>" +"공급사명:"+jsonData[i].sp_name+"</div>"
		inputHtml += "<div id = 'spaddress'>" +"주소:"+jsonData[i].sp_address+"</div>"
		inputHtml += "<div id = 'spbtype'>" +"업태:"+jsonData[i].sp_btype+"</div>"
		inputHtml += "<div id = 'spbkind'>" +"종목:"+jsonData[i].sp_bkind+"</div>"
		inputHtml += "<div id = 'sptel'>" +"연락처:"+jsonData[i].sp_tel+"</div>"
		inputHtml += "<div id = 'spcorpnum'>" +"사업자번호:"+jsonData[i].sp_corpnum+"</div>"
	
		}			
		
		inputList.innerHTML = inputHtml;
}


function choiceTaxCL(){
	
	postAjaxJson('getTaxCL','getTaxCL');
	
}

function getTaxCL(jsonData){
		//세금계산서 폼에 있는 고객사 찾기버튼, 모달
		let clHtml = "<div>고객사목록</div>";
		let clList = document.getElementById("clList");
			
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		clHtml += "<div id = 'clList'>" +"고객사코드:"+jsonData[i].cl_code+ "고객사명:"+ jsonData[i].cl_name+ "주소:"+jsonData[i].cl_address+ "사업자번호:"+jsonData[i].cl_corpnum+"</div>"+"<div class='clInfo' onclick='choiceCLInfo(\""+jsonData[i].cl_code+"\")'>선택</div>";	
		}			
		
		clList.innerHTML = clHtml;
}

function choiceCLInfo(clcode){

	let sendJsonData = [];
		sendJsonData.push({cl_code:clcode});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson('getchoiceCLInfo','inputCLInfo',clientData);

}

function inputCLInfo(jsonData){
	let inputHtml = "";
	let inputList = document.getElementById("clInfo");
			
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		inputHtml += "<div id = 'clname'>" +"고객사명:"+jsonData[i].cl_name+"</div>"
		inputHtml += "<div id = 'claddress'>" +"주소:"+jsonData[i].cl_address+"</div>"
		inputHtml += "<div id = 'clbtype'>" +"업태:"+jsonData[i].cl_btype+"</div>"
		inputHtml += "<div id = 'clbkind'>" +"종목:"+jsonData[i].cl_bkind+"</div>"
		inputHtml += "<div id = 'clhp'>" +"연락처:"+jsonData[i].cl_hp+"</div>"
		inputHtml += "<div id = 'clcorpnum'>" +"사업자번호:"+jsonData[i].cl_corpnum+"</div>"
	
		}			
		
		inputList.innerHTML = inputHtml;
	
}

function choiceTaxDill(){
	postAjaxJson('getTaxDill','getTaxDill');
}

function getTaxDill(jsonData){
		//세금계산서 폼에 있는 거래목록버튼, 모달
		let dillHtml = "<div>거래목록</div>";
		let dillList = document.getElementById("dillList");
			
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		dillHtml += "<div id = 'dillList'>" +"주문코드:"+jsonData[i].re_code+ "고객사코드:"+ jsonData[i].re_clcode+ "고객사명:"+jsonData[i].cl_name+"</div>"+"<div class='dilllist' onclick='choiceDillInfo(\""+jsonData[i].re_code+"\")'>선택</div>";	
		}			
		
		dillList.innerHTML = dillHtml;
}

function choiceDillInfo(recode){
	let sendJsonData = [];
		sendJsonData.push({rd_recode:recode});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson('getchoiceDillInfo','inputDillInfo',clientData);
}

function inputDillInfo(jsonData){
	let inputHtml = "";
	let inputList = document.getElementById("dillInfo");
			
		console.log(jsonData);
		for(i=0; i<jsonData.length; i++){
		inputHtml += "<div id = 'prname'>" +"품명:"+jsonData[i].pr_name+"</div>"
		inputHtml += "<div id = 'rdquantity'>" +"수량:"+jsonData[i].rd_quantity+"</div>"
		inputHtml += "<div id = 'prprice'>" +"공급가액:"+jsonData[i].pr_price+"</div>"
		inputHtml += "<div id = 'prtax'>" +"세액:"+jsonData[i].pr_tax+"</div>"
	
		}			
		
		inputList.innerHTML = inputHtml;
	
}




function getAjax(jobCode,fn,clientData=""){
        	let ajax = new XMLHttpRequest();
        	ajax.onreadystatechange = function(){
        		if(ajax.readyState == 4 && ajax.status == 200){
					window[fn](JSON.parse(ajax.responseText));
        		}
        	}
        	ajax.open("GET",jobCode);
        	ajax.send(clientData);
}

function postAjaxJson(jobCode,fn,clientData="") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			window[fn](JSON.parse(ajax.responseText));
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/json; charset=utf-8");
	ajax.send(clientData);
}

function postAjaxJson2(jobCode,fn,clientData="") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			window[fn](ajax.responseText);
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/json; charset=utf-8");
	ajax.send(clientData);
}

function postAjaxString(jobCode,fn,clientData="") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			window[fn](data);
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded;");
	ajax.send(clientData);
}



function makeForm(action,method,name = null){//name = null => name값이 없다면 null
	let form = document.createElement("form");
	if(name!=null){form.setAttribute("name",name);}
	form.setAttribute("action",action);
	form.setAttribute("method",method);
	return form;
}

function makeInput(type,name,value){
	let input = document.createElement("input");
	input.setAttribute("type",type);
	input.setAttribute("name",name);
	input.setAttribute("value",value);
	return input;
}