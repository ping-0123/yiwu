<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create post</title>

</head>
<body>

	<!-- modal body -->
	<div class="modal-body" style="min-height: 480px">
		<form id="form-create"  method="POST" action="./"  class="form-horizontal form-label-left">
			<input type="hidden" name="id" value="${media.id }">
			<input type="hidden" name="coach.id" value="${media.coach.id }">
			<input type="hidden" name="type" value="${media.type }"> 
			<input type="hidden" name="tag" value="${media.tag }">
			<input type="hidden" name="coverage" value="false">
				
			<div class="form-group ">
				<input type="hidden" name="uri" id="media-uri" value=""> 
				<div class="form-control dropzone" id="dropzone-media">
					<div class="am-text-success dz-message">
						将图片拖拽到此处<br>或点此打开文件管理器选择图片
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-3 col-sm-3 col-xs-6">
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
		var myDropzone = new Dropzone("#dropzone-media", {
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
	</script>
</body>
</html>