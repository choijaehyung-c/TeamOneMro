const mainVue = new Vue({
	el:"#mainVue",
	data:{
		page:[{show:false}],
		supplyList:[]
	},
	methods:{
		resetPage:function(){
			for(i=0;i<this.page.length;i++){
				this.page[i].show=false;
			}
		},
		supplyListPage:function(){
			this.resetPage();
			this.page[0].show=true;
			postAjaxJson('vue/mroSupplyListForm','supplyListVue','j');
		},
		datata:function(data){
			alert(data);
		}
	}
});

function supplyList(){
	mainVue.supplyListPage();
}

function supplyListVue(jsondata){
	mainVue.supplyList = jsondata;
}







//(jobCode, fn, rType, clientData = "") {
	/*	changeHome1:function(){
			console.log("imin");
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[2].show = true;
			
		},*/