var storeId=getUrlParam('store-Id');
var storeName=getUrlParam('store-Name');
var payedDate=getUrlParam('date-id');
var productTypeId=getUrlParam('product-id');
var districtId=getUrlParam('district-id');
var requestDate = new Date(payedDate);
var curDate =format(payedDate,'yyyy-MM-dd');
var year=requestDate.getFullYear();
var month = requestDate.getMonth()+1;
var url=baseApiUrl +  "api/order/getDailyOrders";
alert(url);

	$(document).ready(function(){
		daily(url,storeId,payedDate, productTypeId);
	});

	$("#lastday").click(function(){
		requestDate.setDate(requestDate.getDate() -1);
		curDate=formatDate(requestDate,'yyyy-MM-dd');
		daily(url,storeId,curDate);

	});

	$("#nextday").click(function(){
		requestDate.setDate(requestDate.getDate() +1);
		curDate=formatDate(requestDate,'yyyy-MM-dd');
		daily(url,storeId,curDate);

	});
	
function daily(k_url,k_storeId,k_payedDate,k_productTypeId){
	var k_data={
	storeId:k_storeId,
	payedDate:k_payedDate,
	productTypeId:k_productTypeId
	};
	$.ajax({
		url:k_url,
		type:"GET",
		data:k_data,
		success:function(data){
			var k='';
			var v_data=data.data;
			var count=0;
			for(var i=0;i<v_data.length;i++) {					
				k=k+"<ul><li>"+v_data[i].recordDate+"</li><li>"+v_data[i].productName+"</li><li>"+v_data[i].marckedPrice+"</li><li>"+v_data[i].count+"</li><li>"+v_data[i].payedAmount+"</li><li>"+v_data[i].payedMethod+"</li><li>"+v_data[i].customerName+"</li><li>"+v_data[i].auditOrChild+"</li><li>"+v_data[i].vipAttr+"</li><li>"+v_data[i].salesmanName+"</li></ul>";
				count=count+v_data[i].payedAmount;				
			}
			$("#content_daily").html(k);
			$("#k_store").html(storeName);
			$("#k_date").html(k_payedDate);
			$("#v-count").html(count);
		}
	})
}
