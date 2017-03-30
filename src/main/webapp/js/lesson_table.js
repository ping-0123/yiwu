//var ajaxUrl = "";
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
	var weekdayNames = ["MON","TUE","WED","THU","FRI","SAT","SUN"];
	$.ajax({ 
			type: "POST", 	
			url: url,
			dataType: "json",
			data:v_param,
			success: function(data) {
				var t ="<thead><tr>";
				var len = 0;
				for(var i=0; i<7; i++){
					t=t+"<th>" + weekdayNames[i] + "<br> <small>" 
						+ data[i].date + "</small></th>";
					if(len<data[i].list.length){
						len=data[i].list.length;
					}
				}
				t= t+ "</thead></tr>";
				
				t=t+"<tbody>";
				for(var j=0; j<len; j++){
					t=t+"<tr>";
					for(var k=0; k<7; k++){
						t= t+"<td>";
						if(data[k].list.length >j){
							var lesson = data[k].list[j]
							var courseTypeAbbr;
							if(lesson.courseType=="封闭式"){
								courseTypeAbbr = "封";
								t=t + "<span class=\"closeType\">" + courseTypeAbbr +"</span>";
							}
							else{
								courseTypeAbbr="开";
								t=t + "<span class=\"openType\">" + courseTypeAbbr +"</span>";
							}
							t=t +  "<span class=\"lesson_time\">" + lesson.startTime.substr(0,5) + "-" + lesson.endTime.substr(0,5) + "</span>";
							t=t + "<small class=\"dance_type\">" + lesson.danceName.replace("少儿", "") + lesson.danceGrade + "</small>";
							t=t+"<small class=\"teacher\">" + lesson.dueTeacherName + "</small>";
							if (lesson.courseType=="开放式"){
								var appointedStatus;
								if(lesson.attendedStatus=="APPONTED")
									appointedStatus = "已预约";
								else
									appointedStatus = "未预约";
								t=t+"<span class=\"state\">" + appointedStatus  
									+ "("+ lesson.appointedStudentCount+ "/" + lesson.maxStudentCount + ")</span>";
							}else{
								t=t+"<span class=\"state\">" + "已预约:" + lesson.attendedStudentCount +"人</span>";
							}

						}
					
						t= t+ "</td>";
					}
					t=t+"</tr>";
				}
				t = t+ "</tbody>";
				
				$("#lessonTable").html(t);
			},
			error: function(jqXHR){     
			   alert(" loadLessonTable 发生错误：" + jqXHR.status);  
			},  
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

function loadDistrict(){
	var url = ajaxUrl +"api/district/list";
	$.ajax({ 
			type: "Get", 	
			url: url,
			dataType: "json",
			success: function(data) {
				var district;
				var t="";
				for(var i=0; i<data.length; i++){
					district = data[i];
					t= t+ "<option value=\"" + district.id + "\">" + district.name.replace("区域","")+ "</option>";
				}
				$("#district").html(t);
				loadStores(data[0].id);
			},
			error: function(jqXHR){     
			   alert("loadDistrict 发生错误：" + jqXHR.status); 
			},  
		});	
}



function loadStores(v_districtId){
	var url = ajaxUrl + "api/store/list?districtId=" + v_districtId;
	var v_data = data;
	$.ajax({ 
			type: "GET", 	
			url: url,
			dataType: "json",
			success: function(data) {
				var store;
				var ts="";
				for(var i=0; i<data.length; i++){
					store = data[i];
					ts= ts+ "<option value=\"" + store.id + "\">" + store.name + "</option>";
				}
				$("#store_2").html(ts);
				v_data.storeId = data[0].id;
				loadLessonTable("api/lesson/weeklist",v_data);
			},
			error: function(jqXHR){     
			   alert("loadStores 发生错误：" + jqXHR.status); 
			},  
		});	
}
