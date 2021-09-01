/**
 * 
 */

//고객사 목록 불러오기
function clientList(){
	
	let f = makeForm("/iyj/mroClientListForm","post");
	document.body.append(f);
	f.submit();
	alert("고객사목록을 불러옵니다.");
	
}

//공급사 목록 불러오기
function supplyList(){
	
	let f = makeForm("/iyj/mroSupplyListForm","post");
	document.body.append(f);
	f.submit();
	alert("공급사목록을 불러옵니다.");
}


/*const orderList = new Vue({
	el: "#mOrderList",
	data:{
		msg:"ss",
		list:[]
	},
	method:{
		mroOrderList:function(){
			axios.get('iyj/mroOrderListForm')
			.then(result =>{
				this.list = result.data;
			})
		},
		putData:function(data){
			this.list=data
		}
	}
	
});*/

/*function orderDetail(){
	let os = document.getElementsByName("OD_OSCODE")[0].value;
	//orderList.putData= JSON.parse(os);
	console.log(os);

}*/


//mro에서 주문대기리스트 불러오기
function mroOrderList(){
	
	let f = makeForm("iyj/mroOrderListForm","post");
	document.body.append(f);
	f.submit();
	alert("주문목록을 불러옵니다.");
}


//주문번호의 상세정보 불러오기
function orderDetail(){
	
	const osCode = document.getElementsByName("OD_OSCODE")[0];
	
	alert(osCode);
	let f = makeForm("mroGetOrderDetail","post");
	f.appendChild(osCode);
	document.body.appendChild(f);
	f.submit();
}