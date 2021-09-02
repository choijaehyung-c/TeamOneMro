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
			alert(ajax.responseText);
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

let inout = "";
let jobCodeField ="";

function readyAccessMro(data,jc){
	inout = data;
	jobCodeField = jc;
	
	/*if(inout==1){
		const userId = document.getElementsByName("userId")[0];
		const userPwd = document.getElementsByName("userPwd")[0];
		
		if(userId.value ==""){
			userId.focus();
			return;
		}
		if(userPwd.value ==""){
			userPwd.focus();
			return;
		}
	}*/
	if(data=='1'){
		getAjax("https://api.ipify.org?format=json","sendAccessInfo");
	}else{
		getAjax("https://api.ipify.org?format=json","accessOut");
	}
}

function sendAccessInfo(Ip){
	
	const method = makeInput("hidden","AHM_METHOD",inout);
	const publicIp = makeInput("hidden","AHM_PUBLICIP",Ip.ip);
	const privateIp = makeInput("hidden","AHM_PRIVATEIP",location.host);
	const browser = makeInput("hidden","AHM_BROWSER",navigator.userAgent.replace(/ /g,""));
	let Id = document.getElementsByName("AHM_CODE")[0];
	let Pwd = document.getElementsByName("MD_PWD")[0];
	
	let	f = makeForm(jobCodeField,"post");
	
	f.appendChild(Id);
	f.appendChild(Pwd);
	f.appendChild(method);
	f.appendChild(publicIp);
	f.appendChild(privateIp);
	f.appendChild(browser);
	
	document.body.appendChild(f);
	f.submit();
	
}

function accessOut(Ip){
	const method = makeInput("hidden","AHM_METHOD",inout);
	const publicIp = makeInput("hidden","AHM_PUBLICIP",Ip.ip);
	const privateIp = makeInput("hidden","AHM_PRIVATEIP",location.host);
	const browser = makeInput("hidden","AHM_BROWSER",navigator.userAgent.replace(/ /g,""));
	let	f = makeForm(jobCodeField,"post");
	f.appendChild(method);
	f.appendChild(publicIp);
	f.appendChild(privateIp);
	f.appendChild(browser);
	
	document.body.appendChild(f);
	f.submit();
	
}