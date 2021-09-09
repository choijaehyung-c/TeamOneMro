const mainVue = new Vue({
	el:"#mainVue",
	data:{
		page:[{show:false},{show:false},{show:false}],
		supplyList:[],
		clientList:[]
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		supplyListPage:function(){//공급사 리스트 페이지
			this.resetPage();
			this.page[0].show=true;
			postAjaxJson('vue/mroSupplyListForm','supplyListVue','j');
		},
		datata:function(data){//모달창
			//alert(data);
			this.page[1].show=true;			
		},
		clientListPage:function(){//고객사 페이지
			this.resetPage();
			this.page[2].show=true;
			postAjaxJson('vue/mroClientListForm','clientListVue','j');
		},
		data:function(data){
			//alert(data);
			
		},
		search:function(){//공급사 검색
		 	let word = document.getElementsByName("word")[0].value;
			//alert(word);
			postAjaxJson('vue/searchSupply','supplyListVue','j', word);//돌아오는 View가 다르고 테이블이 달라서...
		},
		searchClient:function(){//고객사 검색
			let word = document.getElementsByName("wordC")[0].value;		
			postAjaxJson('vue/searchClient','clientListVue','j', word);
		},
		add:function(){//공급사 추가
			let name = document.getElementsByName("sp_name")[0].value;
			let code = document.getElementsByName("sp_code")[1].value;
			let add = document.getElementsByName("sp_address")[0].value;
			let tel = document.getElementsByName("sp_tel")[0].value;
			let corpnum = document.getElementsByName("sp_corpnum")[0].value;
			let btype= document.getElementsByName("sp_btype")[0].value;
			let bkind= document.getElementsByName("sp_bkind")[0].value;
			//let pwd = document.getElementsByName("sp_pwd")[0].value; //Bean에 추가해야함
			
			let sendJsonData = {sp_name:name, sp_code:code, sp_address:add, sp_tel:tel, sp_corpnum:corpnum, sp_btype:btype, sp_bkind:bkind};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/addClient','clientListVue2','j', clientData);
			alert(clientData);
		},
		addC:function(){//고객사 추가
			let name = document.getElementsByName("cl_name")[0].value;
			let code = document.getElementsByName("cl_code")[0].value;
			let add = document.getElementsByName("cl_address")[0].value;
			let hp = document.getElementsByName("cl_hp")[0].value;
			let pwd = document.getElementsByName("cl_pwd")[0].value;
			let corpnum = document.getElementsByName("cl_corpnum")[0].value;
			let btype = document.getElementsByName("cl_btype")[0].value;
			let bkind = document.getElementsByName("cl_bkind")[0].value;
			
			let sendJsonData = {cl_name:name, cl_code:code, cl_address:add, cl_hp:hp, cl_corpnum:corpnum, cl_btype:btype, cl_bkind:bkind,cl_pwd:pwd};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/addClient','clientListVue2','j', clientData);
			alert(clientData);			
		},
		deleteC:function(code){//고객사 삭제
			alert(code);
			postAjaxJson('vue/delClient','clientListVue2','s', code);
		},
		deleteS:function(code){//공급사 삭제
			alert(code);
			postAjaxJson('vue/delSupply','supplyListVue2','s', code);
		}
		
	}
});

$(function(){
	$('.form-control').reset();
});
function supplyList(){
	mainVue.supplyListPage();
}

function supplyListVue(jsondata){
	if(jsondata!=""){
		mainVue.supplyList = jsondata;
	}else{
		alert("검색어에 해당되는 공급업체가 없습니다.");
	}
}

function supplyListVue2(msg){
	if(msg!=""){
		alert(msg);
		mainVue.supplyListPage();
	}
}

function clientList(){
	mainVue.clientListPage();
}

function clientListVue(data){
	if(data!=""){
		mainVue.clientList = data;
   }else{
		alert("검색어에 해당되는 고객사가 없습니다.");
 }
}

function clientListVue2(msg){
	if(msg!=""){
		alert(msg);
		mainVue.clientListPage();
	}else{
		
	}
}

//input 박스 텍스트없애기 -아직안됨..
function close(){

var el = document.getElementsByClassName("text");

for(var i=0; i<el.length; i++){

	el[i].value = '';

}
}




//(jobCode, fn, rType, clientData = "") {
	/*	changeHome1:function(){
			console.log("imin");
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[2].show = true;
			
		},*/