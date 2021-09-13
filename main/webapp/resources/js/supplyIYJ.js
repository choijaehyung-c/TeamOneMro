const supplyVue = new Vue({
	el:"#supplyVue",
	data :{
		display :[{show:false},{show:false},{show:false},{show:false},{show:false}],
		modal:{show:false},
		modalDetailList:[],
		modalDetail:{},
		list:[],
		dupCheck:[]
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
			//let rd_note =prompt("거절사유를 입력해주세요.");
			let sendData=[];
			let rd = [];
			let rdnote = document.getElementsByName("rd_note");
			let prcode = document.getElementsByName("rd_prcode");
			sendData.push({re_code:recode,rd:rd});
			
			for(i=0; i<rdnote.length;i++){
				let rdd = {rd_note:rdnote[i].value,rd_prcode:prcode[i].value};
				rd.push(rdd);
			}
			
				//rd.push({ rd_note: values, rd_prcode: prcode});
				//rd.push({rd_note:values,rd_prcode:prcode});
				let ClientData = JSON.stringify(sendData);

				postAjaxJson('vue/supplyResponseOrderOF', 'receiveOrderControll2', 's', ClientData);
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

	
		refusePage:function(){//수주거절 목록
			postAjaxJson('vue/getSupplyRefuseOrderList','getRefuseList','j');
		},
		refuseListDetail:function(recode){
			postAjaxJson('vue/getSupplyRefuseOrderD','getReceiveListD3','j',recode);
		},
		trackDeliveryPage:function(){//배송 출발 목록
			postAjaxJson('vue/getTrackDeliveryList','getTrackingDelivery','j');	
		},
		searchWord:function(){
			let word = document.getElementById("INPUT");
			alert(word.value);
		},
		getCheckedVal:function(){
				const query = 'input[name="choose"]:checked';
				const selected = document.querySelectorAll(query);				
					let result ='';
					selected.forEach((el)=>{
					result+= el.value + ' ';		
				});
					return result;
					
		},
		deliveryState:function(recode){
			alert(recode+"의 배송상태");
			postAjaxJson('vue/getTrackDL','getTrackingDL','j',recode);
			
		},
		 insReason:function(index,prcode){
         console.log(index+"a"+prcode);
         if(this.dupCheck.includes(index))return;
         let updown = 0;
         
         for(i=0;i<this.dupCheck.length;i++){
            if(this.dupCheck[i] > index){
               updown -= 1;
            }
         }
         let modal = document.getElementById('datatablesSimple');
         let newRow = modal.insertRow(this.dupCheck.length+2+index+updown);
         newRow.id=`del${index}`;
         let newCell1 = newRow.insertCell(0);
         let newCell2 = newRow.insertCell(1);
         newCell1.colSpan = "6";
         newCell1.innerHTML = `<input type="text" name="rd_note" style="width:100%;" placeholder="거절 사유 입력"/>
                           <input type="hidden" name="rd_prcode" value="${prcode}"/>`;
         newCell2.innerHTML = `<div id="del${index}" onclick="delReason(${index})">삭제</div>`;
         this.dupCheck.push(index);
      },
	}
});



//onLoad
function change1(){
	supplyVue.changePage(4);
}

function delReason(index){
    $(`#del${index}`).remove();
   for(i=0;i<supplyVue.dupCheck.length;i++){
      if(supplyVue.dupCheck[i]==index){
         supplyVue.dupCheck.splice(i);
      }
   }
}
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
		supplyVue.orderListPage();
	}else{
		supplyVue.orderListPage();
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
//수주거절 디테일목록
function getReceiveListD3(data){
	supplyVue.modalDetailList=data;
	supplyVue.changeWon();
	supplyVue.modalOpen();
}

//배송출발 페이지
function trackDelivery(){
	supplyVue.trackDeliveryPage();
}

//배송출발 목록
function getTrackingDelivery(jsondata){
	supplyVue.list=jsondata;
	supplyVue.changePage(3);
}

//배송 추적
function getTrackingDL(jsondata){
	supplyVue.modalDetailList = jsondata;
	supplyVue.modalOpen();
}