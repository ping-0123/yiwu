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
		<h4 class="modal-title" id="myModalLabel">新增岗位</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form method="POST" action="${pageContext.request.contextPath}/system/posts" class="form-horizontal form-label-left">

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">职位名 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<!--  <input type="text" class="form-control" placeholder="设置之后不可修改"  name="name" value="${post.name }"> -->
					<input id="name" name="name" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="name" placeholder="设置之后不可修改" required="required" type="text">
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

	<!-- modal footer
	<div class="modal-footer">
		<button type="button" class="btn btn-success" data-dismiss="modal">保存</button>
	</div>  -->
	<!-- /modal footer -->

</body>
</html>