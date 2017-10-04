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
		<h4 class="modal-title" id="myModalLabel">新增</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-create"  method="POST" action="./"  class="form-horizontal form-label-left">
			<c:if test="${parent != null }">
				<input type="hidden" name="parent.id" value="${parent.id }">
			</c:if>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">资源代号<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input name="code" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" required="required" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">资源名<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input name="name" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" required="required" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">访问权限<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input name="permission" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" required="required" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">资源路径</label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input name="url" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2"  type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">类型<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="type" class="form-control">
						<option value="MENU" selected="selected">菜单</option>
						<option value="BUTTON">按钮</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">BootStrap图标</label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<!--  <input type="text" class="form-control" placeholder="设置之后不可修改"  name="name" value="${post.name }"> -->
					<input  name="icon" class="form-control col-md-7 col-xs-12" type="text">
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
		$('#form-create').submit(function(){
			$.ajax({
				url: $(this).attr("action"),
				type: $(this).attr("method"),
				data: $(this).serialize(),
				success:function(data){
					if(data.result){
						$('.modal-create').modal('hide');
						location.reload();
					}else
						showSaveFailureModal(data.msg);						
				}
			});
			return false;
		});
	</script>
</body>
</html>