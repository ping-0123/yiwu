<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create post</title>
<style type="text/css">
</style>
</head>
<body>

	<!-- modal header -->
	<div class="modal-header">
		<button type="button" class="close"  data-dismiss="modal">
			<span aria-hidden="false">×</span>
		</button>
		<h4 class="modal-title" id="myModalLabel">设置  ${employee.name } 的岗位</h4>
	</div> 
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<shiro:hasPermission name="employees:update:*">
			<button type="button" data-remote="./${employee.id }/posts/createForm" class="btn btn-primary" data-toggle="modal" data-target=".modal-create-post">
				<span class="glyphicon glyphicon-plus" aria-hidden="false"></span> 新增
			</button>
		</shiro:hasPermission>
		<div class="clearfix"></div>
		<div>
			<table id="postsDatatable" class="table table-bordered table-hover table-condensed" width="100%">
			</table>
		</div>
		
		<div>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
		</div>
	</div>
	
	<!-- modal create form -->
	<div class="modal fade modal-create-post" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md" style="height:800px">
			<div class="modal-content"></div>
		</div>
	</div>
	<div class="modal fade .modal-update-post" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md" style="height:800px">
			<div class="modal-content"></div>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>