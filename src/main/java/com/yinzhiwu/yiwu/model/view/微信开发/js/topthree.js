var url=BaseUrl+"/api/business/getTopThree";
	function getTopThree(){
		$.ajax({
			type:'GET',
			url:url,
			success:function(data){
			var src="../img/photo.jpg";
			var fir='';
			var sec='';
			var thr='';
			if (data.result) {
			var data1=data.data[0];
			if (data1.headIconUrl!=undefined) {
				data1.headIconUrl=data1.headIconUrl;
			}else{
				data1.headIconUrl=src;
			}
			fir=fir+'<div class="ranking"><div class="left"><img src="../img/first.jpg"></div><div class="mid"><span class="portrait"><img src='+data1.headIconUrl+'></span>';
			fir=fir+'<p>'+data1.distributerApiView.name+'</p>';
			fir=fir+'<p>注册日期 : '+data1.distributerApiView.registerDate+'</p></div>';
			fir=fir+'<div class="right"><ul>';
			fir=fir+'<li>总收入:'+data1.accumulativeBrokerage+'</li>';
			fir=fir+'<li>转发次数 : '+(data1.myShareTweetTimes+data1.subordinateShareTweetTimes)+'</li>';
			fir=fir+'<li>会员总数 : '+(data1.subordinateCount+data1.secondaryCount)+'</li>';
			fir=fir+'<li>成交单数 : '+(data1.subordinateOrderCount+data1.secondaryOrderCount)+'</li>';
			fir=fir+'</ul></div></div></div>';
				$("#k_fir").html(fir);

			var data2=data.data[1];
			if (data2.headIconUrl!=undefined) {
				data2.headIconUrl=data2.headIconUrl;
			}else{
				data2.headIconUrl=src;
			}
			sec=sec+'<div class="ranking"><div class="left"><img src="../img/second.jpg"></div><div class="mid"><span class="portrait"><img src='+data2.headIconUrl+'></span>';
			sec=sec+'<p>'+data2.distributerApiView.name+'</p>';
			sec=sec+'<p>注册日期 :'+data2.distributerApiView.registerDate+'</p></div>';
			sec=sec+'<div class="right"><ul>';
			sec=sec+'<li>总收入 :  '+data2.accumulativeBrokerage+'</li>';
			sec=sec+'<li>转发次数 : '+(data2.myShareTweetTimes+data2.subordinateShareTweetTimes)+'</li>';
			sec=sec+'<li>会员总数 : '+(data2.subordinateCount+data2.secondaryCount)+'</li>';
			sec=sec+'<li>成交单数 : '+(data2.subordinateOrderCount+data2.secondaryOrderCount)+'</li>';
			sec=sec+'</ul></div></div></div>';
				$("#k_sec").html(sec);

			var data3=data.data[2];
			if (data3.headIconUrl!=undefined) {
				data3.headIconUrl=data3.headIconUrl;
			}else{
				data3.headIconUrl=src;
			}
			thr=thr+'<div class="ranking"><div class="left"><img src="../img/third.jpg"></div><div class="mid"><span class="portrait"><img src='+data3.headIconUrl+'></span>';
			thr=thr+'<p>'+data3.distributerApiView.name+'</p>';
			thr=thr+'<p>注册日期 :'+data3.distributerApiView.registerDate+'</p></div>';
			thr=thr+'<div class="right"><ul>';
			thr=thr+'<li>总收入 :  '+data3.accumulativeBrokerage+'</li>';
			thr=thr+'<li>转发次数 : '+(data3.myShareTweetTimes+data3.subordinateShareTweetTimes)+'</li>';
			thr=thr+'<li>会员总数 : '+(data3.subordinateCount+data3.secondaryCount)+'</li>';
			thr=thr+'<li>成交单数 : '+(data3.subordinateOrderCount+data3.secondaryOrderCount)+'</li>';
			thr=thr+'</ul></div></div></div>';
				$("#k_thr").html(thr);
			}
			else{
			alert(data.msg);
		}
		}
		})	
	}