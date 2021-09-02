function receiveOrderControll(){
	
	postAjaxJson('getSupplyReceiveWaitOrderList','getReceiveList');
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

function callReceiveListD(recode){
		
		let sendJsonData = [];
		sendJsonData.push({re_code:recode});
		let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjax('getSupplyReceiveWaitOrderListD','getReceiveListD',clientData);
		
}


function getReceiveListD(jsonData){
		let orderHtml = "<div>상세내역</div>";
		let orderList = document.getElementById("orderListD");	
		
		orderHtml += "<div class = 'orderlist'>" +"공급사코드:"+jsonData.rd_prspcode+"주문코드:"+ jsonData.rd_recocde+ "상품코드:"+jsonData.rd_prcocde+ "주문수량:"+jsonData.rd_quantity+ "주문상태:"+jsonData.rd_stcode+"</div>";			
		orderList.innerHTML = orderHtml;
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