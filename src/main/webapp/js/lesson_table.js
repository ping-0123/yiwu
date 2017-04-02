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
	var url=ajaxUrl +"api/district/list";
	$.ajax({
		url:url,
		type:"GET",
		dataType:"json",
		success:function(data){
			//alert(JSON.stringify(data));
			var li='';
			//var fir=data[0];
			//var fragment = document.createDocumentFragment();
			for (var i = data.length - 1; i >= 0; i--) {
				//var div=$('<span class="text_left" district-id="'+data.id+'">'+data.name+'</span>')[0];
				//fragment.appendChild(div);
				li=li+"<li k_district-id=\""+data[i].id+"\">"+data[i].name.substring(0,2)+"</li>";
			}
			$('#list').html(li);
			$('.text_left').html(data[1].name.substring(0,2));
			loadStores(data[1].id);
			$('.list li').click(function(){
			//data.districtId
			$('.text_left').text(($(this).text())); 
			var districtId=$(this).attr("k_district-id");
			//alert(districtId);
			loadStores(districtId);
             }) 
		}
	})
}

	function loadStores(v_districtId){
			var url = ajaxUrl + "api/store/list?districtId=" + v_districtId;
			var v_data=data;
			$.ajax({
				url:url,
				type:"GET",
				dataType:"json",
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
				success:function(data){
					//alert(JSON.stringify(data));
					 //console.log(data);
				$('.address_week .first_line span').html(data.name);
				$('#address span').html(data.address);
				$('#phone span').html(data.telePhone);
			
//				$('#phone span').attr("onclick" "location.href='" + data.telePhone +"';");
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
	$.ajax({ 
			type: "POST", 	
			url: url,
			dataType: "json",
			data:v_data,
			success: function(data) {

				var t ="<thead><tr>";
				var len = 0;
				for(var i=0; i<7; i++){
					t=t+"<th>" + weekdayNames[data[i].weekday-1] + "<br/>"+weekdayNamesCN[data[i].weekday-1]+"</br> <small>" 
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
								t=t+"<ul class=\"close_type\"><li>"+ "<small>" + lesson.danceName.replace("少儿","") + "</small>" +"</li><li><small>"+lesson.startTime.substring(0,5)+"-"+lesson.endTime.substring(0,5)+"</small></li><li><small>"+lesson.dueTeacherName+"</small><li><li><small>预约:"+lesson.attendedStudentCount+"/"+lesson.checkedInsStudentCount+"</small></li></ul>";
							}
						else if(lesson.courseType=="开放式"){
							t=t+"<ul class=\"open_type\"><li>" + "<small>" + lesson.danceName.replace("少儿","") + "</small>" +"</li><li><small>"+lesson.startTime.substring(0,5)+"-"+lesson.endTime.substring(0,5)+"</small></li><li><small>"+lesson.dueTeacherName+"</small><li><li><small>预约:"+lesson.appointedStudentCount+"/"+lesson.maxStudentCount+"/"+lesson.checkedInsStudentCount+"</small></li></ul>";
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
			   alert(" loadLessonTable 发生错误：" + jqXHR.status);  
			},  
		});	
}
				