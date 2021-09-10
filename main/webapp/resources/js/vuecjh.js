/**
 * 
 */


const main = new Vue({
  el: '#supplyVueZone',
  data: {
	display:[{show:false},{show:false}],
	list:[]
  },
	methods:{
		changeForm:function(page){
			console.log("imin");
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[page].show = true;
		},
		pushData:function(jsondata){
			this.list=jsondata;
		}
	}
});


function getRefundListForm(){
	postAjaxJson("vue/supplyReceiveRefundListForm","getRefundList", "j");
}
function getRefundList(jsondata){
	main.changeForm(0);
	main.pushData(jsondata);
}

function getExchangeListForm(){
	
}

function getCateProduct(){
	let cdata = {"pr_spcode":"KR002C","cate":"TC"};
	postAjaxJson('getSupplyCateProductList','tetessettse','j',JSON.stringify(cdata));
}

function tetessettse(ddd){
	console.log(ddd);
}



function uploadFileAjax(){
	let file = document.getElementsByName("file1")[0];
	let multitext = document.getElementsByName("multitext")[0];
	let ajax = new XMLHttpRequest();
	
	ajax.onreadystatechange=function(){
		if(ajax.readyState==4 && ajax.status == 200){
			let data = ajax.responseText;
			upupupup(data);
		}
	}
	ajax.open("POST","schedule/fileUpload");
	
	let formData = new FormData();
	console.log(file.files.length);
	
	for(i=0;i<file.files.length;i++){
	formData.append('file1',file.files[i],file.files[i].name);}
	formData.append('multitext',multitext.value);
	
	ajax.send(formData);
}
