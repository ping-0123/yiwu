	var id='3000018';	 	
	function info(){
	 var url=BaseUrl+"/api/distributer/getById/"+id;
		$.ajax({
		   	type:'GET',
		   	url: url,
		   	success:function(data){
		   	//alert(JSON.stringify(data));
		   	var data;
		   	var	headIconUrl;
		   	var img='';
		   	var info='';
		   	if(data.result){
		   	data=data.data;
		   	if(data.headIconUrl!=undefined){
		   		data.headIconUrl=BaseUrl+data.headIconUrl;
		   	}else{
		   		data.headIconUrl="img/photo.jpg";
		   	}
		   	//alert(data.headIconUrl);
		   	img=img+'<div class="photo"><a href="my/my_information.html?img='+data.headIconUrl+'&name='+data.nickName+'&Id='+data.id+'" img='+data.headIconUrl+' name='+data.nickName+' Id='+data.id+'><img src='+data.headIconUrl+'></a></div>';
		   	info=info+'<ul>';
		   	info=info+'<li><img src="img/'+data.expGradeNo+'.png" width="40" height="auto"></li>';
		   	info=info+'<li><span class="expand">升级还需'+data.neededExpForUpdate+'点经验</span></li>';
		   	info=info+'<li class="name"><a href="my/my_information.html?img='+data.headIconUrl+'&name='+data.nickName+'&Id='+data.id+'" img='+data.headIconUrl+' name='+data.nickName+' Id='+data.id+'>'+data.nickName+'</a></li>';
		   	info=info+'<li class="name"><a href="my/my_information.html?img='+data.headIconUrl+'&name='+data.nickName+'&Id='+data.id+'" img='+data.headIconUrl+' name='+data.nickName+' Id='+data.id+'>ID:'+data.id+'</a></li>';
		   	info=info+'</ul>';	   			
		   			}
		   	$("#my_photo").html(img);
		   	$("#my_information").html(info);
		   	var beatRate=data.beatRate*100+"%";
		   	var beatRate1=data.beatRate*100;
		   	var beatRate2="您击败了"+data.beatRate+"%会员";
		   	$('.anyield').attr("data-text",beatRate);
			$('.anyield').attr("data-percent",beatRate1);
		    $('.anyield').circliful();
		    $('.personal .right p').html(beatRate2);
	   			}	   			
	   		});	
}
	function business(){
		var url=BaseUrl+"/api/business/getperformance";
		$.ajax({
			type:'GET',
			url:url,
			data:{
				distributerId:id
			},
			success:function(data){

				var data=data.data;
				var bg1="推广"+(data.myShareTweetTimes+data.subordinateShareTweetTimes)+"次，成交"+(data.subordinateOrderCount+data.secondaryOrderCount)+"单 >";
				//alert(bg1);
				$("#k_studio").html(bg1);
						}
			});
}


	function wallet(){
		var url=BaseUrl+"";


	}



	