
const mainVue = new Vue({
	el:"#mainVue",
	data:{
		page:[{show:false/*주문리스트*/},{show:false/*주문디테일*/},{show:false/*환불리스트*/}
		,{show:false/*교환리스트*/}],
		orderList:[],
		orderListDetail:[]
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		mroOrderListPage:function(){
			
			this.resetPage();
			this.page[0].show=true;
			postAjaxJson('vue/mroOrderListForm','ListVue','j');
		},
		mroOrderListDetail:function(oscode){											
			postAjaxJson('vue/mroGetOrderDetail','ListDetailVue','j', oscode);
			this.page[1].show=true;
		},
		close:function(num){
			this.page[num].show=false;
			
		},
		mroRefundListPage:function(){
			this.resetPage();
			this.page[2].show=true;
			postAjaxJson('vue/mroRefundListForm','ListVue','j');
		},
		mroRefundListDetail:function(oscode){								
			postAjaxJson('vue/mroGetRefundDetail','ListDetailVue','j', oscode);
			this.page[1].show=true;
		},
		mroExchangeListPage:function(){
			this.resetPage();
			this.page[3].show=true;
			postAjaxJson('vue/mroExchangeListForm','ListVue','j');
		},
		mroExchangeListDetail:function(oscode){								
			postAjaxJson('vue/mroGetExchangeDetail','ListDetailVue','j', oscode);
			this.page[1].show=true;
		}
		
	}
});

function mroOrderList(){
	mainVue.mroOrderListPage();
}


function ListVue(jsondata){
	mainVue.orderList = jsondata;
}


function ListDetailVue(jsondata){	
	mainVue.orderListDetail = jsondata;
}

function mroRefundList(){
	mainVue.mroRefundListPage();
}

function mroExchangeList(){
	mainVue.mroExchangeListPage();
}




//(jobCode, fn, rType, clientData = "") {
	/*	changeHome1:function(){
			console.log("imin");
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[2].show = true;
			
		},*/