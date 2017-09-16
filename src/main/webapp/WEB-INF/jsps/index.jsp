<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style>
			*{margin:0;padding: 0;}
			#index_top{position:absolute; top:0;margin-left:230px;z-index:1000;}
			#index_body{position: absolute;top:62px;left: 230px;z-index:10;}
		
			
		</style>
	</head>
	<body style="overflow-x:hidden">
		<iframe src="index_left" width="100%" id="index_left" height="2065" scrolling="no"></iframe>
		<iframe src="index_top" id="index_top"  height="60" scrolling="no"></iframe>
		<iframe src="index_body" id="index_body" width="300" height="2000" scrolling="no"></iframe>
		<script src="../backend/js/moment/jquery-1.9.1.min.js"></script>
		<script>
			$(document).ready(
				function(){
					var hei=$(window).height();
					var wid=$(window).width();
					var widt=wid-239;
					var heig=$("#index_body").scrollHeight;
				
					$("#index_top").css("width",widt);
				    $("#index_body").css("width",wid-239);
				    
				}
			)
		</script>
	</body>
</html>
