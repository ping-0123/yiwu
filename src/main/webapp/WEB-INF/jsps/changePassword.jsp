<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>

<link href="../assets/font-awe	some/css/font-awesome.min.css" rel="stylesheet">
<link href="../assets/bootstrap/css/bootstrap.min.css">
<link href="../assets/bootstrap-validator/css/bootstrapValidator.min.css" rel="stylesheet">
</head>
<body>

	<!-- modal header -->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">×</span>
		</button>
		<h4 class="modal-title" id="myModalLabel">修改</h4>
	</div>
	<!-- /modal header -->
	
		<!-- modal body -->
	<div class="modal-body">
		<form id="changePassword" method="POST" action="./users/<shiro:principal />/password" class="form-horizontal form-label-left">
			
			<input type="hidden" name="_method" value="PUT">
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">原密码 <span class="required">*</span></label>
					<div class="col-md-9 col-sm-9 col-xs-12">
						<input name="oldPassword" type="password" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" required="required" type="text">
					</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">新密码 <span class="required">*</span></label>
					<div class="col-md-9 col-sm-9 col-xs-12">
						<input name="newPassword" type="password" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" required="required" type="text">
					</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">确认新密码 <span class="required">*</span></label>
					<div class="col-md-9 col-sm-9 col-xs-12">
						<input name="password" type="password" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" required="required" type="text">
					</div>
			</div>
			
			
			<div class="ln_solid"></div>
				<div class="form-group">
					<div class="col-md-9 col-sm-9 col-xs-9 col-md-offset-3">
						<button type="submit" class="btn btn-success">提交</button>
					</div>
			</div>
		</form>
	</div>
	<!-- end modal body -->

<script type="text/javascript" src="../assets/jquery/jquery-1.9.1.min.js"></script>
<script  type="text/javascript" src="../assets/bootstrap/js/bootstrap.min.js"></script>
<script src="../assets/bootstrap-validator/js/bootstrapValidator.min.js" type="text/javascript"></script>
<script src="../backend/main.js" type="text/javascript"></script>
<script type="text/javascript">
	$('#changePassword').submit(function(){
		$.ajax({
			url: $(this).attr("action"),
			type: $(this).attr("method"),
			data: $(this).serialize(),
			success:function(data){
				window.history.back(-1);
			}
		});
		return false;
	});
</script>
</body>
</html>