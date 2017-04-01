//var ajaxUrl = "http://192.168.0.115:8080/yiwu/";
var ajaxUrl = "http://yzw.chenksoft.com:8080/yiwu/";
//var ajaxUrl = "";
var currentDate = new Date();
var year=currentDate.getFullYear();
var month = currentDate.getMonth();
var districtId =0;
var productTypeId=0;


loadDistrict();
loadProductLines();
loadYearMonth();
loadRevenue(districtId,year,month,productTypeId);

$('.all select').change(function(){
	districtId = $(this).val();
	loadRevenue(districtId,year,month,productTypeId);
});

$('#productLine').change(function(){
	productTypeId = $(this).val();
	loadRevenue(districtId,year,month,productTypeId);
});

$('#lastMonth').click(function(){
	if(month>1){
		month = month -1;
	}else{
		month=12;
		year=year-1;
	}
		loadYearMonth();
		loadRevenue(districtId,year,month,productTypeId);
});

$('#nextMonth').click(function(){
	if(month<12){
		month= month +1;
	}else{
		month=1;
		year = year + 1;
	}
	loadYearMonth();
	loadRevenue(districtId,year,month,productTypeId);
});

function loadYearMonth(){
	$('#year_month').text(year   + "年" + month + "月");
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
			var revenues = data.data.revenues;
			var plans = data.data.plans;
			var rows = revenues.length;
			var cols= revenues[0].length;
			
			var arrayDayRevenueSum = new Array(revenues.length);
			for(var m=0; m<revenues.length; m++){
				arrayDayRevenueSum[m]=0;
			}
			var arrayStoreRevenueSum = new Array(revenues[0].length + 1);
			var arrayRate = new Array(revenues[0].length + 1);
			var arrayStorePlanSum = new Array(revenues[0].length +1);
			for(var k=0; k<arrayStoreRevenueSum.length; k++){
				arrayStoreRevenueSum[k]=0;
				arrayRate[k] =0
				arrayStorePlanSum[k]=0
			}
			
			
//			alert(revenues[0][0].date);
			writeLeftDate(revenues);
			writeHeadStores(revenues);
			writeRevenue();
			
			function writeRevenue(){
				var t = "";
			
				for(var i=0; i<revenues.length; i++){
					t=t + "<ul class=\"statistics\"><li></li>";
					var sumDay = 0;
					for(var j=0; j<revenues[i].length; j++){
						var v_amount = revenues[i][j].amount;
						if(v_amount != 0){
							t = t+"<li><a href=\"map.html?store-Id="+revenues[i][j].storeId+"&date-id="+revenues[i][j].date+"\" store-Id='+revenues[i][j].storeId+' date-id='+revenues[i][j].date+'>" +thousandSignNumber(v_amount.toFixed(0)) + "</a></li>";
						}else{
							t = t+"<li></li>";
						}
						arrayDayRevenueSum[i] =0 + arrayDayRevenueSum[i] + v_amount;
						arrayStoreRevenueSum[j] =0 + arrayStoreRevenueSum[j] + v_amount;
					}
					t= t+"<li>" + thousandSignNumber(arrayDayRevenueSum[i].toFixed()) + "</li>";
					t= t+ "</ul>";
					arrayStoreRevenueSum[cols]  = 0 + arrayStoreRevenueSum[cols] +arrayDayRevenueSum[i];
					
				}
				
				//纵向求和
				t=t+"<ul class=\"statistics bottom\"><li></li>";
				for(var j=0; j<arrayStoreRevenueSum.length; j++){
					t=t+"<li>" +thousandSignNumber(arrayStoreRevenueSum[j].toFixed()) +"</li>";
				}
				t=t+"</ul>"
				
				//营业计划
				t=t+"<ul class=\"statistics bottom\"><li></li>";
				for(var j=0; j<cols; j++){
					arrayStorePlanSum[cols] =arrayStorePlanSum[cols] + plans[j].amount;
					arrayStorePlanSum[j] = plans[j].amount;
					t = t + "<li>" + thousandSignNumber(plans[j].amount.toFixed(0)) +"</li>";
				}
				t = t + "<li>" + thousandSignNumber(arrayStorePlanSum[cols].toFixed(0)) +"</li>";
				t=t+"</ul>"
				
				//完成率
				t=t+"<ul class=\"statistics bottom\"><li></li>";
				for(var j=0; j<=cols; j++){
					if (arrayStorePlanSum[j]>0){
						arrayRate[j] = arrayStoreRevenueSum[j]/arrayStorePlanSum[j];
						t = t + "<li>" + (arrayRate[j] *100).toFixed(1) +"%</li>";
					}else{
						t = t + "<li></li>";
					}
				}
				t=t+"</ul>"
				
				$('.content').html(t);
			}

	});
}


function writeLeftDate(v_data){
	var t="";
	for(var i=0; i<v_data.length; i++){
		t=t+ "<li>"+ v_data[i][0].date + "</li>";
	}
	t = t+"<li class=\"bottom_text\">Sub Total</li>";
	t = t+"<li class=\"bottom_text\">Plan</li>";
	t = t+"<li class=\"bottom_text\">Rate</li>";
	
	$('#left_date').html(t);
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
				var t="<option value=\"0\">全部</option>";
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
