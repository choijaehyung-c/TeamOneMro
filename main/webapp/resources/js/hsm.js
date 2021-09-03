function getNewProductRequest(){
	let f = makeForm("/HSM/MroRegisterNewProductForm","post");
	
	document.body.append(f);
	f.submit();
}

//상품등록신청 리스트 불러오기
function callRequestRegisterNewProductList(message){
		modalClose();
	if(message != ""){
		alert(message);
	}
		postAjaxJson('GetRequestRegisterNewProductList', 'getRequestRegisterNewProductList', 'j');
}

function getRequestRegisterNewProductList(jsonData){
	let data = "<div>새 상품 등록신청 리스트</div>";
	if(jsonData !=""){
		for(i=0; i<jsonData.length; i++){
		data += "<div onClick='mroCallNewProductDetail("+JSON.stringify(jsonData[i].pr_code)+")'>"+"상품명:"+jsonData[i].pr_name+"</div>";
		}
	}else{
		data = "<div>새 상품 등록신청이 없습니다.</div>";
	}
	RequestProductListBox.innerHTML = data;
}

//새 상품등록 신청 디테일 불러오기
function mroCallNewProductDetail(prcode){
	alert(prcode);
	let sendJsonData = {pr_code:prcode};
	let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('MroGetNewProductDetail','mroGetNewProductDetail', 'j', clientData);
}


//새 상품등록 신청 디테일모달에 띄우기
function mroGetNewProductDetail(jsonData){
	let modal = document.getElementById("DetailModal");
	modal.style.display = "block";	
	let data = "<div>새 상품 정보</div>";
		data += "<div>"+JSON.stringify(jsonData)+"</div>";
		data += "<div onClick=\"mroResponseNewProduct('"+jsonData.pr_code+"','PC')\">수락</div>"+"<div onClick=\"mroResponseNewProduct('"+jsonData.pr_code+"','AF')\">거절</div>";
	ProductDetailInfo.innerHTML = data;
}
//모달 닫기
function modalClose(){
	let modal = document.getElementById("DetailModal");
	modal.style.display = "none";
}


//새 상품 등록 신청 응답
function mroResponseNewProduct(prcode, prstcode){
	let sendJsonData = {pr_code:prcode, pr_stcode:prstcode};
	let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('MroResponseNewProduct','callRequestRegisterNewProductList', 's', clientData);
}


// mroManageModifyProductForm 로 페이지 전환
function getModifyRequest(){
	let f = makeForm("/HSM/MroManageModifyProductForm","post");
	
	document.body.append(f);
	f.submit();
}

// 수정요청 리스트 불러오기
function callModifyRequestList(message){
		modalClose();
	if(message != ""){
		alert(message);
	}
	postAjaxJson('CallModifyRequestList', 'getModifyRequestList', 'j');
}

// 수정요청 리스트 띄우기
function getModifyRequestList(jsonData){
	let data = "<div>상품 수정요청 리스트</div>";
	if(jsonData !=""){
		for(i=0; i<jsonData.length; i++){
		data += "<div onClick='mroCallProductModifyDetail("+JSON.stringify(jsonData[i].pr_code)+")'>"+"상품명:"+jsonData[i].pr_name+"</div>";
		}
	}else{
		data = "<div>상품 수정요청이 없습니다.</div>";
	}
	RequestProductListBox.innerHTML = data;
}

function mroCallProductModifyDetail(prcode){
	let sendJsonData = {pr_code:prcode};
	let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('MroGetModifyProductDetail','mroGetModifyProductDetail', 'j', clientData);
}


function mroGetModifyProductDetail(jsonData){
	let modal = document.getElementById("DetailModal");
	modal.style.display = "block";	
	let data = "<div>수정요청 상품정보</div>";
		data += "<div>"+JSON.stringify(jsonData)+"</div>";
		data += "<div onClick=\"mroResponseModifyProduct('"+jsonData.pr_code+"','PC')\">수락</div>"+"<div onClick=\"mroResponseModifyProduct('"+jsonData.pr_code+"','RF')\">거절</div>";
		//alert(JSON.stringify(jsonData[0].pr_code)+"         dsdsdsdsdsd");
	ProductDetailInfo.innerHTML = data;
}

function mroResponseModifyProduct(prcode, prstcode){	
	let sendJsonData = {pr_code:prcode, pr_stcode:prstcode};
	let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('MroResponseModifyProduct','callModifyRequestList', 's', clientData);
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
