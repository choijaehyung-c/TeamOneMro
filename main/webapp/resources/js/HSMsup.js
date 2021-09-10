const mainVue = new Vue({
	el:"#mainVue",
	data:{
		page:[{show:false}, {show:false}, {show:false}],
		supplyAllProductList:[],
		categoryCode:"",
		word:""
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
		supplyAllProductListPush:function(jsondata){
			this.supplyAllProductList=jsondata;
			this.page[0].show=true;
			this.page[1].show=true;
		},
		categoryPoductListPage:function(cate){
			this.categoryCode = cate;
			this.page[1].show=false;
			this.page[2].show=true;
		},
		searchProduct:function(word){
			alert(word);
			this.word = word;	
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
			postAjaxJson('vue/SupplyAllProductList','supplyAllProductList','j');	
		},
		callCategoryPoductList:function(cate){
			mainVue.categoryPoductListPage(cate);		
		}
		
	
	}
});


function getCate(jsondata){
	mainVueTwo.supplyGetCategoryPush(jsondata);
}

function supplyAllProductList(jsondata){
	mainVue.supplyAllProductListPush(jsondata);
}