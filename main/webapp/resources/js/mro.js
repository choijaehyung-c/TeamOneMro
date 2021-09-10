const mainVue = new Vue({
	el:"#mainVue",
	data:{
		display:[{show:false},{show:false},{show:false},{show:false},{show:false/*주문리스트*/},{show:false/*환불리스트*/}
		,{show:false/*교환리스트*/}],
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
		////////////////////////////////////////////////////////////
		modalOpen:function(){
			this.modal.show=true;
		},
		modalClose:function(){
			this.modal.show=false;
		},
		////////////////////////////////////////////////////////////
		getNewProductRequestPage:function(){//상품등록신청 리스트 불러오기
			postAjaxJson('vue/GetRequestRegisterNewProductList', 'getNewProductRequestVue', 'j');
		},
		getNewProductRequestListPush:function(jsondata){
			console.log(jsondata);
			this.list = jsondata;
		},
		////////////////////////////////////////////////////////////
		mroGetNewProductDetailPage:function(prcode){//해당 상품등록신청 디테일 보기 
			let sendJsonData = {pr_code:prcode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/MroGetNewProductDetail','mroGetNewProductDetailVue', 'j', clientData);
		},
		mroGetNewProductDetailPush:function(jsondata){
			this.modalDetail = jsondata;
		},
		////////////////////////////////////////////////////////////
		mroResponseNewProduct:function(prcode, prstcode){
			let sendJsonData = {pr_code:prcode, pr_stcode:prstcode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/MroResponseNewProduct','getNewProductRequest', 's', clientData);
			this.modalClose();
		},
		////////////////////////////////////////////////////////////
		getModifyRequestPage:function(){//상품수정신청리스트 불러오기
			postAjaxJson('vue/CallModifyRequestList', 'getModifyRequestListVue', 'j');
		},
		getModifyRequestListPush:function(jsondata){
			this.list = jsondata;
		},
		////////////////////////////////////////////////////////////
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
		},
		mroGetModifyRequestDetailPush:function(jsondata){
			this.mroGetModifyRequestDetail = jsondata;
		},
		////////////////////////////////////////////////////////////
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
			postAjaxJson('vue/MroResponseModifyProduct','getModifyRequest', 's', clientData);
		},
		
		//////////////////////////////////////////
		supplyListPage:function(){//공급사 리스트 페이지

         postAjaxJson('vue/mroSupplyListForm','supplyListVue','j');
      },

		/////////////////////////////////////////
	    clientListPage:function(){//고객사 페이지
         postAjaxJson('vue/mroClientListForm','clientListVue','j');
      },
		/////////////////////////////////////////
	  search:function(){//공급사 검색
          let word = document.getElementsByName("word")[0].value;
         postAjaxJson('vue/searchSupply','supplyListVue','j', word);//돌아오는 View가 다르고 테이블이 달라서...
      },
		/////////////////////////////////////////
	   searchClient:function(){//고객사 검색
         let word = document.getElementsByName("wordC")[0].value;      
         postAjaxJson('vue/searchClient','clientListVue','j', word);
      },
		/////////////////////////////////////////
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
		/////////////////////////////////////////
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
		/////////////////////////////////////////
      deleteC:function(code){//고객사 삭제
      
         if(confirm("회원을 삭제하시겠습니까?")){
         postAjaxJson('vue/delClient','clientListVue2','s', code);
       }
      },
		/////////////////////////////////////////
      deleteS:function(code){//공급사 삭제
         
         if(confirm("회원을 삭제하시겠습니까?")){
         postAjaxJson('vue/delSupply','supplyListVue2','s', code);
         }
      },
		/////////////////////////////////////////
	mroOrderListPage:function(){			
			postAjaxJson('vue/mroOrderListForm','ListVue','j');
		},
		
		/////////////////////////////////////////
	mroOrderListDetail:function(oscode){											
			postAjaxJson('vue/mroGetOrderDetail','ListDetailVue','j', oscode);
			
		},
		/////////////////////////////////////////
	mroRefundListPage:function(){
			postAjaxJson('vue/mroRefundListForm','ListVue2','j');
		},
		/////////////////////////////////////////
	mroRefundListDetail:function(oscode){								
			postAjaxJson('vue/mroGetRefundDetail','ListDetailVue','j', oscode);
		},
		
		/////////////////////////////////////////
	mroExchangeListPage:function(){
			postAjaxJson('vue/mroExchangeListForm','ListVue3','j');
		},	
		
		/////////////////////////////////////////
	mroExchangeListDetail:function(oscode){								
			postAjaxJson('vue/mroGetExchangeDetail','ListDetailVue','j', oscode);
		}	
	}
});




/////////////////////////////////////////nav-bar

function getNewProductRequest(message){
	if(message !=""){
		alert(message);
		mainVue.getNewProductRequestPage();
	}{
		mainVue.getNewProductRequestPage();
	}
}

function getModifyRequest(message){
	mainVue.modalClose();
	if(message !=""){
		alert(message);
		mainVue.getModifyRequestPage();
		
	}{
		mainVue.getModifyRequestPage();
	}
}

function supplyList(){
   mainVue.supplyListPage();
}

function clientList(){
   mainVue.clientListPage();
}

function mroOrderList(){
	mainVue.mroOrderListPage();
}

function mroRefundList(){
	mainVue.mroRefundListPage();
}

function mroExchangeList(){
	mainVue.mroExchangeListPage();
}

		/////////////////////////////////////////

function getNewProductRequestVue(jsondata){
	mainVue.getNewProductRequestListPush(jsondata);
	mainVue.changePage(0);
}

function mroGetNewProductDetailVue(jsondata){
	mainVue.mroGetNewProductDetailPush(jsondata);
	mainVue.modalOpen();
}



function getModifyRequestListVue(jsondata){
	mainVue.getModifyRequestListPush(jsondata);
	mainVue.changePage(1);
}

function mroGetModifyProductDetailVue(jsondata){
	mainVue.mroGetModifyRequestDetailPush(jsondata);
	mainVue.modalOpen();
}
/////////////////////////////////////////iyj


function supplyListVue(jsondata){
   if(jsondata!=""){
      mainVue.list = jsondata;
	  mainVue.changePage(2);
   }else{
      alert("검색어에 해당되는 공급업체가 없습니다.");
   }
}

function clientListVue(data){
   if(data!=""){
      mainVue.list = data;
      mainVue.changePage(3);
   }else{
      alert("검색어에 해당되는 고객사가 없습니다.");
 }
}

function supplyListVue2(msg){
   if(msg!=""){
      alert(msg);
      mainVue.supplyListPage();
   }
}



function clientListVue2(msg){
   if(msg!=""){
      alert(msg);
      mainVue.clientListPage();
   }else{
      
   }
}


//////////////////////////////////////////////////////////
function ListVue(jsondata){
	mainVue.list = jsondata;
	mainVue.changePage(4);
}


function ListVue2(jsondata){
	mainVue.list = jsondata;
	mainVue.changePage(5);
}

function ListVue3(jsondata){
	mainVue.list = jsondata;
	mainVue.changePage(6);
}


function ListDetailVue(jsondata){	
	mainVue.modalDetailList = jsondata;
	mainVue.modalOpen();
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////











