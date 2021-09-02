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

//mro에서 주문대기리스트 불러오기
function mroOrderList(){
	
	postAjaxJson('vue2/mroOrderListForm','getWaitOrderListM',clientData="")
	alert("주문목록을 불러옵니다.");
}

//주문목록 찍어주는 펑션
function getWaitOrderListM(data){
	
	let space = document.getElementById("mOrderList");
	let HTML = "<div>주문목록</div>";
	
	for(i=0; i<data.length; i++){
		HTML += "<div id='orderList' name='orderList'>"+"[주문날짜 : " + data[i].os_DATE +"] [주문번호 : " + data[i].os_CODE+ "] [고객사 : " + data[i].cl_NAME + "] [상태 : " + data[i].os_STATE +"]</div>";
		
	
		
		space.innerHTML = HTML;
	}
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




