











function postAjaxJson(jobCode, fn, rType, clientData = "") {
	let ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			if (rType == 'j') {
				window[fn](JSON.parse(ajax.responseText));
			} else if (rType == 's') {
				window[fn](ajax.responseText);
			}
		}
	}
	ajax.open("POST", jobCode);
	ajax.setRequestHeader("Content-type", "application/json; charset=utf-8");
	ajax.send(clientData);
	/*
	대부분 이것으로 사용, 제이슨 타입으로 파람 넘김
	백엔드에서 빈으로 받을꺼면 clientData={키:밸류,키:밸류}
	그 빈안에 <>리스트 타입이 있다면 {키:밸류,키:밸류,키:[]밸류}
	백엔드 컨트롤러에서는 @RequestBody로 받으면됨
	*/
}