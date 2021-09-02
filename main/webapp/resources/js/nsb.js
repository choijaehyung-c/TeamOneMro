

function receiveOrderControll(){

	postAjaxJson('SupplyNSB/getSupplyReceiveWaitOrderList','getReceiveList');
	
}
	

function getReceiveList(jsonData){
	
		let teamHtml = "<div>접수대기리스트</div><div class='detail' onclick='listDetail()'>상세보기</div>";
		let teamList = document.getElementById("orderList");		
	
		for(i=0; i<jsonData.length; i++){
		 	teamHtml += "<div class = 'list' width:\'80%\'>" +"</div>";
			teamHtml += "<div class = 're_code'>" +jsonData[i].re_code+ "</div>";
			teamHtml += "<div class = 're_clcode'>" +jsonData[i].re_clcode+ "</div>";
			teamHtml += "<div class = 're_spcode'>" +jsonData[i].re_spcode+ "</div>";
			teamHtml += "</div>";
		}
	
		teamList.innerHTML = teamHtml;
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