<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="ping" uri="http://yinzhiwu.com/yiwu/tags/ping"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form class="form-horizontal form-label-left input_mask" id="form-update">

		<!-- <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
			<input type="text" class="form-control has-feedback-left" id="inputSuccess2" placeholder="First Name"> <span
				class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
		</div>

		<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
			<input type="text" class="form-control" id="inputSuccess3" placeholder="Last Name"> <span class="fa fa-user form-control-feedback right"
				aria-hidden="true"></span>
		</div>

		<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
			<input type="text" class="form-control has-feedback-left" id="inputSuccess4" placeholder="Email"> <span
				class="fa fa-envelope form-control-feedback left" aria-hidden="true"></span>
		</div>

		<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
			<input type="text" class="form-control" id="inputSuccess5" placeholder="Phone"> <span class="fa fa-phone form-control-feedback right"
				aria-hidden="true"></span>
		</div>
 -->	
 		<input type="hidden" name="_method" value="PUT">
 		<input type="hidden" name="id" value="${dept.id}">
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">部门名称</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" class="form-control" name="name" value="${dept.name }">
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">办公电话 </label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" class="form-control"  name="officialAccount" value="${dept.officialAccount }">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">办公地址</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" class="form-control"  value="${ping:getDetailAddress(dept.officialAddress) }">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">备注 
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<textarea class="form-control" rows="2" name="description"> ${dept.description } </textarea>
			</div>
		</div>
		<div class="ln_solid"></div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
			<div class="col-md-6 col-sm-6 col-xs-12" align="left">
				<button class="btn btn-primary" type="reset">重置</button>
				<button type="submit" class="btn btn-success">修改</button>
			</div>
		</div>

	</form>
	
	<script type="text/javascript">
		$('#form-update').submit(function(){
			$.ajax({
				url: $(this).attr("action"),
				type: $(this).attr("method"),
				data: $(this).serialize(),
				success:function(data){
					$('.modal-update').modal('hide');
					TABLE.draw();
				}
			});
			return false;
		});
	</script>
</body>
</html>