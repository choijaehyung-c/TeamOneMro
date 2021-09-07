/**
 * 
 */
let Home1 = {
  template: `<p>Welcome home!</p>`
}

let Home2 = {
  template: `<div><p v-for="item in list">{{item}}</p></div>`
	,
	data(){
		return{
			list:{a:1,b:2,c:3}
		}
	}
}

let Home3 = {
  template: `<div><input type="text" value="" v-model="msg"/>
			<br><p>{{msg}}{{aaqqd[0].aa2}}</p></div>`,
	data(){
		return{
			msg:'hi'
		}
	},
	props: ['aaqqd'],
}


const main = new Vue({
  el: '#supplyVueZone',
  data: {
    currentView: this.mycomponent,
	msg:"asddd",
	mssg:[
		{aa1:"aa",
		aa2:"bb"}
	],
	display:[{show:false},{show:true},{show:false}],
	/*display:{
		vue1:false,
		vue2:true
	}*/
	newdata:{},
	newss:""
	
  },
	methods:{
		/*changeHome1:function(){
			this.currentView = Home1;
		},
		changeHome2:function(){
			this.currentView = Home2;
		},
		changeHome3:function(){
			this.currentView = Home3;
		},
		hhh:function(){
			this.aa2 = "cc";
		}*/
		changeHome1:function(){
			console.log("imin");
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[2].show = true;
			
		},
		changeHome2:function(){
			this.currentView = Home2;
		},
		changeHome3:function(){
			this.currentView = Home3;
		},
		hhh:function(){
			this.aa2 = "cc";
		},
		sdfgsdfg:function(ddd){
			this.newdata = ddd;
		}
	}
});

/*function change3(){
	main.mssg.aa2="cc";
	main.changeHome3();
}
function change1(){
	main.changeHome1();
}
function change2(){
	main.changeHome2();
}

*/
function change3(){
	main.changeHome3();
}
function change1(){
	main.changeHome1();
}
function change2(){
	main.changeHome2();
}

function getCateProduct(){
	let cdata = {"pr_spcode":"KR002C","cate":"TC"};
	postAjaxJson('getSupplyCateProductList','tetessettse','j',JSON.stringify(cdata));
}

function tetessettse(ddd){
	console.log(ddd);
}

// jsp 버튼 js 함수

function asdasd(){
	postAjaxJson()
}

function asdasd(jsondata){
	main.sdfgsdfg(jsondata);
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
