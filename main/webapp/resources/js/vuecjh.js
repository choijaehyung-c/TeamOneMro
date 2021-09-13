/**
 * 
 */


const main = new Vue({
  el: '#supplyVue',
  data: {
	display:[{show:false},{show:false}],
	modal: { show: false },
	list:[],
	modalList:[],
	dupCheck:[]
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

























/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



const mainVue = new Vue({
	el:"#mainVue",
	data:{						    
		page:[{show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}],
		modal:{show:false},
		list:[],		
		PClist:[],		
		PRAFlist:[],
		MRDRDAlist:[],
		categoryList2:[],
		detail:{},
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		modalClose:function(modalN){
			this.modal[modalN].show=false;
		},
		supplyAllProductListPush:function(jsondata){
			this.list=jsondata;
			this.resetPage();
			this.page[0].show=true;
			this.page[1].show=true;
		},
		categoryPoductListPage:function(cate){
			this.categoryCode = cate;
			this.resetPage();
			this.page[0].show=true;
			this.page[2].show=true;
		},
		productDetail:function(prcode, stcode, modalN){
			let sendJsonData = {pr_code:prcode, pr_stcode:stcode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/SupplyGetProductDetail','detailPush', 'j', clientData);
			this.modal[modalN].show = true;
		},
		detailPush:function(jsondata){
			this.modal.show = true;
			this.detail = jsondata;	
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
			this.modal[0].show = false;
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
			this.modal[0].show = false;
		},
		getNewProductDetail:function(prcode){
			let sendJsonData = {pr_code:prcode};
			let clientData = JSON.stringify(sendJsonData);
			postAjaxJson('vue/MroGetNewProductDetail','newProductDetailPush', 'j', clientData);
		},
		newProductDetailPush:function(jsondata){
			this.detail = jsondata;
			this.modal.show=true;
		},
		supplyPRAFProductListPush:function(jsondata){
			this.list = jsondata;
			this.resetPage();
			this.page[3].show = true;
			this.page[6].show = true;
			
		},
		supplyMRDRDAProductListPush:function(jsondata){
			this.list = jsondata;
			this.resetPage();
			this.page[4].show = true;
			this.page[8].show = true;
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
			this.modal[2].show = true;
			postAjaxJson('vue/supplyGetCategory','getCate','j');
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
			this.resetPage();
			this.page[0].show = true;
			this.page[5].show = true;
		},
		search2:function(word){
			this.searchWord = word.target.value;
			this.resetPage();
			this.page[3].show = true;
			this.page[7].show = true;
		},
		search3:function(word){
			this.searchWord = word.target.value;
			this.resetPage();
			this.page[4].show = true;
			this.page[9].show = true;
		}
		
	}
});




const mainVueTwo = new Vue({
	el:"#mainVueTwo",
	data:{
		page:[{show:false}],
		categoryList:[]
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		modalClose:function(num){
			this.page[num].show=false;
		},
		supplyGetCategoryPage:function(){
			postAjaxJson('vue/supplyGetCategory','getCate','j');										
			this.page[0].show=true;
		},
		supplyGetCategoryPush:function(jsondata){
			this.categoryList = jsondata;	
		},
		supplyAllProductListPage:function(){
			postAjaxJson('vue/SupplyAllProductList','supplyAllProductListPush','j');	
		},
		callCategoryPoductList:function(cate){
			mainVue.categoryPoductListPage(cate);		
		},
		supplyPRAFProductListPage:function(){
			this.resetPage();
			postAjaxJson('vue/SupplyPRAFProductList', 'supplyPRAFProductListPush', 'j');
		},
		supplyMRDRDAProductListPage:function(){
			this.resetPage();
			postAjaxJson('vue/SupplyMRDRDAProductList', 'supplyMRDRDAProductListPush', 'j');
		}
		
	}
});


function getCate(jsondata){
	mainVueTwo.supplyGetCategoryPush(jsondata);
	mainVue.supplyGetCategoryPush(jsondata);
}

function supplyAllProductListPush(jsondata){
	mainVue.supplyAllProductListPush(jsondata);
}

function reSupplyAllProductListPage(msg){
	alert(msg);
	mainVueTwo.supplyAllProductListPage();
}

function detailPush(jsondata){
	mainVue.detailPush(jsondata);
}

function supplyPRAFProductListPush(jsondata){
	mainVue.supplyPRAFProductListPush(jsondata);
}
function supplyMRDRDAProductListPush(jsondata){
	mainVue.supplyMRDRDAProductListPush(jsondata);
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




