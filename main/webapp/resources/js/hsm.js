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
	let data2 = "<div>상품 삭제요청 리스트</div>";
	if(jsonData !=""){
		for(i=0; i<jsonData.length; i++){
			if(jsonData[i].pr_stcode == 'MR'){		
				data += `<div onClick="mroCallProductModifyDetail('${jsonData[i].pr_code}','MR')">상품명:${jsonData[i].pr_name}</div>`;
			}else{
				data2 += `<div onClick="mroCallProductModifyDetail('${jsonData[i].pr_code}','DR')">상품명:${jsonData[i].pr_name}</div>`;	
			}
		}
	}else{
		data = "<div>수정요청이 없습니다.</div>";
		data2 = "<div>삭제요청이 없습니다.</div>";
	}
	RequestProductListBox.innerHTML = data;
	DeleteRequestProductListBox.innerHTML = data2;
}

function mroCallProductModifyDetail(prcode, stcode){
	let sendJsonData = {pr_code:prcode, pr_stcode:stcode};
	let clientData = JSON.stringify(sendJsonData);
	alert(clientData);
		postAjaxJson('MroGetModifyProductDetail','mroGetModifyProductDetail', 'j', clientData);
}


function mroGetModifyProductDetail(jsonData){
	let data = '';
	let modal = document.getElementById("DetailModal");
	modal.style.display = "block";
	if(jsonData.pr_stcode == 'MR'){		
		data = "<div>수정요청 상품정보</div>";
		data += "<div>"+JSON.stringify(jsonData)+"</div>";
		data += "<div onClick=\"mroResponseModifyProduct('"+jsonData.pr_code+"','PC')\">수락</div>"+"<div onClick=\"mroResponseModifyProduct('"+jsonData.pr_code+"','RF')\">거절</div>";
	}else{
		data = "<div>삭제요청 상품정보</div>";
		data += "<div>"+JSON.stringify(jsonData)+"</div>";
		data += `<div onClick="mroResponseModifyProduct('${jsonData.pr_code}','DA')">수락</div>
				<div onClick="mroResponseModifyProduct('${jsonData.pr_code}','DF')">거절</div>`;
	}	
	ProductDetailInfo.innerHTML = data;
}

function mroResponseModifyProduct(prcode, prstcode){
		
	let sendJsonData = {pr_code:prcode, pr_stcode:prstcode};
	let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
		postAjaxJson('MroResponseModifyProduct','callModifyRequestList', 's', clientData);
}










// supply

function supplyCallProductDetail(){
	let prcode = "1037532711";
	let sendJsonData = {pr_code:prcode};
	let clientData = JSON.stringify(sendJsonData);
		postAjaxJson('SupplyGetProductDetail','supplyGetProductDetail', 'j', clientData);
}

function supplyGetProductDetail(jsonData){
	alert(JSON.stringify(jsonData));
	let modal = document.getElementById("DetailModal");
	modal.style.display = "block";	
	let data = "<div>상품정보</div>";
		data += "<input id='read_pr_spcode' type='hidden' value="+jsonData.pr_spcode+">";
		data += "<input id='read_pr_code' type='hidden' value="+jsonData.pr_code+">";
		data += "<input id='read_pr_tax' type='hidden' value="+jsonData.pr_tax+">";
		data += "<input id='read_pr_spbkind' type='hidden' value="+jsonData.pr_spbkind+">";
		data += "<input id='read_pr_stcode' type='hidden' value='MR'>";
		data += "<input id='read_pr_stcode2' type='hidden' value='DR'>";
		data += "<input id='read_pr_image' type='hidden' value="+jsonData.pr_image+">";
		data += "<input id='read_cate' type='hidden' value="+jsonData.cate+">";
		data += "<input id='read_cate_name' type='hidden' value="+jsonData.cate_name+">";
		data += "<img id='read_pr_image' src="+jsonData.pr_image+">";
		data += "<input readonly type='text' id='read_pr_name'  value="+jsonData.pr_name+">";
		data += "<input readonly type='text' id='read_pr_price' value="+jsonData.pr_price+">";
		data += "<input readonly type='text' id='read_pr_stock' value="+jsonData.pr_stock+"><div onClick='readOnlyFStock()'>수량입력</div><div onClick='supplyModifyStock()'>수량변경</div>";
		data += "<input readonly type='text' id='read_pr_origin' value="+jsonData.pr_origin+">";
		data += "<input readonly type='text' id='read_pr_info' value="+jsonData.pr_info+">";
		data += "<div onClick='readOnlyF()'>수정입력하기</div>";
		data += "<div onClick='supplyRequestModify()'>수정요청</div>";
		data += "<div onClick='supplyRequestDelete()'>삭제요청</div>"; 
	ProductDetailInfo.innerHTML = data;
}
//물품 정보 리드온리 풀기
function readOnlyF(){
	document.getElementById("read_pr_name").readOnly = false;
	document.getElementById("read_pr_price").readOnly = false;
	document.getElementById("read_pr_origin").readOnly = false;
	document.getElementById("read_pr_info").readOnly = false;
}
function readOnlyFStock(){
	document.getElementById("read_pr_stock").readOnly = false;
	}

//수정입력 정보 제이슨으로 만들기
function supplyRequestModify(){
	let prspcode = document.getElementById("read_pr_spcode").value;
	let prcode = document.getElementById("read_pr_code").value;
	let prtax = document.getElementById("read_pr_tax").value;
	let prspbkind = document.getElementById("read_pr_spbkind").value;
	let prstcode = document.getElementById("read_pr_stcode").value;
	let primage = document.getElementById("read_pr_image").value;
	let prname = document.getElementById("read_pr_name").value;
	let prprice = document.getElementById("read_pr_price").value;
	let prstock = document.getElementById("read_pr_stock").value;
	let prorigin = document.getElementById("read_pr_origin").value;
	let prinfo = document.getElementById("read_pr_info").value;
	let cate = document.getElementById("read_cate").value;
	let catename = document.getElementById("read_cate_name").value;

	let sendJsonData = {pr_spcode:prspcode, pr_code:prcode, pr_tax:prtax,
						pr_spbkind:prspbkind, pr_stcode:prstcode,
						pr_image:primage, pr_name:prname,
						pr_price:prprice, pr_stock:prstock,
						pr_origin:prorigin, pr_info:prinfo,
						cate:cate, cate_name:catename};
	let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
	postAjaxJson('SupplyRequestModify','testtest', 's', clientData);		
}

// 수량수정
function supplyModifyStock(){
	let prcode = document.getElementById("read_pr_code").value;
	let prstock = document.getElementById("read_pr_stock").value;
	let sendJsonData = {pr_code:prcode, pr_stock:prstock};
	
	let clientData = JSON.stringify(sendJsonData);
	postAjaxJson('SupplyModifyStock','testtest', 's', clientData);
}

//삭제요청
function supplyRequestDelete(){
	let prspcode = document.getElementById("read_pr_spcode").value;
	let prcode = document.getElementById("read_pr_code").value;
	let prtax = document.getElementById("read_pr_tax").value;
	let prspbkind = document.getElementById("read_pr_spbkind").value;
	let prstcode = document.getElementById("read_pr_stcode2").value;
	let primage = document.getElementById("read_pr_image").value;
	let prname = document.getElementById("read_pr_name").value;
	let prprice = document.getElementById("read_pr_price").value;
	let prstock = document.getElementById("read_pr_stock").value;
	let prorigin = document.getElementById("read_pr_origin").value;
	let prinfo = document.getElementById("read_pr_info").value;
	let cate = document.getElementById("read_cate").value;
	let catename = document.getElementById("read_cate_name").value;

	let sendJsonData = {pr_spcode:prspcode, pr_code:prcode, pr_tax:prtax,
						pr_spbkind:prspbkind, pr_stcode:prstcode,
						pr_image:primage, pr_name:prname,
						pr_price:prprice, pr_stock:prstock,
						pr_origin:prorigin, pr_info:prinfo,
						cate:cate, cate_name:catename};
	let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
	postAjaxJson('SupplyRequestDelete','testtest', 's', clientData);
}


//상품신청시 카테고리 가져오기
function getCate(){
	postAjaxJson('GetCate','inputProductInfo', 'j');
}
// 상품신청
function inputProductInfo(jsonData){
	let modal = document.getElementById("DetailModal");
	modal.style.display = "block";
		
	let data = "<div>새 상품 추가</div>";
		data += "<div>상품이름</div><input id='pr_name' type='text'><br>";
		data += "<div>상품가격</div><input id='pr_price' type='text'><br>";
		data += "<div>재고</div><input id='pr_stock' type='text'><br>";
		data += "<div>원산지</div><input id='pr_origin' type='text'><br>";
		data += "<div>카테고리</div><select id='CG'>";
			for(i=0; i<jsonData.length; i++){
				data += `<option value='${jsonData[i].cate}'>${jsonData[i].cate_name}</option>`;
			}
		data += "</select><br>";
		data += "<div>사진</div><input id = 'pr_image' type='text'><br>";
		data += "<div>정보</div><input id='pr_info' type='text'><br>";
		data += `<div onClick ="supplyRequestNewProduct()">추가요청</div>`;
	ProductDetailInfo.innerHTML = data;
}

function supplyRequestNewProduct(){
	let prname = document.getElementById("pr_name").value;
	let prprice = document.getElementById("pr_price").value;
	let prstock = document.getElementById("pr_stock").value;
	let prorigin = document.getElementById("pr_origin").value;
	let primage = document.getElementById("pr_image").value;
	let cate = document.getElementById("CG").value;
	let catename = CG.options[CG.selectedIndex].text;
	let prinfo = document.getElementById("pr_info").value;
	
	let sendJsonData = {pr_image:primage, pr_name:prname,
						pr_price:prprice, pr_stock:prstock,
						pr_origin:prorigin, pr_info:prinfo,
						cate:cate, cate_name:catename};
	let clientData = JSON.stringify(sendJsonData);
		alert(clientData);
	postAjaxJson('SupplyRequestNewProduct','testtest', 's', clientData);
}


function testtest(msg){
	alert(msg);
}



/*
{"pr_spcode":"KR001D",
"pr_code":"1037532711",
"pr_stock": "2000",//
"pr_name":"[아모스] 딱풀 35g",//
"pr_image":"http://image.imarket.co.kr/goods_image/2/7/1/1/1037532711s.gif",
"pr_price":"920",//
"pr_tax":"92",
"pr_info":"아모스딱풀35g(1200)   풀용량 : 35g   한국      ",//
"pr_origin":null,//
"pr_spbkind":"KS",
"pr_stcode":"PC"}
*/

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
