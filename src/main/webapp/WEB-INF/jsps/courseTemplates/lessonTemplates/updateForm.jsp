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
		<form id="form-update" method="POST" action="./${template.id}" class="form-horizontal form-label-left">
			<input type="hidden" name="_method" value="PUT">
			<!-- tab nav-->
			<ul class="nav nav-tabs">
				<li><a href="#connotation" data-toggle="tab">课程内容</a></li>
				<li><a href="#picture" data-toggle="tab">上传图片</a></li>
				<li><a href="#audio" data-toggle="tab">上传音乐</a></li>
				<li><a href="#video" data-toggle="tab">上传视频</a></li>
			</ul>
			<!-- end tab nav -->

			<br />

			<!-- tab content -->
			<div class="tab-content container">

				<!-- basic tab pane -->
				
				<!-- connotation tab pane  -->
				<div class="tab-pane fade" id="connotation">
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-4">课程简介</label>
						<div class="col-md-10 col-sm-10 col-xs-8">
							<textarea name="connotation.introduction" rows="2" class="form-control">${template.connotation.introduction }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-4">课程内容</label>
						<div class="col-md-10 col-sm-10 col-xs-8">
							<textarea class="form-control" name="connotation.connotation" rows="2">${template.connotation.connotation }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-4">课程内容</label>
						<div class="col-md-10 col-sm-10 col-xs-8">
							<textarea class="form-control" name="connotation.helpInfomation" rows="2">${template.connotation.helpInfomation }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-4">舞蹈简介</label>
						<div class="col-md-10 col-sm-10 col-xs-8">
							<textarea class="form-control" name="connotation.danceIntroduction" rows="2">${template.connotation.danceIntroduction }</textarea>
						</div>
					</div>
				</div>
				<!-- end conotation tab pane -->

				<!-- picture tab pane  -->
				<div class="tab-pane fade" id="picture">
					<div class="form-group ">
						<input type="hidden" name="connotation.pictureUri" id="picture-uri" value=${template.connotation.pictureUri }>
						<div class="form-control dropzone" id="dropzone-picture">
							<div class="am-text-success dz-message">
								上传图片<br>
								将图片拖拽到此处<br>或点此打开文件管理器选择图片
							</div>
						</div>
					</div>
				</div>
				<!-- end picture tab pane -->

				<!-- audio tab pane  -->
				<div class="tab-pane fade" id="audio">
					<div class="form-group ">
						<input type="hidden" name="connotation.audioName" id="audioName" value=${template.connotation.audioName }>
						<input type="hidden" name="connotation.audioUri" id="audioUri" value=${template.connotation.audioUri }>
						<div class="form-control dropzone" id="dropzone-audio">
							<div class="am-text-success dz-message">
								上传音频<br>
								将音频文件拖拽到此处<br>或点此打开文件管理器选择音频文件
							</div>
						</div>
					</div>
				</div>
				<!-- end audio tab pane -->

				<!-- video tab pane  -->
				<div class="tab-pane fade" id="video">
					<div class="form-group ">
						<input type="hidden" name="connotation.videoTitle" id="videoTitle" value=${template.connotation.videoTitle }>
						<input type="hidden" name="connotation.videoPosterUri" id="videoPosterUri" value=${template.connotation.videoPosterUri }>
						<div class="form-control dropzone col-md-6 col-sm-6 col-xs-12" id="dropzone-videoPoster">
							<div class="am-text-success dz-message">
								上传视频封面图片<br>
								将图片拖拽到此处M<br>或点此打开文件管理器选择图片
							</div>
						</div>
						<input type="hidden" name="connotation.videoUri" id="videoUri" value=${template.connotation.videoUri }>
						<div class="form-control dropzone col-md-6 col-sm-6 col-xs-12" id="dropzone-video">
							<div class="am-text-success dz-message">
								上传视频<br>
								将视频文件拖拽到此处M<br>或点此打开文件管理器选择视频文件
							</div>
						</div>
					</div>
				</div>
				<!-- end video tab pane -->



			</div>
			<!--  end tab-content -->

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12">
					<button type="reset" class="btn btn-primary">重置</button>
					<button type="submit"  class="btn btn-success">修改</button>
				</div>
			</div>


		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#form-update').bootstrapValidator({
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					name : {
						validators : {
							notEmpty : {},
							stringLength : {
								min : 2,
								max : 30
							}
						}
					},
					times : {
						validators : {
							notEmpty : {}
						}
					},
					minStudentCount : {
						validators : {
							greaterThan : {
								value : 4
							}
						}
					}

				}

			}).on('success.form.bv', function(e) {
				// Prevent form submission
				e.preventDefault();

				// Get the form instance
				var $form = $(e.target);

				// Get the BootstrapValidator instance
				// var bv = $form.data('bootstrapValidator'); 

				// Use Ajax to submit form data
				$.ajax({
					url : $form.attr('action'),
					type : $form.attr('method'),
					data : $form.serialize(),
					dataType : 'json',
					success : function(data) {
						if (data.result) {
							flashUpdateSuccessModal();
							TABLE.draw(false);
						} else {
							showUpdateFailureModal(data.msg);
						}
					}
				});
			});

			loadHtmlForSubCourseType($('#courseType').val());

			$('#courseType').change(function() {
				loadHtmlForSubCourseType($(this).val());
			});

			// upload picture dropzone
			Dropzone.autoDiscover = false;
			var pictureDropzone = new Dropzone('#dropzone-picture', {
				url : QINIU_UPLOAD_URL,
				method : "POST",
				params : {
					"token" : "${uploadToken}"
				},
				addRemoveLinks : true,
				dictRemoveLinks : "x",
				dictRemoveFile : "删除",
				dictMaxFilesExceeded : "已超出允许的最大上传文件数量",
				maxFiles : 1,
				filesizeBase : 1024,
				success : function(file, response, e) {
					$("#picture-uri").val(response.key);
				},
				init : function() {
					this.on("removedfile", function(file) {
						console.log(file.name);
						$.ajax({
							type : "delete",
							url : "../qiniu/" + $('#picture-uri').val(),
							async : true,
							success : function(data) {
								console.log("删除成功");
							}
						});
					});
				}
			});
			
			//upload audio dropzone
			var audioDropzone = new Dropzone('#dropzone-audio', {
				url : QINIU_UPLOAD_URL,
				method : "POST",
				params : {
					"token" : "${uploadToken}"
				},
				addRemoveLinks : true,
				dictRemoveLinks : "x",
				dictRemoveFile : "删除",
				dictMaxFilesExceeded : "已超出允许的最大上传文件数量",
				maxFiles : 1,
				filesizeBase : 1024,
				success : function(file, response, e) {
					$("#audioUri").val(response.key);
					$("#audioName").val(file.name);
				},
				init : function() {
					this.on("removedfile", function(file) {
						console.log(file.name);
						$.ajax({
							type : "delete",
							url : "../qiniu/" + $('#audioUri').val(),
							async : true,
							success : function(data) {
								console.log("删除成功");
							}
						});
					});
				}
			});
			
			//upload video poster dropzone
			var videoPosterDropzone = new Dropzone('#dropzone-videoPoster', {
				url : QINIU_UPLOAD_URL,
				method : "POST",
				params : {
					"token" : "${uploadToken}"
				},
				addRemoveLinks : true,
				dictRemoveLinks : "x",
				dictRemoveFile : "删除",
				dictMaxFilesExceeded : "已超出允许的最大上传文件数量",
				maxFiles : 1,
				filesizeBase : 1024,
				success : function(file, response, e) {
					$("#videoPosterUri").val(response.key);
				},
				init : function() {
					this.on("removedfile", function(file) {
						console.log(file.name);
						$.ajax({
							type : "delete",
							url : "../qiniu/" + $('#videoPosterUri').val(),
							async : true,
							success : function(data) {
								console.log("删除成功");
							}
						});
					});
				}
			});
			
			
			//upload video dropzone
			var videoDropzone = new Dropzone('#dropzone-video', {
				url : QINIU_UPLOAD_URL,
				method : "POST",
				params : {
					"token" : "${uploadToken}"
				},
				addRemoveLinks : true,
				dictRemoveLinks : "x",
				dictRemoveFile : "删除",
				dictMaxFilesExceeded : "已超出允许的最大上传文件数量",
				maxFiles : 1,
				filesizeBase : 1024,
				success : function(file, response, e) {
					$("#videoUri").val(response.key);
					$("#videoTitle").val(file.name);
				},
				init : function() {
					this.on("removedfile", function(file) {
						console.log(file.name);
						$.ajax({
							type : "delete",
							url : "../qiniu/" + $('#videoUri').val(),
							async : true,
							success : function(data) {
								console.log("删除成功");
							}
						});
					});
				}
			});

		});

		function loadHtmlForSubCourseType(v_courseType) {
			var html;
			$.ajax({
				url : "./subCourseTypes?courseType=" + v_courseType,
				type : "GET",
				success : function(response) {
					if (response.result) {
						var types = response.data;
						for (var i = 0; i < types.length; i++) {
							html = html + '<option value="' +types[i] + '">'
									+ translateSubCourseType(types[i])
									+ '</option>';
						}
						$('#subCourseType').html(html);
					}
				}
			});
		}
	</script>
</body>
</html>