<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create post</title>

</head>
<body>

	<!-- modal header -->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">×</span>
		</button>
		<h4 class="modal-title" id="myModalLabel">新增部门</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-create"  method="POST" action="${pageContext.request.contextPath}/system/employees"  class="form-horizontal form-label-left">

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">姓名 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input id="name" name="name" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="name" required="required" type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">性别 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="gender" class="form-control">
						<option value="MALE" selected="selected">男</option>
						<option value="FEMALE">女</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">手机号码 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<!--  <input type="text" class="form-control" placeholder="设置之后不可修改"  name="name" value="${post.name }"> -->
					<input id="cellphone" name="cellphone" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="name"  required="required" type="text">
				</div>
			</div>


			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">邮箱 </label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control" rows="3" name="email" />
				</div>
			</div>

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
					<button type="submit"  class="btn btn-success">提交</button>
				</div>
			</div>

		</form>
	</div>
	
	<script type="text/javascript">
		$('#form-create').submit(function(){
			$.ajax({
				url: $(this).attr("action"),
				type: $(this).attr("method"),
				data: $(this).serialize(),
				success:function(data){
					if(data.result){
						$('.modal-create').modal('hide');
						TABLE.order([CLOUMN_CREATE_TIME,'desc']).draw();
					}else
						showSaveFailureModal(data.msg);						
				}
			});
			return false;
		});
	</script>
</body>
</html>