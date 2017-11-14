<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>

	<body>

		<div class="row">
			<div class="col-md-12">
				<div id="container">
					<a class="btn btn-default btn-lg " id="pickfiles" href="#">
						<i class="glyphicon glyphicon-plus"></i>
						<span>选择文件</span>
					</a>
				</div>
				<div style="display:none" id="success" class="col-md-12">
					<div class="alert-success">
						队列全部文件处理完毕
					</div>
				</div>

				<div class="col-md-12 ">
					<table class="table table-striped table-hover text-left" style="margin-top:40px;display:none">
						<thead>
							<tr>
								<th class="col-md-4">Filename</th>
								<th class="col-md-2">Size</th>
								<th class="col-md-6">Detail</th>
							</tr>
						</thead>
						<tbody id="fsUploadProgress">
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
		
		<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../js/qiniu.min.js"></script>
		<script type="text/javascript" src="../js/plupload/moxie.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				var uploader = Qiniu.uploader({
					runtimes:'html5,flash,html4',
					browse_button: 'pickfiles',
					uptoken_url:'../../system/qiniu/uptoken',
					get_new_uptoken: false,
					domain:'http://oq3hegvvd.bkt.clouddn.com/',
					container:'container',
					max_file_size : '200mb',
					max_retries: 3,
					dragdrop: true,
					drop_element: 'container',
					chunk_size: '4mb',
					auto_start: true,
					init:{
						'BeforeChunkUpload':function(up,file){
							console.log("before chunk upload:", file.name);
						},
						'FilesAdded':function(up,files){
							$('table').show();
							$('#success').hide();
							plupload.each(files,function(file){
								console.log("added file name is " + file.name);
								var progress=new FileProgress(file, 'fsUploadProgress');
								progress.setStatus("等待.....");
								progress.bindUploadCancel(up);
							});
						},
						'BeforeUpload':function(up, file){
							
						},
						'UploadProgress':function(up, file){
							
						},
						'FileUploaded':function(up, file, info){
							
						},
						'Error':function(up, err, errTip){
							
						},
						'UploadComplete':function(){
							
						},
						'Key':function(up, file){
							
						}
					}
				});
			});
			
		</script>
	</body>

</html>