function getNewProductRequest(){
	let f = makeForm("/HSM/MroRegisterNewProductForm","post");
	
	document.body.append(f);
	f.submit();
	alert("새상품신청목록 연결");
}

//상품등록신청 리스트 불러오기
function callRequestRegisterNewProductList(){
	postAjaxJson('GetRequestRegisterNewProductList', 'getRequestRegisterNewProductList');
}

function getRequestRegisterNewProductList(jsonData){
	alert(JSON.stringify(jsonData));
	let data = "<div>새 상품 등록신청 리스트</div>";
	if(jsonData !=""){
		for(i=0; i<jsonData.length; i++){
		data += "<div onClick='getNewProductDetail("+JSON.stringify(jsonData[i])+")'>"+"상품명:"+jsonData[i].pr_name+"</div>";
		}
	}else{
		data += "<div>새 상품 등록신청이 없습니다.</div>";
	}
	newProductListBox.innerHTML = data;
}

//새 상품등록 신청 디테일모달
function getNewProductDetail(jsonData){

	alert(JSON.stringify(jsonData));
	let modal = document.getElementById("newProductDetailModal");
	let data = "<div>새 상품 정보</div>";
		data += "<div>"+JSON.stringify(jsonData)+"</div>";
		data += "<div onClick=\"responseNewProduct('"+jsonData.pr_code+"','PR')\">수락</div>"+"<div onClick=\"responseNewProduct('"+jsonData.pr_code+"','PC')\">거절</div>";
	newProductDetailInfo.innerHTML = data;
	modal.style.display = "block";	
}
//모달 닫기
function modalClose(){
	let modal = document.getElementById("newProductDetailModal");
	modal.style.display = "none";
}


//새 상품 등록 신청 응답
function responseNewProduct(prcode, prstcode){
	alert(prcode+ prstcode);
	let sendJsonData = []
		sendJsonData.push({pr_code:prcode, pr_stcode:prstcode});
	let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('ResponseNewProduct','callRequestRegisterNewProductList', clientData);
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
