/**
 * 
 */


const main = new Vue({
  el: '#supplyVue',
  data: {
	display:[{show:false},{show:false}],
	modal: { show: false },
	list:[],
	modalList:[],
	dupCheck:[]
  },
	methods:{
		changePage:function(page){
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.modalClose();
			this.display[page].show = true;
		},
		modalOpen: function() {
			this.dupCheck = [];
			this.modal.show = true;
		},
		modalClose: function() {
			this.modal.show = false;
		},
		pushData:function(jsondata){
			/*console.log(jsondata);*/
			this.list=jsondata;
		},
		pushDataModalList:function(jsondata){
			console.log(jsondata);
			this.modalList=jsondata;
		},
		getAsDetail:function(data,type){
			let cData = `re_code=${data}`;
			if(type=="r"){
			postAjaxForm("vue/supplyReceiveAsDetailR","getAsDetailForm", "j",cData);}
			else{
			postAjaxForm("vue/supplyReceiveAsDetailE","getAsDetailForm", "j",cData);	
			}
		},
		insReason:function(index,code){
			console.log(index+"a"+code);
			if(this.dupCheck.includes(index))return;
			let updown = 0;
			
			for(i=0;i<this.dupCheck.length;i++){
				if(this.dupCheck[i] > index){
					updown -= 1;
				}
			}
			let modal = document.getElementById('modalTable');
			let newRow = modal.insertRow(this.dupCheck.length+2+index+updown);
			newRow.id=`del${index}`;
			let newCell1 = newRow.insertCell(0);
			let newCell2 = newRow.insertCell(1);
			newCell1.colSpan = "4";
			newCell1.innerHTML = `<input type="text" name="rd_note" style="width:100%;" placeholder="거절 사유 입력"/>
									<input type="hidden" name="rd_prcode" value="${code}"/>`;
			newCell2.innerHTML = `<div id="del${index}" onclick="delReason(${index})">삭제</div>`;
			this.dupCheck.push(index);
		},
		sendAsResponse:function(re,yn,type){
			let cData;
			if(yn=="FF" || yn=="EF"){
				let rd = [];
				let rdnote = document.getElementsByName("rd_note");
				let prcode = document.getElementsByName("rd_prcode");
				
				for(i=0;i<rdnote.length;i++){
					let rdd = {rd_note:rdnote[i].value,rd_prcode:prcode[i].value};
					rd.push(rdd);		
				}
				cData = {re_code:re,re_state:yn,rd:rd};
			}else{
				cData = {re_code:re,re_state:yn};
			}
			console.log(cData);
			if(type=="r"){
				postAjaxJson('vue/supplyResponseRefund','getRefundListForm','s',JSON.stringify(cData));
			}else if(type=="e"){
				postAjaxJson('vue/supplyResponseOrder','getExchangeListForm','s',JSON.stringify(cData));
			}
		}
	}
});

function delReason(index){
    $(`#del${index}`).remove();
	for(i=0;i<main.dupCheck.length;i++){
		if(main.dupCheck[i]==index){
			main.dupCheck.splice(i);
		}
	}
}


function getRefundListForm(msg=""){
	if(msg!="")alert(msg);
	postAjaxJson("vue/supplyReceiveRefundListForm","getRefundList", "j");
}

function getExchangeListForm(msg=""){
	if(msg!="")alert(msg);
	postAjaxJson("vue/supplyReceiveExchangeListForm","getExchangeList", "j");
}
/****************************************************************************/
function getRefundList(jsondata){
	main.changePage(0);
	main.pushData(jsondata);
}

function getExchangeList(jsondata){
	main.changePage(1);
	main.pushData(jsondata);
}

function getAsDetailForm(jsondata){
	main.modalOpen();
	main.pushDataModalList(jsondata);
}

