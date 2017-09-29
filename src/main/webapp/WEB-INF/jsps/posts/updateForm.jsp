<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>post edition</title>

<!-- Bootstrap -->
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
		<form id="form-update" method="POST" action="${post.id}" class="form-horizontal form-label-left">

			<input type="hidden" name="_method" value="PUT">
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">职位名 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input type="text" class="form-control" placeholder="岗位名" disabled="disabled" name="name" value="${post.name }">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">状态 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="dataStatus" class="form-control">
						<option value="NORMAL" <c:if test="${post.dataStatus eq 'NORMAL'}"> selected="selected"</c:if>>正常</option>
						<option value="FORBID" <c:if test="${post.dataStatus eq 'FORBID'}"> selected="selected"</c:if>>禁用</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">岗位描述 </label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<textarea class="form-control" rows="3" name="description"> ${post.description } </textarea>
				</div>
			</div>

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
					<button type="submit" class="btn btn-success">提交</button>
				</div>
			</div>

		</form>
	</div>
	<!-- /modal body -->

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