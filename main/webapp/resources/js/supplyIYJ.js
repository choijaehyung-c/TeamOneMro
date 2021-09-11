const supplyVue = new Vue({
	el:"#supplyVue",
	data :{
		display :[{show:false},{show:false},{show:false}],
		modal:{show:false},
		modalDetailList:[],
		modalDetail:{},
		list:[]
	},
	methods:{
		changePage:function(page){
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[page].show=true;
		},
		modalOpen:function(){
			this.modal.show=true;
		},
		modalClose:function(){
			this.modal.show=false;
		},
		/////////////////////////////////////////
		orderListPage:function(){//수주대기목록
			postAjaxJson('vue/getSupplyReceiveWaitOrderList','getReceiveList','j');			
		},
		orderListDetail:function(recode){//수주대기 디테일	
		postAjaxJson('vue/getSupplyReceiveWaitOrderListD','getReceiveListD','j',recode);		
		},
		respondOA:function(recode){ //수주대기 -  주문수락
			sendData={re_code:recode};
			let clientData = JSON.stringify(sendData);
			postAjaxJson('vue/supplyResponseOrderOA','receiveOrderControll2','s',clientData);
		},
		respondOF:function(recode){//수주대기 - 주문거절
			let rd_note =prompt("거절사유를 입력해주세요.");
			let sendData=[];
			let rd = [];
			sendData.push({re_code:recode,rd});
			
			let values =rd_note;
			rd.push({rd_note:values});
			let ClientData = JSON.stringify(sendData);
			postAjaxJson('vue/supplyResponseOrderOF','receiveOrderControll2','s',ClientData);	
			alert(ClientData);		
		},	
		/////////////////////////////////////////
		deliveryListPage:function(){//접수완료목록
			postAjaxJson('vue/getSupplyReceiveClearOrderList','getReceiveListC','j');
		},		
		deliveryListDetail:function(recode){//접수완료 디테일	
			postAjaxJson('vue/getSupplyReceiveClearOrderListD','getReceiveListD2','j',recode);					
		},
		changeWon:function(){//가격 콤마찍기
				for(i=0; i<this.modalDetailList.length;i++){
				this.modalDetailList[i].pr_price = this.modalDetailList[i].pr_price.toLocaleString();
				this.modalDetailList[i].pr_tax = this.modalDetailList[i].pr_tax.toLocaleString();
				this.modalDetailList[i].pr_ttprice =this.modalDetailList[i].pr_ttprice.toLocaleString();
			}
		},       
		respond2:function(recode){ //출고처리
			alert(recode);
		},
		refusePage:function(){//수주거절 목록
			postAjaxJson('vue/getSupplyRefuseOrderList','getRefuseList','j');
		},
		refuseListDetail:function(recode){
			postAjaxJson('vue/getSupplyRefuseOrderD','getReceiveListD3','j',recode);
		}
      
	}
	
});


/////////////////////////////////////////////////
//수주대기목록페이지 (사이드바 클릭시)
function orderWaitList(){
	supplyVue.orderListPage();
}

//수주대기목록페이지 - 리턴
function getReceiveList(data){
	supplyVue.list = data;
	supplyVue.changePage(0);
}
//수주대기 목록 디테일
function getReceiveListD(data){
	supplyVue.modalDetailList = data;
	supplyVue.changeWon();
	supplyVue.modalOpen();
}

//수주 접수 응답 - 리턴
function receiveOrderControll2(msg){
	if(msg!=""){
		alert(msg);
		supplyVue.modalClose();
		supplyVue.changePage(0);
	}else{
		supplyVue.changePage(0);
	}
}

//수주접수완료 목록(사이드바 클릭시)
function orderReceiveList(){
	supplyVue.deliveryListPage();
	
}

//수주접수완료 목록 - 리턴
function getReceiveListC(jsonData){
	supplyVue.list= jsonData;
	supplyVue.changePage(1);
	
}
//수주접수완료 목록 - 디테일
function getReceiveListD2(data){
	supplyVue.modalDetailList= data;
	supplyVue.changeWon();
	supplyVue.modalOpen();
}

//수주거절 목록 페이지 
function orderRefuseList(){
	supplyVue.refusePage();
}

//수주거절 목록 
function getRefuseList(jsondata){
	supplyVue.list=jsondata;
	supplyVue.changePage(2);
}

function getReceiveListD3(data){
	supplyVue.modalDetailList=data;
	supplyVue.changeWon();
	supplyVue.modalOpen();
}