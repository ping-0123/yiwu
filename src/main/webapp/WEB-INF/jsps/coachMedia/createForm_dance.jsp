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
		<h4 class="modal-title" >新增岗位</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-create"  method="POST" action="./"  class="form-horizontal form-label-left">
			<input type="hidden" name="id" value="${media.id }">
			<input type="hidden" name="coach.id" value="${media.coach.id }">
			<input type="hidden" name="type" value="${media.type }"> 
			<input type="hidden" name="tag" value="${media.tag }">
			<input type="hidden" name="coverage" value="false">
			
			<div class="form-group">
				<input type="hidden" name="videoPosterUri" id="media-poster-uri" value=""> 
				<label class="control-label col-md-3 col-sm-3 col-xs-12">上传视频封面图片</label>
				<div class="col-md-9 col-sm-9 col-xs-12 dropzone" id="dropzone-poster-uri">
					<div class="am-text-success dz-message">
						将图片拖拽到此处<br>或点此打开文件管理器选择图片
					</div>
				</div>
			</div>
				
			<div class="form-group">
				<input type="hidden" name="uri" id="media-uri" value=""> 
				<label class="control-label col-md-3 col-sm-3 col-xs-12">上传视频</label>
				<div class="col-md-9 col-sm-9 col-xs-12 dropzone" id="dropzone-uri">
					<div class="am-text-success dz-message">
						将图片拖拽到此处<br>或点此打开文件管理器选择图片
					</div>
				</div>
			</div>

			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
					<button type="submit" class="btn btn-success">保存</button>
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
					$('.modal-create').modal('hide');
				}
			});
			return false;
		});
		
		Dropzone.autoDiscover = false;
		var myDropzone = new Dropzone("#dropzone-uri", {
			url : QINIU_UPLOAD_URL,
			method : "POST",
			params : {
				"token" : "${uploadToken}"
			},
			addRemoveLinks : true,
			dictRemoveLinks : "x",
			dictRemoveFile : "移除",
			dictMaxFilesExceeded : "",
			maxFiles : 1,
			filesizeBase : 1024,

			sending : function(file, xhr, formData) {
				formData.append("filesize", file.size);
			},
			success : function(file, response, e) {
				$("#media-uri").val(response.key);
			},
			init : function() {
				this.on("removedfile", function(file) {
					console.log(file.name);
					$.ajax({
						type : "delete",
						url : "../qiniu/" + file.name,
						async : true,
						success : function(data) {
							console.log("删除成功");
						}
					});
				});
			}
		});
		
		var dropzonePosterUri = new Dropzone("#dropzone-poster-uri", {
			url : QINIU_UPLOAD_URL,
			method : "POST",
			params : {
				"token" : "${uploadToken}"
			},
			addRemoveLinks : true,
			dictRemoveLinks : "x",
			dictRemoveFile : "移除",
			dictMaxFilesExceeded : "",
			maxFiles : 1,
			filesizeBase : 1024,

			sending : function(file, xhr, formData) {
				formData.append("filesize", file.size);
			},
			success : function(file, response, e) {
				$("#media-poster-uri").val(response.key);
			},
			init : function() {
				this.on("removedfile", function(file) {
					console.log(file.name);
					$.ajax({
						type : "delete",
						url : "../qiniu/" + file.name,
						async : true,
						success : function(data) {
							console.log("删除成功");
						}
					});
				});
			}
		});
	</script>
</body>
</html>