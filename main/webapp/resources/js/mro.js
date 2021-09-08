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
		supplyListPage:function(){
			this.resetPage();
			this.page[0].show=true;
			postAjaxJson('vue/mroSupplyListForm','supplyListVue','j');
		},
		datata:function(data){
			//alert(data);
			this.page[1].show=true;			
		},
		clientListPage:function(){
			this.resetPage();
			this.page[2].show=true;
			postAjaxJson('vue/mroClientListForm','clientListVue','j');
		},
		data:function(data){
			//alert(data);
			
		},
		search:function(){//검색
		 	let word = document.getElementsByName("sp_code")[0].value;
			alert(word);
			postAjaxJson('vue/searchSupply','supplyListVue','j', word);
		},
		add:function(){
			let name = document.getElementsByName("sp_name")[0].value;
			let code = document.getElementsByName("sp_code")[1].value;
			let add = document.getElementsByName("sp_address")[0].value;
			let tel = document.getElementsByName("sp_tel")[0].value;
			let corpnum = document.getElementsByName("sp_corpnum")[0].value;
			let btype= document.getElementsByName("sp_btype")[0].value;
			let bkind= document.getElementsByName("sp_bkind")[0].value;
			
			let sendJsonData = {sp_name:name, sp_code:code, sp_address:add, sp_tel:tel, sp_corpnum:corpnum, sp_btype:btype, sp_bkind:bkind};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/addClient','clientListVue2','j', clientData);
			alert(clientData);
		}
		
	}
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

function clientList(){
	mainVue.clientListPage();
}

function clientListVue(data){
	mainVue.clientList = data;
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