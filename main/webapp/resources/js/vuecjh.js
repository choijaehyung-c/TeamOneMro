/**
 * 
 */


const main = new Vue({
  el: '#supplyVue',
  data: {
	display:[{show:false},{show:false},{show:false},{show:false},{show:false},{show:false},{show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false},{show:false/*거래내역리스트*/},{show:false/*세금계산서페이지*/}
		,{show:false/*고객사정보기입*/}
		,{show:false/*세금계산서 거래내역기입*/},{show:false/*세금계산서 발행내역*/},{show:false}],
	modal: { show: false },
	modal2:{show:false},
	list:[],
	modalList:[],
	dupCheck:[],
	modalDetailList:[],
	  categoryList2:[],
	  categoryList3:[],
      detail:{},
	  categoryCode:'',
	  searchWord:'',
	  clbean:{},
	  spbean:{},
	  modalDealList:[],
	  modalCLList:[],
	  tbbean:{},
	  od:[]
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
		modalOpen2:function(){
			this.modal2.show=true;
		},
		modalClose: function() {
			this.modal.show = false;
			this.modal2.show = false;
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
		 postAjaxJson('vue/supplyGetBK','getBK','j');
      },
      supplyGetCategoryPush:function(jsondata){
         this.categoryList2 = jsondata;
      },
      supplyRequestNewProduct:function(){
		let name = document.getElementsByName("pr_name")[0];
		let price = document.getElementsByName("pr_price")[0];	
		let stock = document.getElementsByName("pr_stock")[0];
		
			if(name.value==""){
				alert("상품명 필수입력사항입니다.");				
				name.focus();
				return;
			}else if(name.value.length>200){
				alert("상품명은 1~200자 이내로 입력해주세요.");
				name.value="";
				name.focus();
				return;
			}
			
			if(!isValidateCheck(1,price.value)){
				alert("가격은 필수입력사항입니다.(숫자만 가능하며, 7자리까지 가능합니다.)");
				price.value="";
				price.focus();
				return;			
			}

			
			if(!isValidateCheck(2,stock.value)){					
				alert("수량은 필수입력사항입니다.(숫자만 가능하며, 5자리까지 등록가능합니다.)");
				stock.value="";
				stock.focus();
				return;
				
			}
	
		 postAjaxMultiUpload('vue/SupplyRequestNewProduct','reSupplyPRAFProductListPage');
        
      },
      search1:function(word){
         this.searchWord = word.target.value;
         this.changePage(6);
         this.display[11].show = true;
      },
      search2:function(word){
         this.searchWord = word.target.value;
         this.changePage(9);
         this.display[13].show = true;
      },
      search3:function(word){
         this.searchWord = word.target.value;
         this.changePage(10);
         this.display[15].show = true;
      },
	  //거래내역탭
	  supplyDealListPage:function(){			
	  	postAjaxJson('vue/getSupplyDealList','DealListVue','j');
	  },
	  SupplyDealDetail:function(recode){											
			postAjaxJson('vue/getSupplyDealDetail','DealListDetailVue','j', recode);			
		},
		searchDeal:function(){//공급사 검색
        let word = document.getElementsByName("word")[0].value;
        postAjaxJson('vue/getSearchSupplyDeal','searchVue','j', word);//돌아오는 View가 다르고 테이블이 달라서...
     	 },
		
		////////////////////////////////////////
		//세금계산서탭
		supplyIssueTaxbillPage: function(){
			postAjaxJson('vue/getChoiceSPInfo','InputSPVue','j');
		},		
		supplyIssueTaxbillPage2: function(message){
			this.changePage(17);
					
		},
		getClientInfo:function(){
			postAjaxJson('vue/getTaxCL','ClientListlVue','j');
		},		
		inputClientInfo:function(cl_code){
			this.modalClose();
			postAjaxJson('vue/getchoiceCLInfo','InputCLVue','j',cl_code);		
		},
		getTaxDeal:function(){
			postAjaxJson('vue/getTaxDeal','TaxDealListVue','j');
		},
		inputDeal:function(recode){
			this.modalClose();
			postAjaxJson('vue/getSupplyDealDetail','inputDealVue','j', recode);
		},
		issuedTaxbillPage:function(){
			postAjaxJson('vue/getIssuedTax','IssuedTaxVue','j');
		},
		taxDetail:function(tbcode){		
			postAjaxJson('vue/getIssuedTaxDetail','IssuedTaxDetailVue','j',tbcode);
		},
		///////////////////////////////////////////////////
		//세금계산서 발행
		issueTax:function(ttprice){
			let sendJsonData = {rdb:this.modalDealList, sb:this.spbean, cb:this.clbean, tb_ttprice:ttprice};
			let clientData = JSON.stringify(sendJsonData);		
			
			postAjaxJson('vue/issueTax','supplyIssueTaxbill2','s', clientData);			
		},
		mainPage:function(){
			postAjaxJson('vue/getChart','gettingChart','j');
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

function getBK(jsondata){
	main.categoryList3 = jsondata;
	postAjaxJson('vue/supplyGetCategory','getCate2','j');
}


/****************************************************************************/
function mainPage(){//onLoad
	main.mainPage();
	main.changePage(21);
}



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
//노승백
//탭눌렀을때 펑션들
function supplyDealList(){
	main.supplyDealListPage();
}

function supplyIssueTaxbill(){
	main.supplyIssueTaxbillPage();
}

function supplyIssueTaxbill2(message){
	if(message!=""){
		main.supplyIssueTaxbillPage2(message);
		
	}else{
		alert("fail");
	}
}

function supplyIssueTaxbillListForm(){
	main.issuedTaxbillPage();
}	
//////////////////////////////////////////////////////////


//거래내역 페이지 이동해서 거래내역리스트 출력
function DealListVue(jsondata){
	main.list = jsondata;
	main.changePage(16);
}
//거래내역 디테일 모달로 리스트 출력
function DealListDetailVue(jsondata){
	main.modalDealList = jsondata;
	main.modalOpen();
}

//거래내역 검색
function searchVue(jsondata){
   if(jsondata!=""){
      main.list = jsondata;
	  main.changePage(16);
   }else{
      alert("검색어에 해당되는 거래내역이 없습니다.");
   }
}

//세션으로 공급사정보 빈 기입
function InputSPVue(jsondata){	
	main.spbean = jsondata;
	main.changePage(17);
}

//고객사리스트 모달로 출력
function ClientListlVue(jsondata){	
	main.modalCLList = jsondata;
	main.modalOpen();
}
//고객사 정보 빈 기입
function InputCLVue(jsondata){	
	main.clbean = jsondata;
	main.display[17].show=true;
	main.display[18].show=true;
}
//거래내역 모달로 출력
function TaxDealListVue(jsondata){
	main.list = jsondata;
	main.modalOpen2();
}

function inputDealVue(jsondata){
	main.modalDealList = jsondata;
	main.display[17].show=true;
	main.display[18].show=true;
	main.display[19].show=true;
}

function IssuedTaxVue(jsondata){
	main.list = jsondata;
	main.changePage(20);
}

function IssuedTaxDetailVue(jsondata){
	
	main.tbbean = jsondata;
	main.od = jsondata.od;	
	main.modalOpen();
}



//유효성 검사
function charCount(value, min, max){
	return value.length >= min && value.length<=max;
	
}

function isValidateCheck(type,word){
	//const pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	const priceNum =  /^[0-9]{1,7}$/;
	const stockNum = /^[0-9]{1,5}$/;
	let result;
	
	if(type == 1){
		result = priceNum.test(word);
		console.log(word);
		console.log(result);
	}else if(type == 2){
		result = stockNum.test(word);
	}
	
	return result;
}



