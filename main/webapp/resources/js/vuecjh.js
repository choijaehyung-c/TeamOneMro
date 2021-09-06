/**
 * 
 */
let Home1 = {
  template: `<p>Welcome home!</p>`
}

let Home2 = {
  template: `<div><p v-for="item in list">{{item}}</p></div>`
	,
	data(){
		return{
			list:{a:1,b:2,c:3}
		}
	}
}

let Home3 = {
  template: `<div><input type="text" value="" v-model="msg"/>
			<br><p>{{msg}}{{aaqqd[0].aa2}}</p></div>`,
	data(){
		return{
			msg:'hi'
		}
	},
	props: ['aaqqd'],
}


const main = new Vue({
  el: '#supplyVueZone',
  data: {
    currentView: this.mycomponent,
	msg:"asddd",
	mssg:[
		{aa1:"aa",
		aa2:"bb"}
	],
	display:[{show:false},{show:true},{show:false}]
	/*display:{
		vue1:false,
		vue2:true
	}*/
  },
	methods:{
		/*changeHome1:function(){
			this.currentView = Home1;
		},
		changeHome2:function(){
			this.currentView = Home2;
		},
		changeHome3:function(){
			this.currentView = Home3;
		},
		hhh:function(){
			this.aa2 = "cc";
		}*/
		changeHome1:function(){
			console.log("imin");
			for(i=0;i<this.display.length;i++){
				this.display[i].show=false;
			}
			this.display[2].show = true;
			
		},
		changeHome2:function(){
			this.currentView = Home2;
		},
		changeHome3:function(){
			this.currentView = Home3;
		},
		hhh:function(){
			this.aa2 = "cc";
		}
	}
});

/*function change3(){
	main.mssg.aa2="cc";
	main.changeHome3();
}
function change1(){
	main.changeHome1();
}
function change2(){
	main.changeHome2();
}

*/
function change3(){
	main.changeHome3();
}
function change1(){
	main.changeHome1();
}
function change2(){
	main.changeHome2();
}

