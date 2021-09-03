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
	let id = document.getElementsByName("OS_CLCODE")[0].value;
	let pwd = document.getElementsByName("CL_PWD")[0].value;
	
	console.log(pwd);
	/*
	let OD = [];
	for(i=0;i<3;i++){
		OD.push({OD_PRSPCODE:"KR001D",OD_PRCODE:"123123123"+i});
	}
	*/
	let clientData = {osclCode:id,cl_Pwd:pwd};
	//console.log(clientData.OS_CLCODE+clientData.CL_PWD)
	postAjaxJson('clientOrder','ajaxToServerResponse',JSON.stringify(clientData));
}


function postAjaxJson(jobCode,fn,clientData="") {
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

