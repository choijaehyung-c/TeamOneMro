/**
 * 
 */

function clientList(){
	
	let f = makeForm("/iyj/M_ClientListForm","post");
	document.body.append(f);
	f.submit();
	alert("고객사목록을 불러옵니다.");
	
}

function supplyList(){
	
	let f = makeForm("/iyj/M_SupplyListForm","post");
	document.body.append(f);
	f.submit();
	alert("공급사목록을 불러옵니다.");
}

function mroOrderList(){
	
	let f = makeForm("iyj/M_WaitOrderForm","post");
	document.body.append(f);
	f.submit();
	alert("주문목록을 불러옵니다.");
}