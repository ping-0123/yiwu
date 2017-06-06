//var baseApiUrl="http://192.168.0.115:8080/yiwu/";
var baseApiUrl="http://yzw.chenksoft.com:8080/yiwu/"
//var baseApiUrl="";

function getTodayBegin(){
	var today = new Date();
	today.setHours(0);
	today.setMinutes(0);
	today.setSeconds(0);
	today.setMilliseconds(0);
	return today;
}
