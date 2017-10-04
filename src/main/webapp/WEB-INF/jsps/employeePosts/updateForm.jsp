<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create post</title>

</head>
<body>

	<!-- modal header -->
	<div class="modal-header">
		<button type="button" class="close" onclick="hideModal();">
			<span aria-hidden="true">×</span>
		</button>
		<h4 class="modal-title" id="myModalLabel">修改员工岗位</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-update-post"  method="POST" action="../employeePosts/${ep.id}"  class="form-horizontal form-label-left">
			
			<input type="hidden" name="_method" value="PUT">
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">员工工号 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<input   value="${ep.employee.number }" class="form-control col-md-7 col-xs-12" disabled="disabled" required="required"  type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">员工姓名 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<input   value="${ep.employee.name }" class="form-control col-md-7 col-xs-12" disabled="disabled" required="required"  type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">任职部门 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<input   value="${ep.department.name }" class="form-control col-md-7 col-xs-12" disabled="disabled" required="required"  type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">任职岗位 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<input   value="${ep.post.name }" class="form-control col-md-7 col-xs-12" disabled="disabled" required="required"  type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">任职开始日期 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<input  value="<fmt:formatDate value="${ep.startTime}" pattern="yyyy-MM-dd" />" type="date" disabled="disabled" class="form-control col-md-7 col-xs-12"  required="required" >
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">任职结束日期 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<input  name="endTime"  value="<fmt:formatDate value="${ep.endTime}" pattern="yyyy-MM-dd" />" class="form-control col-md-7 col-xs-12" required="required" type="date">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">是否为主职务 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input  class="" type="radio" name="isDefault" value="true">是
					<input  class="" type="radio" name="isDefault" value="false" checked>否
				</div>
			</div>

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
					<button type="submit"  class="btn btn-success">保存</button>
				</div>
			</div>

		</form>
	</div>
	
	<script type="text/javascript">
		$('#form-update-post').submit(function(){
			$.ajax({
				url: $(this).attr("action"),
				type: $(this).attr("method"),
				data: $(this).serialize(),
				success:function(data){
					if(data.result){
						$('.modal-update-post').modal('hide');
						postDatatable.draw();
					}else
						showSaveFailureModal(data.msg);						
				}
			});
			return false;
		});
		
		function hideModal(){
			$(".modal-update-post").modal("hide");
		}
	</script>
</body>
</html>