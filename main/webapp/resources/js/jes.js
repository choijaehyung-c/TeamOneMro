function ProductList(){
	
	let f = makeForm("/jes/SupplyProductListForm","post");
	document.body.append(f);
	f.submit();
	alert("상품리스트");
	
}

function newProductList(){
	
	let f = makeForm("/jes/SupplyProductListForm","post");
	document.body.append(f);
	f.submit();
	alert("새상품리스트");
	
}
