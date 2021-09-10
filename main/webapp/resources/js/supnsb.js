
const mainVue = new Vue({
	el:"#mainVue",
	data:{
		page:[{show:false/*거래내역리스트*/},{show:false/*거래디테일*/}],
		dealList:[],
		dealListDetail:[]
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		supplyDealListPage:function(){
			
			this.resetPage();
			this.page[0].show=true;
			postAjaxJson('vue/getSupplyDealList','ListVue','j');
		},
		SupplyDealDetail:function(recode){											
			postAjaxJson('vue/getSupplyDealDetail','ListDetailVue','j', recode);
			this.page[1].show=true;
		},
		close:function(num){
			this.page[num].show=false;			
		}
		
	}
});

function supplyDealList(){
	mainVue.supplyDealListPage();
}


function ListVue(jsondata){
	mainVue.dealList = jsondata;
}


function ListDetailVue(jsondata){	
	mainVue.orderListDetail = jsondata;
}


