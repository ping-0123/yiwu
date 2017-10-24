<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>post edition</title>

<!-- Bootstrap -->
</head>
<body>

	<!-- modal header -->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">×</span>
		</button>
		<h4 class="modal-title" id="myModalLabel">修改教师资料</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<ul class="nav nav-tabs">
			<li><a href="#header" data-toggle="tab">顶部图片</a></li>
			<li><a href="#certificate" data-toggle="tab">资质＆证书</a></li>
			<li><a href="#daily" data-toggle="tab">生活照</a></li>
			<li><a href="#dance" data-toggle="tab">舞蹈视频</a></li>
		</ul>

		<br /> <br />

		<div id="tab-content" class="tab-content">
			<div class="tab-pane fade in active" id="header">
				<form id="form-update-header" method="POST" action="./" class="form-horizontal form-label-left">
					<input type="hidden" name="id" value="${headerMedia.id }">
					 <input type="hidden" name="coach.id" value="${headerMedia.coach.id }">
					<input type="hidden" name="type" value="IMAGE"> 
					<input type="hidden" name="tag" value="HEADER">
					<input type="hidden" name="coverage" value="true">

					<div class="form-group">
						<input type="hidden" name="uri" id="headerUri" value=""> <label class="control-label col-md-3 col-sm-3 col-xs-12">头像 </label>
						<div class="col-md-9 col-sm-9 col-xs-12 dropzone" id="dropzone-header">
							<div class="am-text-success dz-message">
								将图片拖拽到此处<br>或点此打开文件管理器选择图片
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">说明</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<textarea class="form-control" rows="2" name="text"> ${headerMedia.text } </textarea>
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

			<div class="tab-pane fade " id="certificate">this is certificate</div>
			<div class="tab-pane fade " id="daily">this is daily</div>
			<div class="tab-pane fade " id="dance">this is dance</div>
		</div>
	</div>
	<!-- /modal body -->

	<script type="text/javascript">
		$('#form-update').submit(function() {
			$.ajax({
				url : $(this).attr("action"),
				type : $(this).attr("method"),
				data : $(this).serialize(),
				success : function(data) {
					$('.modal-update').modal('hide');
					TABLE.draw(false);
				}
			});
			return false;
		});

		Dropzone.autoDiscover = false;
		var myDropzone = new Dropzone("#dropzone-header", {
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
				$("#headerUri").val(response.key);
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
				
				var mockFile = {name:"${headerMedia.uri}"};
				myDropzone.emit("addedfile", mockFile);
				myDropzone.emit("thumbnail", mockFile, "${headerMedia.uri}");
				myDropzone.emit("complete", mockFile);
			    
			    $(".dz-image img").css({
					"width":"100%",
				    "height":"100%" 
				});

			}
		});
		
		
		$('#form-update-header').submit(function(){
			$.ajax({
				url: $(this).attr("action"),
				type: $(this).attr("method"),
				data: $(this).serialize(),
				success:function(data){
					if(data.result)
						flashUpdateSuccessModal();
					else
						showUpdateFailureModal(data.msg);
				}
			});
			return false;
		});
	</script>
</body>
</html>