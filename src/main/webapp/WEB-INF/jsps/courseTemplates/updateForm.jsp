<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ping" uri="http://yinzhiwu.com/yiwu/tags/ping"%>
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
		<h4 class="modal-title" id="myModalLabel">修改</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-update"  method="POST" action="./${template.id}"  class="form-horizontal form-label-left">
			<input type="hidden" name="_method" value="PUT">
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-3"> 模板名 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-9">
					<input name="name" class="form-control" type="text" value="${template.name }"> 
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 舞种 *</label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="dance.id" class="form-control" disabled="disabled" required>
							<option value="${template.dance.id }">${template.dance.name }</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 舞种等级 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="danceGrade.id" class="form-control" disabled="disabled" required>
						<option value="${template.danceGrade.id }">${template.danceGrade.name }</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">课程类型<span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="courseType" id="courseType" class="form-control " disabled="disabled"  required>
						<option value="${template.courseType}">${ping:getCourseTypeName(template.courseType)}</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">中类<span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="subCourseType" id="subCourseType" class="form-control" disabled="disabled" required>
						<option value="${template.subCourseType }">${ping.getSubCourseTypeName(template.subCourseType) }</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">课程总节数<span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input  name="times" class="form-control" required type="number" value="${template.times }">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">每节课时长(单位:小时)<span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input  name="hoursPerTime" class="form-control" required  type="text" value="${template.hoursPerTime }">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">开课最小学员数量 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input  name="minStudentCount" class="form-control"  required type="number" value="${template.minStudentCount }">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">可容纳学员数量 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input  name="maxStudentCount" class="form-control" required  type="number" value="${template.maxStudentCount }">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">有效开始日期 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input  name="effectiveStart" class="form-control" required disabled="disabled" type="date" value=${template.effectiveStart}>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">有效结束日期 <span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input  name="effectiveEnd" class="form-control" required  type="date" value=${template.effectiveEnd }>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 可使用范围 (子部门可用)<span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="usableDepartment.id" class="form-control" disabled required>
						<option value="${template.usableDepartment.id }">${template.usableDepartment.name }</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12"> 教材提供者<span class="required">*</span></label>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<select name="provider.id" class="form-control" disabled required>
						<option value="${template.provider.id }">${template.provider.name }</option>
					</select>
				</div>
			</div>

			<div class="ln_solid"></div>
			<div class="form-group">
				
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
					<button type="reset"  class="btn btn-primary">重置</button>
					<button type="submit"  class="btn btn-success">修改</button>
				</div>
			</div>

		</form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			 $('#form-update')
			 	.bootstrapValidator({
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
			        	},
			        	times:{
			        		validators:{
			        			notEmpty:{}
			        		}
			        	},
			        	minStudentCount:{
			        		validators:{
			        			greaterThan:{
			        				value:4
			        			}
			        		}
			        	}
			        
			        }
			        
	/* 		         submitHandler: function(validator, form, submitButton){
			        	 $.ajax({
			        		 url:form.attr("action"),
			        		 type:form.attr("method"),
			        		 data:form.serialize(),
			        		 success:function(data){
			        			 $('.modal-create').modal('hide');
			        			 TABLE.draw(false);
			        		 }
			        	 });
			        } 
	 */			})
	 			.on('success.form.bv', function(e) {
	 	            // Prevent form submission
	 	            e.preventDefault();
	
	 	            // Get the form instance
	 	            var $form = $(e.target);

	 	            // Get the BootstrapValidator instance
	 	            var bv = $form.data('bootstrapValidator');
	
	 	            // Use Ajax to submit form data
	 	            $.ajax({
	 	            	url:$form.attr('action'),
	 	            	type:$form.attr('method'),
	 	            	data:$form.serialize(),
	 	            	dataType:json,
	 	            	success:function(data){
	 	            		if(data.result){
	 	            			flashUpdateSuccessModal();
	 	            			TABLE.draw(false);
	 	            		}else{
	 	            			showUpdateFailureModal(data.msg);
	 	            		}
	 	            		
	 	            	}
	 	            });
	 	        });
				
			
			
			loadHtmlForSubCourseType($('#courseType').val());
			
		 	/*  $('#form-create').submit(function() {	
				if($(this).data('bootstrapValidator').validate().isValid()){
					$.ajax({
						url : $(this).attr("action"),
						type : $(this).attr("method"),
						data : $(this).serialize(),
						success : function(data) {
							$('.modal-create').modal('hide');
							TABLE.draw(false);
						}
					});
				}
			});  */ */
			
			$('#courseType').change(function(){
				loadHtmlForSubCourseType($(this).val());
			});
		});
		
		function loadHtmlForSubCourseType(v_courseType){
			var html;
			$.ajax({
				url:"./subCourseTypes?courseType=" + v_courseType,
				type:"GET",
				success:function(response){
					if(response.result){
						var types = response.data;
						for(var i=0; i<types.length;i++){
							html = html + '<option value="' +types[i] + '">' + translateSubCourseType(types[i]) + '</option>';
						}
						$('#subCourseType').html(html);
					}
				}
			});
		}
	</script>
</body>
</html>