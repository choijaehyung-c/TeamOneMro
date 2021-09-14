/**
 * 
 */
const appvue = new Vue({
	el:"#vuezone",
	data:{
		msg:[]
	},
	methods:{
		serverResponse:function(ddata){
			this.msg = ddata;
		}
	}
});

function ajaxToServerResponse(data){
	appvue.serverResponse(data);
}


function sendApiData(){
	let id = document.getElementsByName("os_clcode")[0].value;
	let pwd = document.getElementsByName("cl_pwd")[0].value;
	let tData1 = ['1039672976','1039672975','1039171655','1039171799','1037946894','1037947505'];
	let tData2 = ['1038908322','1038768782','1038641866','1037532711','1039694563'];
	let OD = [];
	
	for(i=0;i<tData1.length;i++){
		OD.push({od_prspcode:"KR001G",od_prcode:tData1[i],od_quantity:i+1});
	}
	for(i=0;i<tData2.length;i++){
		OD.push({od_prspcode:"KR001D",od_prcode:tData2[i],od_quantity:i+1});
	}

	let clientData ={os_clcode:id,cl_pwd:pwd,od:OD};
	console.log(clientData.od[0].od_quantity);
	postAjaxJson('clientOrder','ajaxToServerResponse','s',JSON.stringify(clientData));
}

