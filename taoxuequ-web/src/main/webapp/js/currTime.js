$(function(){
	var t = null;
    t = setTimeout(time,1000);//开始执行
    function time(){
    	
       clearTimeout(t);//清除定时器
       var dt = new Date();
       var y=dt.getFullYear(); 
       var M=dt.getMonth()+1; 
       var d=dt.getDate();
       var h=dt.getHours();
       var m=dt.getMinutes();
       var s=dt.getSeconds();
       var week;
       
       M=formatTime(M);
       d=formatTime(d);
       h=formatTime(h);
       m=formatTime(m);
       s=formatTime(s);
      
       switch (dt.getDay()){
	       case 1: week="星期一"; break;
	       case 2: week="星期二"; break;
	       case 3: week="星期三"; break;
	       case 4: week="星期四"; break;
	       case 5: week="星期五"; break;
	       case 6: week="星期六"; break;
	       default:week="星期日"; break;
        }
       $("#timeShow").html(y+"年"+M+"月"+d+"日 "+h+"时"+m+"分"+s+"秒 "+week);
       t = setTimeout(time,1000); //再次调用定时器，循环执行             
    } 
    
    function formatTime(t){
    	
    	 if(t<10){
	    	   t="0"+t;
	       }
    	 return t;
	}
});