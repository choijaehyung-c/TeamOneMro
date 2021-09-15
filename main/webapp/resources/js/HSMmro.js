const mainVue = new Vue({
	el:"#mainVue",
	data:{
		page:[{show:false},{show:false},{show:false},{show:false}],
		getNewProductRequestList:[],
		mroGetNewProductDetail:{},
		modifyRequestList:[],
		mroGetModifyRequestDetail:{}
		
		
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		getNewProductRequestPage:function(){//상품등록신청 리스트 불러오기
			this.resetPage();
			postAjaxJson('vue/GetRequestRegisterNewProductList', 'getNewProductRequestVue', 'j');
			this.page[0].show=true;
		},
		getNewProductRequestListPush:function(jsondata){
			this.getNewProductRequestList = jsondata;
		},
		mroGetNewProductDetailPage:function(prcode){//해당 상품등록신청 디테일 보기 
			let sendJsonData = {pr_code:prcode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/MroGetNewProductDetail','mroGetNewProductDetailVue', 'j', clientData);
			this.page[1].show=true;
		},
		mroGetNewProductDetailPush:function(jsondata){
			this.mroGetNewProductDetail = jsondata;
		},
		modalClose:function(num){
			this.page[num].show=false;
		},
		mroResponseNewProduct:function(prcode, prstcode){
			let sendJsonData = {pr_code:prcode, pr_stcode:prstcode};
			let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('vue/MroResponseNewProduct','getNewProductRequest', 's', clientData);
			this.page[1].show = false;
		},
		
		getModifyRequestPage:function(){//상품수정신청리스트 불러오기
			this.resetPage();
			postAjaxJson('vue/CallModifyRequestList', 'getModifyRequestListVue', 'j');
			this.page[2].show=true;
		},
		getModifyRequestListPush:function(jsondata){
			this.modifyRequestList = jsondata;
		},
		mroGetModifyProductDetailPage:function(prcode, num){
			let stcode = "";
			if(num== "1"){
				stcode = "MR";
			}else{
				stcode = "DR";
			}
			let sendJsonData = {pr_code:prcode, pr_stcode:stcode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/MroGetModifyProductDetail','mroGetModifyProductDetailVue', 'j', clientData);
			this.page[3].show=true;	
		},
		mroGetModifyRequestDetailPush:function(jsondata){
			this.mroGetModifyRequestDetail = jsondata;
		},
		mroResponseModifyProduct:function(prcode, code, num){
			alert(code);
			let prstcode = "";
			if(code == "MR"){
				if(num == "1"){
					prstcode = "PC";
				}else{
					prstcode = "RF";
				}
			}else{
				if(num == "1"){
					prstcode = "DA";
				}else{
					prstcode = "DF";
				}
			}
			let sendJsonData = {pr_code:prcode, pr_stcode:prstcode};
			let clientData = JSON.stringify(sendJsonData);
			alert(clientData);
			postAjaxJson('vue/MroResponseModifyProduct','getModifyRequest', 's', clientData);
			this.page[3].show = false;
		}
	
	}
});



//상품등록신청리스트
function getNewProductRequest(message){
	if(message !=""){
		alert(message);
		mainVue.getNewProductRequestPage();
	}{
		mainVue.getNewProductRequestPage();
	}
}

function getNewProductRequestVue(jsondata){
	mainVue.getNewProductRequestListPush(jsondata);
}


function mroGetNewProductDetailVue(jsondata){
	//alert(JSON.stringify(jsondata));
	mainVue.mroGetNewProductDetailPush(jsondata) ;
}

//상품수정신청리스트
function getModifyRequest(message){
	if(message !=""){
		alert(message);
		mainVue.getModifyRequestPage();
	}{
		mainVue.getModifyRequestPage();
	}
}
function getModifyRequestListVue(jsondata){
	mainVue.getModifyRequestListPush(jsondata);
}

function mroGetModifyProductDetailVue(jsondata){
	mainVue.mroGetModifyRequestDetailPush(jsondata);
}










///딜리버리테스트//////////////////////////////////////////////////////
function callDeliveryList(){
	let sendJsonData = {dl_dvcode:"IYJ032"};
	let clientData = JSON.stringify(sendJsonData);
	alert(clientData);
	postAjaxJson('vue/DeliveryTest','getDeliveryListPush', 'j', clientData);
}

function getDeliveryListPush(jsondata){
	alert(JSON.stringify(jsondata));
	deliveryVue.pushData(jsondata);
	deliveryVue.deliveryPage[0].show = true;
}

const deliveryVue = new Vue({
	el:"#deliveryVue",
	data:{
		deliveryPage:[{show:false}],
		deliveryList:[],
	},
	methods:{
		pushData:function(jsondata){
			this.deliveryList = jsondata;
		},
		insertsdcode:function(dlcode, dscode){
			let sendJsonData = {dl_code:dlcode, dl_dscode:dscode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/Insertsdcode','callDeliveryList', 's', clientData);
		}
		
	}
	});





