var ajaxUrl = "http://192.168.0.115:8080/yiwu/";
var currentDate = new Date();
var year=currentDate.getFullYear();
var month = currentDate.getMonth();
var districtId;
var productTypeId;


loadDistrict();
loadProductLines();
loadRevenue(113,2017,3,0);

function loadYearMonth(){
	$('.year_month').text(year + "年" + month + "月");
}

function loadRevenue(v_districtId,
					v_year,
					v_month,
					v_productTypeId){
						
	var url =ajaxUrl +"api/revenue/getMonthlyRevenue";

	
	$.get(url,
		{districtId:v_districtId,
		year:v_year,
		month:v_month,
		productTypeId:v_productTypeId},
		function(data){
			writeLeftDate(data.data);
//			writeHeadStores(data.data);
			var arrayDayRevenueSum = new Array(data.data.length);
			var arrayStoreRevenueSum = new Array(data.data[0].length + 1);
			var arrayStorePlanSum = new Array(data.data[0].length +1);
//			writeRevenue();
			
			function writeRevenue(){
				var t = "";
				for(var i=0; i<data.data.length; i++){
					t=t + "<ul class=\"statistics\">";
					var sumDay = 0;
					for(var j=0; j<data.data[i].length; j++){
						var v_amount = data.data[i][j].amount;
						if(v_amount>0){
							t = t+"<li>" + v_amount + "</li>";
						}else{
							t = t+"<li></li>";
						}
						sumDay = sumDay + v_amount;
//						arrayDayRevenueSum[i] =0 + arrayDayRevenueSum[i] + v_amount;
//						arrayStoreRevenueSum[j] = arrayStoreRevenueSum[j] + v_amount;
					}
					t= t+"<li>" + sumDay + "</li>";
					t= t+ "</ul>";
					arrayDayRevenueSum[i]= sumDay;
//					arrayStoreRevenueSum[j+1]  = arrayStoreRevenueSum[j+1] +arrayDayRevenueSum[i];
					
				}
				t=t+"<ul class=\"statistics bottom\">";
				for(var j=0; j<data.data[0].length +1; j++){
					t=t+"<li>" +0 +"</li>";
				}
				t=t+"</ul>"
				
				$('.revenue_amount').html(t);
			}

	});
}


function writeLeftDate(v_data){
	var t="<li class=\"posion_nav\"></li>";
	for(var i=0; i<v_data.length; i++){
		t=t+ "<li>"+ v_data[i][0].date + "</li>";
	}
	t = t+"<li class=\"bottom\">Sub Total</li>";
	t = t+"<li class=\"bottom\">Plan</li>";
	t = t+"<li class=\"bottom\">Rate</li>";
	
	$('.left ul').html(t);
}

function writeHeadStores(v_data){
	var t = "";
	for(var i=0; i<v_data[0].length;i++){
		t = t+ "<li>" + v_data[0][i].storeName + "</li>";
	}
	t= t+ "<li>SUM</li>";
	$('#head_stores').html(t);
}

function loadDistrict(){
	var url = ajaxUrl +"api/district/list";
	$.ajax({ 
			type: "Get", 	
			url: url,
			dataType: "json",
			async:false,
			success: function(data) {
				var district;
				var t="<option value=\"0\">城市</option>";
				for(var i=0; i<data.length; i++){
					district = data[i];
					t= t+ "<option value=\"" + district.id + "\">" + district.name.replace("区域","")+ "</option>";
				}
				$('.all select').html(t);
//				loadStores(data[0].id);
			},
			error: function(jqXHR){     
			   alert("loadDistrict 发生错误：" + jqXHR.status); 
			},  
		});	
}


function loadProductLines(){
	var url = ajaxUrl + "api/productType/getAll";
	$.ajax({
		type:"get",
		url:url,
		dataType: "json",
		async: false,
		success: function(data){
			var productLine;
			var t="<option value=\"0\">全线产品</option>";
			for(var i=0; i<data.data.length; i++){
				productLine=data.data[i];
				t= t+ "<option value=\"" + productLine.id + "\">" + productLine.name+ "</option>";
			}
			$('.product select').html(t);
		},
		async:true
	});
}


function format(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
