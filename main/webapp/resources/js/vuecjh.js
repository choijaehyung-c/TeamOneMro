/**
 * 
 */


const main = new Vue({
  el: '#supplyVue',
  data: {
	display:[{show:false},{show:false},{show:false},{show:false},{show:false},{show:false},{show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}],
	modal: { show: false },
	modal2:{show:false},
	list:[],
	modalList:[],
	dupCheck:[],
	modalDetailList:[],
	  categoryList2:[],
      detail:{},
	  categoryCode:'',
	  searchWord:''
  },
	methods:{
		changePage:function(page){
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.modalClose();
			this.display[page].show = true;
		},
		modalOpen: function() {
			this.modal.show = true;
		},
		modalClose: function() {
			this.modal.show = false;
		},
		pushData:function(jsondata){
			/*console.log(jsondata);*/
			this.list=jsondata;
		},
		pushDataModalList:function(jsondata){
			console.log(jsondata);
			this.modalList=jsondata;
		},
		getAsDetail:function(data,type){
			let cData = `re_code=${data}`;
			if(type=="r"){
			postAjaxForm("vue/supplyReceiveAsDetailR","getAsDetailForm", "j",cData);}
			else{
			postAjaxForm("vue/supplyReceiveAsDetailE","getAsDetailForm", "j",cData);	
			}
		},
		insReason:function(index,code){
			console.log(index+"a"+code);
			if(this.dupCheck.includes(index))return;
			let updown = 0;
			
			for(i=0;i<this.dupCheck.length;i++){
				if(this.dupCheck[i] > index){
					updown -= 1;
				}
			}
			let modal = document.getElementById('modalTable');
			let newRow = modal.insertRow(this.dupCheck.length+2+index+updown);
			newRow.id=`del${index}`;
			let newCell1 = newRow.insertCell(0);
			let newCell2 = newRow.insertCell(1);
			newCell1.colSpan = "4";
			newCell1.innerHTML = `<input type="text" name="rd_note" style="width:100%;" placeholder="거절 사유 입력"/>
									<input type="hidden" name="rd_prcode" value="${code}"/>`;
			newCell2.innerHTML = `<div id="del${index}" onclick="delReason(${index})">삭제</div>`;
			this.dupCheck.push(index);
		},
		sendAsResponse:function(re,yn,type){
			let cData;
			if(yn=="FF" || yn=="EF"){
				let rd = [];
				let rdnote = document.getElementsByName("rd_note");
				let prcode = document.getElementsByName("rd_prcode");
				
				for(i=0;i<rdnote.length;i++){
					let rdd = {rd_note:rdnote[i].value,rd_prcode:prcode[i].value};
					rd.push(rdd);		
				}
				cData = {re_code:re,re_state:yn,rd:rd};
			}else{
				cData = {re_code:re,re_state:yn};
			}
			console.log(cData);
			if(type=="r"){
				postAjaxJson('vue/supplyResponseRefund','getRefundListForm','s',JSON.stringify(cData));
			}else if(type=="e"){
				postAjaxJson('vue/supplyResponseOrder','getExchangeListForm','s',JSON.stringify(cData));
			}
		},
		/////////////////////////////////////////
      orderListPage:function(){//수주대기목록
         postAjaxJson('vue/getSupplyReceiveWaitOrderList','getReceiveList','j');         
      },
      orderListDetail:function(recode){//수주대기 디테일   
      postAjaxJson('vue/getSupplyReceiveWaitOrderListD','getReceiveListD','j',recode);      
      },
      respondOA:function(recode){ //수주대기 -  주문수락
         sendData={re_code:recode,re_state:"OA"};
         let clientData = JSON.stringify(sendData);
         postAjaxJson('vue/supplyResponseOrder','receiveOrderControll2','s',clientData);
      },
      respondOF:function(recode){//수주대기 - 주문거절
         //let rd_note =prompt("거절사유를 입력해주세요.");
         
         let rd = [];
         let rdnote = document.getElementsByName("rd_note");
         let prcode = document.getElementsByName("rd_prcode");
        
         
         for(i=0; i<rdnote.length;i++){
            let rdd = {rd_note:rdnote[i].value,rd_prcode:prcode[i].value};
            rd.push(rdd);
         }

			let sendData= {re_code:recode,re_state:"OF",rd:rd};
         
            //rd.push({ rd_note: values, rd_prcode: prcode});
            //rd.push({rd_note:values,rd_prcode:prcode});
            let ClientData = JSON.stringify(sendData);

            postAjaxJson('vue/supplyResponseOrder', 'receiveOrderControll2', 's', ClientData);
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
       insReason2:function(index,prcode){
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
 	  detailPush:function(jsondata){
         this.modalOpen();
         this.detail = jsondata;   
      },
      supplyAllProductListPush:function(){
         this.changePage(6);
         this.display[7].show=true;
      },
      categoryPoductListPage:function(cate){
         this.categoryCode = cate;
         this.changePage(6);
         this.display[8].show=true;
      },
      productDetail:function(prcode, stcode){
         let sendJsonData = {pr_code:prcode, pr_stcode:stcode};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/SupplyGetProductDetail','detailPush', 'j', clientData);
      },
      supplyModifyStock:function(prcode, prstock){
         let sendJsonData = {pr_code:prcode, pr_stock:prstock};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/SupplyModifyStock','msg', 's', clientData);      
      },
      supplyRequestModify:function(prspcode, prcode, prtax, prspbkind, prstcode, primage, prname, prprice, prstock, prorigin, prinfo, cate, catename){
         let sendJsonData = {pr_spcode:prspcode, pr_code:prcode, pr_tax:prtax,
                  pr_spbkind:prspbkind, pr_stcode:prstcode,
                  pr_image:primage, pr_name:prname,
                  pr_price:prprice, pr_stock:prstock,
                  pr_origin:prorigin, pr_info:prinfo,
                  cate:cate, cate_name:catename};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/SupplyRequestModify','reSupplyAllProductListPage', 's', clientData);
         this.modalClose();
      },
      supplyRequestDelete:function(prspcode, prcode, prtax, prspbkind, prstcode, primage, prname, prprice, prstock, prorigin, prinfo, cate, catename){
         let sendJsonData = {pr_spcode:prspcode, pr_code:prcode, pr_tax:prtax,
                  pr_spbkind:prspbkind, pr_stcode:prstcode,
                  pr_image:primage, pr_name:prname,
                  pr_price:prprice, pr_stock:prstock,
                  pr_origin:prorigin, pr_info:prinfo,
                  cate:cate, cate_name:catename};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/SupplyRequestDelete','reSupplyAllProductListPage', 's', clientData);
         this.modalClose();
      },
      getNewProductDetail:function(prcode){
         let sendJsonData = {pr_code:prcode};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/MroGetNewProductDetail','detailPush', 'j', clientData);
      },
      supplyPRAFProductListPush:function(){
		 this.changePage(9);
         this.display[12].show = true;
      },
      supplyMRDRDAProductListPush:function(){
         this.changePage(10);
         this.display[14].show = true;
      },
      supplyRequestCancel:function(prcode, stcode){
         let sendJsonData = {pr_code:prcode, pr_stcode:stcode};
         let clientData = JSON.stringify(sendJsonData);
         if(stcode == 'PR'||stcode=='AF'){         
            postAjaxJson('vue/SupplyRequestCancel','reSupplyPRAFProductListPage', 's', clientData);
         }else{
            postAjaxJson('vue/SupplyRequestCancel','reSupplyMRDRDAProductListPage', 's', clientData);
         }
      },
      supplyRequestNewProductModal:function(){
         this.modal2.show = true;
         postAjaxJson('vue/supplyGetCategory','getCate2','j');
      },
      supplyGetCategoryPush:function(jsondata){
         this.categoryList2 = jsondata;
      },
      supplyRequestNewProduct:function(){
         let catename = CG.options[CG.selectedIndex].text;
         let sendJsonData = {pr_image:this.pr_image, pr_name:this.pr_name,
                  pr_price:this.pr_price, pr_stock:this.pr_stock,
                  pr_origin:this.pr_origin, pr_info:this.pr_info,
                  cate:this.cate, cate_name:catename};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/SupplyRequestNewProduct','reSupplyPRAFProductListPage', 's', clientData);
      },
      search1:function(word){
         this.searchWord = word.target.value;
         this.changePage(6);
         this.display[11].show = true;
      },
      search2:function(word){
         this.searchWord = word.target.value;
         this.resetPage(9);
         this.display[13].show = true;
      },
      search3:function(word){
         this.searchWord = word.target.value;
         this.changePage(10);
         this.display[15].show = true;
      }
	}
});


const mainVueTwo = new Vue({
   el:"#mainVueTwo",
   data:{
      display:[{show:false}],
      categoryList:[]
   },
   methods:{
      supplyGetCategoryPage:function(){
         postAjaxJson('vue/supplyGetCategory','getCate2','j');                              
         this.display[0].show=true;
      },
      supplyGetCategoryPush:function(jsondata){
         this.categoryList = jsondata;   
      },
      supplyAllProductListPage:function(){
         postAjaxJson('vue/SupplyAllProductList','supplyAllProductListPush','j');   
      },
      callCategoryPoductList:function(cate){
         main.categoryPoductListPage(cate);      
      },
      supplyPRAFProductListPage:function(){
         postAjaxJson('vue/SupplyPRAFProductList', 'supplyPRAFProductListPush', 'j');
      },
      supplyMRDRDAProductListPage:function(){
         postAjaxJson('vue/SupplyMRDRDAProductList', 'supplyMRDRDAProductListPush', 'j');
      }
      
   }
});

function delReason(index){
    $(`#del${index}`).remove();
	for(i=0;i<main.dupCheck.length;i++){
		if(main.dupCheck[i]==index){
			main.dupCheck.splice(i);
		}
	}
}


function getRefundListForm(msg=""){
	if(msg!="")alert(msg);
	postAjaxJson("vue/supplyReceiveRefundListForm","getRefundList", "j");
}

function getExchangeListForm(msg=""){
	if(msg!="")alert(msg);
	postAjaxJson("vue/supplyReceiveExchangeListForm","getExchangeList", "j");
}
/****************************************************************************/
function getRefundList(jsondata){
	main.changePage(0);
	main.pushData(jsondata);
}

function getExchangeList(jsondata){
	main.changePage(1);
	main.pushData(jsondata);
}

function getAsDetailForm(jsondata){
	main.modalOpen();
	main.dupCheck = [];
	main.pushDataModalList(jsondata);
}


/////////////////////////////////////////////////
//수주대기목록페이지 (사이드바 클릭시)
function orderWaitList(){
   main.orderListPage();
}

//수주대기목록페이지 - 리턴
function getReceiveList(data){
   main.list = data;
   main.changePage(2);
}
//수주대기 목록 디테일
function getReceiveListD(data){
   main.modalDetailList = data;
   main.changeWon();
   main.modalOpen();
}

//수주 접수 응답 - 리턴
function receiveOrderControll2(msg){
   if(msg!=""){
      alert(msg);
      main.modalClose();
      main.orderListPage();
   }else{
      main.orderListPage();
   }
}

//수주접수완료 목록(사이드바 클릭시)
function orderReceiveList(){
   main.deliveryListPage();
   
}

//수주접수완료 목록 - 리턴
function getReceiveListC(jsonData){
   main.list= jsonData;
   main.changePage(3);
   
}
//수주접수완료 목록 - 디테일
function getReceiveListD2(data){
   main.modalDetailList= data;
   main.changeWon();
   main.modalOpen();
}


//수주거절 목록 페이지 
function orderRefuseList(){
   main.refusePage();
}

//수주거절 목록 
function getRefuseList(jsondata){
   main.list=jsondata;
   main.changePage(4);
}
//수주거절 디테일목록
function getReceiveListD3(data){
   main.modalDetailList=data;
   main.changeWon();
   main.modalOpen();
}

//배송출발 페이지
function trackDelivery(){
   main.trackDeliveryPage();
}

//배송출발 목록
function getTrackingDelivery(jsondata){
   main.list=jsondata;
   main.changePage(5);
}

//배송 추적
function getTrackingDL(jsondata){
   main.modalDetailList = jsondata;
   main.modalOpen();
}



function getCate2(jsondata){
	console.log(jsondata);
   mainVueTwo.supplyGetCategoryPush(jsondata);
   main.supplyGetCategoryPush(jsondata);
}

function supplyAllProductListPush(jsondata){
   main.pushData(jsondata);
   main.supplyAllProductListPush();
}

function reSupplyAllProductListPage(msg){
   alert(msg);
   mainVueTwo.supplyAllProductListPage();
}

function detailPush(jsondata){
   main.detailPush(jsondata);
}

function supplyPRAFProductListPush(jsondata){
   main.pushData(jsondata);
   main.supplyPRAFProductListPush();
}
function supplyMRDRDAProductListPush(jsondata){
   main.pushData(jsondata);
   main.supplyMRDRDAProductListPush();
}

function reSupplyPRAFProductListPage(msg){
   alert(msg);
   mainVueTwo.supplyPRAFProductListPage();
}
function reSupplyMRDRDAProductListPage(msg){
   alert(msg);
   mainVueTwo.supplyMRDRDAProductListPage();
}

function msg (msg){
   alert(msg);
}























/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



