/**
 * 
 */


const deliveryVue = new Vue({
   el:"#deliveryVue",
   data:{
      deliveryPage:[{show:false}],
      deliveryList:[],
   },
   methods:{
      pushData:function(jsondata){
         this.deliveryList = jsondata;
      },
      insertsdcode:function(dlcode, dscode){
         let sendJsonData = {dl_code:dlcode, dl_dscode:dscode};
         let clientData = JSON.stringify(sendJsonData);
         postAjaxJson('vue/Insertsdcode','callDeliveryList', 's', clientData);
      }     
   }
});


function callDeliveryList(){
   let sendJsonData = {dl_dvcode:"IYJ032"};
   let clientData = JSON.stringify(sendJsonData);
   alert(clientData);
   postAjaxJson('vue/DeliveryTest','getDeliveryListPush', 'j', clientData);
}

function getDeliveryListPush(jsondata){
   alert(JSON.stringify(jsondata));
   deliveryVue.pushData(jsondata);
   deliveryVue.deliveryPage[0].show = true;
}
