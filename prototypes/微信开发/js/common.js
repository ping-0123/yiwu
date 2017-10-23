var BaseUrl="http://192.168.0.115:9090/yiwu";
function getUrlParam(name) {  
           var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
           var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
           if (r != null) return unescape(r[2]); return null; //返回参数值  
       }  
       
function getFileName(o){
    var pos=o.lastIndexOf("\\");
    return o.substring(pos+1);  
} 

