/**
 * 
 */
const appvue = new Vue({
	el:"#vuezone",
	data:{
		msg:'응답대기',
		modalOnOff:'off'
	},
	methods:{
		serverResponse:function(ddata){
			this.msg = ddata;
		},
		momo:function(){
			//this.modalOnOff = 'on';
			this.modalOnOff=(this.modalOnOff=='on')?'off':'on';
		}
		
	}
});

function modalOn(){
	appvue.momo();
}


function ajaxToServerResponse(data){
	appvue.serverResponse(data);
}




function sendApiData(){
	let id = document.getElementsByName("os_clcode")[0].value;
	let pwd = document.getElementsByName("cl_pwd")[0].value;
	
	console.log(pwd);
	/*clientData=`변수명(빈.OO)=${변수값}&변수명(빈.OO)=${변수값}`;
	let OD = [];
	for(i=0;i<3;i++){
		OD.push({OD_PRSPCODE:"KR001D",OD_PRCODE:"123123123"+i});
	}
	*/
	//let clientData = cl_Pwd:pwd;
	//console.log(clientData.OS_CLCODE+clientData.CL_PWD)
	
	let clientData = `'os_clcode'='${id}'&'cl_pwd'='${pwd}'`;
	console.log(clientData);
	postAjaxForm('clientOrder','ajaxToServerResponse','s',clientData);
}


function postAjaxJson2(jobCode,fn,clientData="") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			window[fn](ajax.responseText);
			//appvue.serverResponse(ajax.responseText);
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/json; charset=utf-8");
	console.log(clientData);
	ajax.send(clientData);
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
	clientData에 리스트를 넘긴다 = []에 push한 값, 단순 한개 이상의 키:밸류 = {키:밸류,키:밸류},한개의 값만 넘긴다 = 키:밸류
	백엔드 컨트롤러에서는 @RequestBody로 받으면됨
	*/
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
	clientData=`변수명(빈.OO)=${변수값}&변수명(빈.OO)=${변수값}`;
	백엔드 컨트롤러에서는 @ModelAttribute으로 받으면됨(빈도 받을 수 있음)
	*/
}
