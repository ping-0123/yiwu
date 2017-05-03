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

function loadDistrict(){
	var defaultDistrictId=82;
	var existDefault=false;
	var url = ajaxUrl +"api/district/list";
	$.ajax({ 
			type: "Get", 	
			url: url,
			dataType: "json",
			timeout:5000,
			success: function(data) {
				var district;
				var t="";
				for(var i=0; i<data.length; i++){
					district = data[i];
					t= t+ "<option value=\"" + district.id + "\">" + district.name.replace("区域","")+ "</option>";
					if (district.id==defaultDistrictId){
						existDefault = true;
					}
				}
				$('.district select').html(t);
				if(existDefault){
					$('.district select').val(defaultDistrictId);
					loadStores(defaultDistrictId);
				}else{
					loadStores(data[0].id);
				}
			},
			error: function(jqXHR){     
			},  
		});	
}


function loadStores(v_districtId){
	var url = ajaxUrl + "api/store/list?districtId=" + v_districtId;
	var defaulStoreId=61;
	var existDefault=false;
	var v_data = data;
	$.ajax({ 
			type: "GET", 	
			url: url,
			dataType: "json",
			timeout: 5000,
			success: function(data) {
				var store;
				var ts="";
				for(var i=0; i<data.length; i++){
					store = data[i];
					ts= ts+ "<option value=\"" + store.id + "\">" + store.name + "</option>";
					if(store.id==defaulStoreId){
						existDefault = true;
					}
				}
				$('.store select').html(ts);
				if(existDefault){
					$('.store select').val(defaulStoreId);
					v_data.storeId = defaulStoreId;
				}else{
					v_data.storeId = data[0].id;
				}
				loadStoreAddress(v_data.storeId);
				loadLessonTable("api/lesson/weeklist",v_data);
			},
			error: function(jqXHR){     
			},  
		});	
}


	function loadStores_v1_1_0(v_districtId){
			var url = ajaxUrl + "api/store/list?districtId=" + v_districtId;
			var v_data=data;
			$.ajax({
				url:url,
				type:"GET",
				dataType:"json",
				timeout:5000,
				success:function(data){
					//alert(JSON.stringify(data));
					var li='';
					for (var i = data.length - 1; i >= 0; i--) {
					li= li+ "<li store-id=\"" + data[i].id + "\">[" + data[i].name + "]</li>";					
					}
					$('#k_storeid').html(li);
					$('#m_storeid').html(data[0].name);
					loadStoreAddress(data[0].id);
					v_data.storeId=data[0].id;
					loadLessonTable("api/lesson/weeklist",v_data);

				$('.list_store li').click(function(){
  					$('.text_left_store').text(($(this).text())); 
  					v_data.storeId=$(this).attr("store-id");
  					loadLessonTable("api/lesson/weeklist",v_data);
  					loadStoreAddress(v_data.storeId);
  													}) 
				}
			})
	}

	function loadStoreAddress(v_storeId){
			var url=ajaxUrl+"api/store/id/"+v_storeId;
			$.ajax({
				url:url,
				type:"GET",
				dataType:"json",
				timeout:5000,
				success:function(data){
				$('.address_week .first_line span').html(data.name);
				$('#address span').html(data.address);
				$('#phone span').html(data.telePhone);
			
			}

			})
	}

	function loadLessonTable(v_url,v_param){
	/**
	 * 
	v_pram ={
				storeId : '63',
				courseType: '',
				teacherName: '',
				danceCatagory: '',
				date: '2017-03-18',
				weChat: 'oqSnljhna3lMmHtKODoSvWQNfUTc'
			};
	 */
	var url = ajaxUrl + v_url;
	var weekdayNames = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
	var weekdayNamesCN=["周 日","周 一","周 二","周 三","周 四","周 五","周 六"];
	var v_data=v_param;
	var v_curDate = format(new Date(), 'yyyy-MM-dd');
	$.ajax({ 
			type: "POST", 	
			url: url,
			dataType: "json",
			data:v_data,
			timeout:5000,
			success: function(data) {

				var t ="<thead><tr>";
				var len = 0;
				var class_current="nonCurrent";
				for(var i=0; i<7; i++){
					if(v_curDate == data[i].date){
						class_current="current";
					}else{
						class_current ="nonCurrent";
					}
					t=t+"<th class=\"" + class_current + "\">" 
						+ weekdayNames[data[i].weekday-1] + "<br/>"
						+weekdayNamesCN[data[i].weekday-1]+"</br> <small>"
						+ data[i].date + "</small></th>";
					if(len<data[i].list.length){
						len=data[i].list.length;
					}
				}
				t= t+ "</tr></thead>";
				
				t=t+"<tbody>";
				for (var j = 0; j <len; j++) {
					t=t+"<tr>";
					for (var k=0;k<7;k++) {
						t=t+"<td>";
						if (data[k].list.length>j) {
							var lesson=data[k].list[j];
						
							if(lesson.courseType=="封闭式"){
								t=t+"<ul class=\"close_type\">" ;
							}
							else if(lesson.courseType =="开放式"){
								if(lesson.subCourseType == "开放式A"){
									t = t +"<ul class=\"open_A_type\">" ;}
								else if(lesson.subCourseType == "开放式B"){
									t = t +"<ul class=\"open_B_type\">" ;}
							}
							
							t= t+ "<li><small>" + lesson.danceName.replace("少儿","") + lesson.danceGrade  + "</small>" +"</li><li><small>"
								+lesson.startTime.substring(0,5)+"-"+lesson.endTime.substring(0,5)+"</small></li><li><small>"
								+lesson.dueTeacherName+"</small><li>" ;
								
							if(lesson.courseType=="封闭式"){
							//	封闭式的预约：门店点名人数|教师点名人数|班级人数
								var classStudentCount =0;
								if(lesson.lessonDate >=new Date()){
									classStudentCount = lesson.attendedStudentCount;
								}else{
									classStudentCount = lesson.checkedInsStudentCount;
								}
								t= t+ "<li><small>预约:" + lesson.storeManCallRollCount
									+ "/" + lesson.teacherCallRollCount 
									+ "/" + classStudentCount + "</small></li></ul>";
							}
							else if(lesson.courseType =="开放式"){
							//	开放式的预约： 预约人数/签到人数/容量
								t= t+ "<li><small>预约:" + lesson.appointedStudentCount 
									+ "/" +lesson.checkedInsStudentCount
									+ "/" +lesson.maxStudentCount
									+"</small></li></ul>";
							}
						}
						t=t+"</td>";
					}
					t=t+"</tr>"
				}
				t=t+"</tbody>";
				$("#lesson_table").html(t);
			$('.list_type li').click(function(){
  				$('.text_left_type').text(($(this).text())); 
  				v_data.courseType=$(this).val();
  				alert(v_data.courseType);
  				loadLessonTable("api/lesson/weeklist",v_data);
  								}) 
			},
			error: function(jqXHR){     
			},  
		});	
}

