function getAjax(jobCode, fn, rType, clientData = "") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			if (rType == 'j') {
				window[fn](JSON.parse(ajax.responseText));
			} else if (rType == 's') {
				window[fn](ajax.responseText);
			}
		}
	}
	ajax.open("GET", jobCode);
	ajax.send(clientData);
	/*거의 안씀 보안성 요구가 0 노출되도 상관없는 잡일때 사용*/
}

function postAjaxForm(jobCode, fn, rType, clientData = "") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			if (rType == 'j') {
				window[fn](JSON.parse(ajax.responseText));
			} else if (rType == 's') {
				window[fn](ajax.responseText);
			}
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded;");
	ajax.send(clientData);
	/*
	보안성이 필요한 요청을 할때 form으로 전송!! ex)pwd를 파람으로 받는 잡일때 사용
	clientData=`변수명=${변수값}&변수명=${변수값}`;
	백엔드 컨트롤러에서는 @ModelAttribute으로 받으면됨(빈도 받을 수 있음)
	*/
}

function postAjaxJson(jobCode, fn, rType, clientData = "") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			if (rType == 'j') {
				window[fn](JSON.parse(ajax.responseText));
			} else if (rType == 's') {
				window[fn](ajax.responseText);
			}
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/json; charset=utf-8");
	ajax.send(clientData);
	/*
	대부분 이것으로 사용, 제이슨 타입으로 파람 넘김
	백엔드에서 빈으로 받을꺼면 clientData={키:밸류,키:밸류}
	그 빈안에 <>리스트 타입이 있다면 {키:밸류,키:밸류,키:[]밸류}
	백엔드 컨트롤러에서는 @RequestBody로 받으면됨
	*/
}

function postAjaxMultiUpload(jobCode,fn){
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange=function(){
		if(ajax.readyState==4 && ajax.status ==200){
			window[fn](ajax.responseText);
		}
	}
	ajax.open("POST",jobCode);
	let formData = new FormData();
	let CG = document.getElementById("CG");
	let file = document.getElementsByName("file")[0];
	formData.append('cate',CG.options[CG.selectedIndex].value);
	formData.append('catename',CG.options[CG.selectedIndex].text);
	formData.append('file',file.files[0],file.files[0].name);
	formData.append('pr_name',document.getElementsByName("pr_name")[0].value);
	formData.append('pr_price',document.getElementsByName("pr_price")[0].value);
	formData.append('pr_stock',document.getElementsByName("pr_stock")[0].value);
	formData.append('pr_origin',document.getElementsByName("pr_origin")[0].value);
	formData.append('pr_info',document.getElementsByName("pr_info")[0].value);
	ajax.send(formData);
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
let jobCodeField = "";

function readyAccessMro(data, jc) {
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
	if (data == '1') {
		getAjax("https://api.ipify.org?format=json","sendAccessInfo","j");
	}else{
		getAjax("https://api.ipify.org?format=json","accessOut","j");
	}
}

function sendAccessInfo(Ip){

	const method = makeInput("hidden","ah_method",inout);
	const publicIp = makeInput("hidden","ah_publicip",Ip.ip);
	const privateIp = makeInput("hidden","ah_privateip",location.host);
	const browser = makeInput("hidden","ah_browser",navigator.userAgent.replace(/ /g,""));
	const Id = document.getElementsByName("ah_code")[0];
	const Pwd = document.getElementsByName("ah_pwd")[0];
	
	
	let type;
	for(i=0;i<2;i++){
		if(document.getElementsByName("ah_table")[i].checked){
			type=document.getElementsByName("ah_table")[i];
		}
	}
	
	let	f = makeForm(jobCodeField,"post");
	if(type.value=="AHS"){
	const spcode = document.getElementsByName("ah_sdspcode")[0];
	f.appendChild(spcode);}
	f.appendChild(Id);
	f.appendChild(Pwd);
	f.appendChild(method);
	f.appendChild(publicIp);
	f.appendChild(privateIp);
	f.appendChild(browser);
	f.appendChild(type);
	
	document.body.appendChild(f);
	f.submit();
	
}

function accessOut(Ip){
	const method = makeInput("hidden","ah_method",inout);
	const publicIp = makeInput("hidden","ah_publicip",Ip.ip);
	const privateIp = makeInput("hidden","ah_privateip",location.host);
	const browser = makeInput("hidden","ah_browser",navigator.userAgent.replace(/ /g,""));
	let	f = makeForm(jobCodeField,"post");
	f.appendChild(method);
	f.appendChild(publicIp);
	f.appendChild(privateIp);
	f.appendChild(browser);
	
	document.body.appendChild(f);
	f.submit();
}

function typeChange(){
	
	if($("input[name='ah_table']:checked").val()=="AHS"){
		$('#insertPoint').html(
			`<div class="form-group">
             <input type="text" name="ah_sdspcode" class="form-control form-control-user" placeholder="Office Code"></div>`);
	}else{
		$('#insertPoint').html('');
	}
	
	
}