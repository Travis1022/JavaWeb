
/*
   message:要显示的消息
   x,y:显示消息框的坐标,默认为左上角
   delay:延迟多长时间消失，-1为永不消失，单位为毫秒
*/
function feedBackMessage(message,x,y,delay){
   
   if(!message) return;
   //只允许百分数或数值参数
   x=/\d{1,2}%|100%|left|right/.test(x)?x:(parseInt(x)||0)+"px";
   y=/\d{1,2}%|100%|top|bottom/.test(y)?y:(parseInt(y)||0)+"px";
   delay=parseInt(delay)||-1;
   var fdDiv=document.getElementById('show_feedBack_message');
   if(!fdDiv){
    var showMessage=document.createElement("<div id='show_feedBack_message' style='z-index:10000;filter:alpha(opacity=100);position:absolute;white-space:nowrap'></div>");
    document.body.appendChild(showMessage);
    fdDiv=document.getElementById('show_feedBack_message');
   }

   if(feedBackMessage.timer){clearInterval(feedBackMessage.timer)}
   
   
   
   fdDiv.innerHTML=message;
   fdDiv.style.display="";
   var docWidth=document.documentElement.scrollWidth>document.documentElement.clientWidth?document.documentElement.scrollWidth:document.documentElement.clientWidth;
   var docHeight=document.documentElement.scrollHeight>document.documentElement.clientHeight?document.documentElement.scrollHeight:document.documentElement.clientHeight;
   if(/left|right/.test(x)){
    x=(x=="left")?"0px":(docWidth-fdDiv.offsetWidth)+"px";
   }
   if(/top|bottom/.test(y)){
    y=(y=="top")?"0px":(docHeight-fdDiv.offsetHeight)+"px";
   }
   fdDiv.style.left=x;
   fdDiv.style.top=y;
   fdDiv.filters.Alpha.Opacity=100;
   
   

   //渐隐效果
   var step=parseInt(delay/1000);
  // $(fdDiv).fadeOut("slow");
   var alpha=fdDiv.filters.Alpha.Opacity;
   
   if(delay!=-1){
    feedBackMessage.timer=window.setInterval(function(){
      
     if(fdDiv.filters.Alpha.Opacity>0){
      fdDiv.filters.Alpha.Opacity--;
     }else{
      window.clearInterval(feedBackMessage.timer);
      fdDiv.style.display="none";
     }
    },step);
   }
}
