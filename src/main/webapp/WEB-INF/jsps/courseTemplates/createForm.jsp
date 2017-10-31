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

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 模板名 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input name="name" class="form-control " type="text"> 
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 舞种 *</label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="dance.id" class="form-control" required>
						<c:forEach items="${dances }" var="dance">
							<option value="${dance.id }">${dance.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 舞种等级 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="danceGrade.id" class="form-control" required>
						<c:forEach items="${danceGrades }" var="grade">
							<option value="${grade.id }">${grade.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-6"> 课程类型 <span class="required">*</span></label>
				<div class="col-md-3 col-sm-3 col-xs-6">
					<select name="courseType" class="form-control" required>
						<option value="CLOSED">封闭式</option>
						<option value="OPEND">开放式</option>
						<option value="PRIVATE">私教课</option>
					</select>
				</div>
				<label class="control-label col-md-3 col-sm-3 col-xs-6"> 中类<span class="required">*</span></label>
				<div class="col-md-3 col-sm-3 col-xs-6">
					<select name="courseType" class="form-control" required>
						<option value="CLOSED">封闭式</option>
						<option value="OPEND">开放式</option>
						<option value="PRIVATE">私教课</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">手机号码 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<!--  <input type="text" class="form-control" placeholder="设置之后不可修改"  name="name" value="${post.name }"> -->
					<input id="cellphone" name="cellphone" class="form-control col-md-7 col-xs-12"  type="text">
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
		/* $('#form-create').submit(function(){
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
		}); */
		
		$(document).ready(function(){
			$('#form-create').bootstrapValidator({
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields:{
		        	name:{
		                validators: {
		                    notEmpty: {
		                    },
		                    stringLength: {
		                        min: 2,
		                        max: 30
		                    }
		                }
		        	}
		        
		        },
		        
		        submitHandler: function(validator, form, submitButton){
		        	validator.validatte();
		        	$.ajax({
						url: form.attr("action"),
						type: form.attr("method"),
						data: form.serialize(),
						success:function(data){
							if(data.result){
								$('.modal-create').modal('hide');
								TABLE.draw(false);
							}else
								showSaveFailureModal(data.msg);						
						}
					});
		        }
			});
		});
	</script>
</body>
</html>