const supplyVue = new Vue({
	el:"#supplyVue",
	data:{
		page:[{show:false},{show:false},{show:false},{show:false}],
		orderList:[],
		orderDetail:[],
		deliveryList:[],
		deliveryDetail:[]
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		orderListPage:function(){
			this.resetPage();
			this.page[0].show=true;
			postAjaxJson('vue/getSupplyReceiveWaitOrderList','getReceiveList','j');
		
			
		},
		deliveryListPage:function(){
			this.resetPage();
			this.page[2].show=true;
			postAjaxJson('vue/getSupplyReceiveClearOrderList','getReceiveListC','j');
		},
		data:function(recode){				
		postAjaxJson('vue/getSupplyReceiveWaitOrderListD','getReceiveListD','j',recode);		
		this.page[1].show=true;	
		},
		close:function(num){
         this.page[num].show=false;
         
      },
		respondOA:function(recode){ //주문수락
			sendData={re_code:recode};
			let clientData = JSON.stringify(sendData);
			postAjaxJson('vue/supplyResponseOrderOA','receiveOrderControll2','s',clientData);
			//alert(clientData);
		},
		
		respondOF:function(recode){
			sendData={re_code:recode};
			let ClientData = JSON.stringify(sendData);
			postAjaxJson('vue/supplyResponseOrderOF','receiveOrderControll2','s',clientData);
		},
		changeWon:function(){
				for(i=0; i<this.orderDetail.length;i++){
				this.orderDetail[i].pr_price = this.orderDetail[i].pr_price.toLocaleString();
				this.orderDetail[i].pr_tax = this.orderDetail[i].pr_tax.toLocaleString();
				this.orderDetail[i].pr_ttprice =this.orderDetail[i].pr_ttprice.toLocaleString();
			}
		},
		data2:function(recode){			
			postAjaxJson('vue/getSupplyReceiveClearOrderListD','getReceiveListD2','j',recode);	
			this.page[3].show=true;		
			
		},
		changeWon2:function(){
			for(i=0; i<this.deliveryDetail.length;i++){
				this.deliveryDetail[i].pr_price = this.deliveryDetail[i].pr_price.toLocaleString();
				this.deliveryDetail[i].pr_tax = this.deliveryDetail[i].pr_tax.toLocaleString();
				this.deliveryDetail[i].pr_ttprice =this.deliveryDetail[i].pr_ttprice.toLocaleString();
			}
			
		},
		respond2:function(recode){
				alert(recode);
			}
		
	}
		
});

//수주대기목록페이지
function orderWaitList(){
	supplyVue.orderListPage();
}

//수주대기목록페이지 - 리턴
function getReceiveList(data){
	supplyVue.orderList = data;
}
//수주대기 목록 디테일
function getReceiveListD(data){
	supplyVue.orderDetail = data;
	supplyVue.changeWon();
}

//수주 접수 응답 - 리턴
function receiveOrderControll2(msg){
		if(msg!=""){
			alert(msg);
			supplyVue.close(1);
			supplyVue.orderListPage();
	}	
}

//수주접수완료 목록
function orderReceiveList(){
	supplyVue.deliveryListPage();
}

//수주접수완료 목록 - 리턴
function getReceiveListC(jsonData){
	supplyVue.deliveryList= jsonData;
	
}

function getReceiveListD2(data){
	supplyVue.deliveryDetail= data;
	supplyVue.changeWon2();
}