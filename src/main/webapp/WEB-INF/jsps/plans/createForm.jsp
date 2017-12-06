<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String path = request.getContextPath();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String date = df.format( new Date());
	
%>

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
		<h4 class="modal-title" >新增岗位</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-create"  method="POST" action="<%=path%>/system/plans"  class="form-horizontal form-label-left">

			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">门店 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<select name="storeId" id="storeId"  class="form-control">
						<c:forEach items="${stores}" var="v">
							<option value="${v.id }" >${v.name }</option>
						</c:forEach>
					</select>
				</div>
		<!-- 	</div>

			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">月份<span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="month"  required=true, type="date" value="<%= date%>">
				</div>
		 </div>

			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">少儿业绩</label>
				<div class="col-md-4 col-sm-4 col-xs-6">
                    <input type="hidden" name="amounts[0].type" value="CHILD">
                    <input class="form-control"  name="amounts[0].amount">
				</div>
			<!-- </div>

			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">成人业绩 </label>
				<div class="col-md-4 col-sm-4 col-xs-6">
                <input type="hidden" name="amounts[1].type" value="ADULT">
                <input class="form-control"  name="amounts[1].amount" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">小主持人业绩 </label>
				<div class="col-md-4 col-sm-4 col-xs-6">
                        <input type="hidden" name="amounts[2].type" value="PRESENTER">
					<input class="form-control"  name="amounts[2].amount" >
				</div>
			</div>


			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-2">
					<button type="submit" class="btn btn-success">提交</button>
				</div>
			</div>

		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$('#form-create').submit(function(){
				$.ajax({
					url: $(this).attr("action"),
					type: $(this).attr("method"),
					data: $(this).serialize(),
					success:function(data){
						$('.modal-create').modal('hide');
						TABLE.order([CLOUMN_CREATE_TIME,'desc']).draw();
					}
				});
				return false;
			});

		});

	</script>
</body>
</html>