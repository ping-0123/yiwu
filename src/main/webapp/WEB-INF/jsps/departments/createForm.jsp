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
	<form method="POST" action="./" class="form-horizontal form-label-left input_mask" id="form-create">
		<input type="hidden" name="parent.id" value="${parentId}">
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">部门名称</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" class="form-control" name="name" value="">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">办公电话 </label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" class="form-control" name="officialAccount" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">办公地址</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<input type="text" class="form-control" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">备注 </label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<textarea class="form-control" rows="2" name="description"> </textarea>
			</div>
		</div>
		<div class="ln_solid"></div>
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
			<div class="col-md-6 col-sm-6 col-xs-12" align="left">
				<button class="btn btn-primary" type="reset">重置</button>
				<button type="submit" class="btn btn-success">保存</button>
			</div>
		</div>
	</form>
	</div>

	<script type="text/javascript">
		$('#form-create').submit(function() {
			$.ajax({
				url : $(this).attr("action"),
				type : $(this).attr("method"),
				data : $(this).serialize(),
				success : function(data) {
					$('.modal-create').modal('hide');
					loadDepartmentZtree();
				}
			});
			return false;
		});
	</script>
</body>
</html>