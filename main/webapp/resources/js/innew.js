/**
 * 
 */

function clientList(){
	
}

function supplyList(){
	
	let f = makeForm("/iyj/mroSupplyListForm","post");
	document.body.append(f);
	f.submit();
	alert("공급사목록을 보시겠습니까?")
}