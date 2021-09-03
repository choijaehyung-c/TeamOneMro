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
	/*
	let OD = [];
	for(i=0;i<3;i++){
		OD.push({OD_PRSPCODE:"KR001D",OD_PRCODE:"123123123"+i});
	}
	*/
	//let clientData = cl_Pwd:pwd;
	//console.log(clientData.OS_CLCODE+clientData.CL_PWD)
	postAjaxJson('clientOrder','ajaxToServerResponse',JSON.stringify(clientData));
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
