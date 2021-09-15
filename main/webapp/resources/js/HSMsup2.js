

const mainVue = new Vue({
   el:"#mainVue",
   data:{                      
      display:[{show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}, {show:false}],
      modal:{show:false},
	  modal2:{show:false},
      list:[],      
      PClist:[],      
      PRAFlist:[],
      MRDRDAlist:[],
      categoryList2:[],
      detail:{},
	  categoryCode:''
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
         this.list=jsondata;
      },
      modalClose:function(modalN){
         this.modal[modalN].show=false;
      },
 	  detailPush:function(jsondata){
         this.modalOpen();
         this.detail = jsondata;   
      },
      supplyAllProductListPush:function(){
         this.changePage(0);
         this.display[1].show=true;
      },
      categoryPoductListPage:function(cate){
         this.categoryCode = cate;
         this.changePage(0);
         this.display[2].show=true;
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
		 this.changePage(3);
         this.display[6].show = true;
      },
      supplyMRDRDAProductListPush:function(){
         this.changePage(4);
         this.display[8].show = true;
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
         this.changePage(0);
         this.display[5].show = true;
      },
      search2:function(word){
         this.searchWord = word.target.value;
         this.resetPage(3);
         this.display[7].show = true;
      },
      search3:function(word){
         this.searchWord = word.target.value;
         this.changePage(4);
         this.display[9].show = true;
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
      resetPage:function(){
         for(i=0;i<this.page.length;i++){
            this.page[i].show=false;
         }
      },
      supplyGetCategoryPage:function(){
         postAjaxJson('vue/supplyGetCategory','getCate','j');                              
         this.display[0].show=true;
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
   mainVue.pushData(jsondata);
   mainVue.supplyAllProductListPush();
}

function reSupplyAllProductListPage(msg){
   alert(msg);
   mainVueTwo.supplyAllProductListPage();
}

function detailPush(jsondata){
   mainVue.detailPush(jsondata);
}

function supplyPRAFProductListPush(jsondata){
   mainVue.pushData(jsondata);
   mainVue.supplyPRAFProductListPush();
}
function supplyMRDRDAProductListPush(jsondata){
   mainVue.pushData(jsondata);
   mainVue.supplyMRDRDAProductListPush();
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



