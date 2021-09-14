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
	let tData1 = ['1039672976','1039672975','1039171655','1039171799','1037946894','1037947505']KR001G
	let tData2 = ['1038908322'
1038768782
1038641866
1037532711
1039694563] 
	let OD = [];
	for(i=0;i<3;i++){
		OD.push({od_prspcode:"KR001D",od_prcode:"123123123"+i,od_quantity:i+1});
	}


	let clientData ={os_clcode:id,cl_pwd:pwd,od:OD};
	console.log(clientData.od[0].od_quantity);
	postAjaxJson('clientOrder','ajaxToServerResponse','s',JSON.stringify(clientData));
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
