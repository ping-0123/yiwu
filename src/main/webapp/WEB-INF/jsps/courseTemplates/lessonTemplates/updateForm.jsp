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
		<!-- tab nav-->
		<ul class="nav nav-tabs">
			<li><a href="#connotation" data-toggle="tab">课时内容</a></li>
			<li><a href="#picture" data-toggle="tab">上传图片</a></li>
			<li><a href="#audio" data-toggle="tab">上传音乐</a></li>
			 <li class="dropdown">
		        <a href="#" id="videoDrop" class="dropdown-toggle"
		        	data-toggle="dropdown">上传视频
		            <b class="caret"></b>
		        </a>
		        <ul class="dropdown-menu" role="menu" aria-labelledby="videoDrop">
		            <li><a href="#standardVideo" data-toggle="tab">上传标准视频</a></li>
					<li><a href="#puzzleVideo" data-toggle="tab">上传疑难点视频</a></li>
					<li><a href="#practicalVideo" data-toggle="tab">上传实际视频</a></li>
		        </ul>
		    </li>
		</ul>
		<!-- end tab nav -->

		<br />

		<!-- tab content -->
		<div class="tab-content container">
			
			<!-- connotation tab pane  -->
			<div class="tab-pane fade in active" id="connotation">
				<form id="form-connotation" method="POST" action="../../../lessonTemplates/${template.id}" class="form-horizontal form-label-left">
					<input type="hidden" name="_method" value="PUT" />
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-2">简介</label>
						<div class="col-md-10 col-sm-10 col-xs-10">
							<textarea name="connotation.introduction" rows="2" class="form-control">${template.connotation.introduction }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-2">内容</label>
						<div class="col-md-10 col-sm-10 col-xs-10">
							<textarea class="form-control" name="connotation.connotation" rows="2">${template.connotation.connotation }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-2">帮助信息</label>
						<div class="col-md-10 col-sm-10 col-xs-10">
							<textarea class="form-control" name="connotation.helpInfomation" rows="2">${template.connotation.helpInfomation }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-2">舞蹈简介</label>
						<div class="col-md-10 col-sm-10 col-xs-10">
							<textarea class="form-control" name="connotation.danceIntroduction" rows="2">${template.connotation.danceIntroduction }</textarea>
						</div>
					</div>
				
					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-2">
							<button type="reset" class="btn btn-primary">重置</button>
							<button type="submit" class="btn btn-success">修改</button>
						</div>
					</div>
				</form>
			</div>
			<!-- end conotation tab pane -->

			<!-- picture tab pane  -->
			<div class="tab-pane fade" id="picture">
				<button id="btn-create-picture"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/pictureUri',updatePictureUriCallback)" class="btn btn-primary">上传</button>
				<button id="btn-delete-picture"  onclick="showDeleteModal('../../../lessonTemplates/${template.id}/connotation/pictureUri',deletePictureUriCallback)" class="btn btn-danger">删除</button>
				<div>
					<img id="pictureUrl" alt="" class="img-responsive" src="${template.connotation.pictureUrl }" width="200">
				</div>
			</div>
			<!-- end picture tab pane -->

			<!-- audio tab pane  -->
			<div class="tab-pane fade" id="audio">
				<div class="container">
					<div class="row">
						<button id="btn-create-audio"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/audioUri',updateAudioUriCallback)" class="btn btn-primary">上传</button>
						<button id="btn-delete-audio"  onclick="showDeleteModal('../../../lessonTemplates/${template.id}/connotation/audioUri',deleteAudioUriCallback)" class="btn btn-danger">删除</button>
					</div>
					
					<div class="row">
						<audio  id="audioUrl" alt="" class="img-responsive" src="${template.connotation.audioUrl }"  controls="controls" width="200">
					</div>
				</div>
			</div>
			<!-- end audio tab pane -->

			<!-- standerdVideo tab pane  -->
			<div class="tab-pane fade" id="standardVideo">
				<button id="btn-create-standardVideo"   class="btn btn-primary">上传视频</button>
				<!-- onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/standardVideoUri', updateStandardVideoCallback)" -->
				<button id="btn-create-standardVideoPoster"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/standardVideoPosterUri',updateStandardVideoPosterCallback)" class="btn btn-primary">上传视频封面</button>
				<button id="btn-delete-standardVideo"  onclick="showDeleteModal('../../../lessonTemplates/${template.id}/connotation/standardVideoUri',deleteStandardVideoCallback)" class="btn btn-danger">删除</button>
				
				<div id="row">
					<div id="container">
					</div>
					<div style="display:none" id="success" class="col-md-12">
		                <div class="alert-success">
		                    	队列全部文件处理完毕
		                </div>
	                </div>
					<div class="col-md-12 ">
	                    <table class="table table-striped table-hover text-left"   style="margin-top:40px;display:none">
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
				
				<div>
					<video id="standardVideoUrl" width="320" height="240" controls="controls" poster="${template.connotation.standardVideoPosterUrl }" src="${template.connotation.standardVideoUrl }">
					</video>
				</div>
			</div>
			<!-- end standerdVideo tab pane -->
			
			<!-- puzzleVideo tab pane  -->
			<div class="tab-pane fade" id="puzzleVideo">
				<button id="btn-create-puzzleVideo"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/puzzleVideoUri', updatepuzzleVideoCallback)" class="btn btn-primary">上传视频</button>
				<button id="btn-create-puzzleVideoPoster"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/puzzleVideoPosterUri',updatepuzzleVideoPosterCallback)" class="btn btn-primary">上传视频封面</button>
				<button id="btn-delete-puzzleVideo"  onclick="showDeleteModal('../../../lessonTemplates/${template.id}/connotation/puzzleVideoUri',deletepuzzleVideoCallback)" class="btn btn-danger">删除</button>
				<div>
					<video id="puzzleVideoUrl" width="320" height="240" controls="controls" poster="${template.connotation.puzzleVideoPosterUrl }" src="${template.connotation.puzzleVideoUrl }">
					</video>
				</div>
			</div>
			<!-- end puzzleVideo tab pane -->
			
			<!-- practicalVideo tab pane  -->
			<div class="tab-pane fade" id="practicalVideo">
				<button id="btn-create-practicalVideo"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/practicalVideoUri', updatepracticalVideoCallback)" class="btn btn-primary">上传视频</button>
				<button id="btn-create-practicalVideoPoster"  onclick="showQiniuDropzoneModal('${uploadToken}','../../../lessonTemplates/${template.id}/connotation/practicalVideoPosterUri',updatepracticalVideoPosterCallback)" class="btn btn-primary">上传视频封面</button>
				<button id="btn-delete-practicalVideo"  onclick="showDeleteModal('../../../lessonTemplates/${template.id}/connotation/practicalVideoUri',deletepracticalVideoCallback)" class="btn btn-danger">删除</button>
				<div>
					<video id="practicalVideoUrl" width="320" height="240" controls="controls" poster="${template.connotation.practicalVideoPosterUrl }" src="${template.connotation.practicalVideoUrl }">
					</video>
				</div>
			</div>
			<!-- end practicalVideo tab pane -->

		</div>
		<!--  end tab-content -->
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			
			//form submit
			$('#form-connotation').submit(function(){
				$.ajax({
					url: $(this).attr('action'),
						type: $(this).attr('method'),
						data: $(this).serialize(),
						dataType: 'json',
						success: function(data) {
							if(data.result) {
								flashUpdateSuccessModal();
							} else {
								showUpdateFailureModal(data.msg);
							}
						}
				});
				return false;
			});
			// end form submit
			
			// qiniu uploader
			var uploader = Qiniu.uploader({
				runtimes:'html5,flash,html4',
				browse_button: 'btn-create-standardVideo',
				uptoken_url:'../../../qiniu/uptoken',
				get_new_uptoken: false,
				domain:'http://oq3hegvvd.bkt.clouddn.com/',
				container:'upload-picture-container',
				max_file_size : '200mb',
				max_retries: 3,
				dragdrop: true,
				drop_element: 'dragdrop-picture-container',
				chunk_size: '4mb',
				auto_start: true,
				init:{
					'FilesAdded':function(up,files){
						plupload.each(files,function(file){
							
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
			//end qiniu uploader
			
			// control button visable
				//pictureUri
			<c:if test="${empty template.connotation.pictureUrl}" >
				$('#btn-delete-picture').css("display","none");
			</c:if>
			<c:if test="${not empty template.connotation.pictureUrl}" >
				$('#btn-create-picture').css("display","none");
			</c:if>
				//audioUri
			<c:if test="${empty template.connotation.audioUrl}" >
				$('#btn-delete-audio').css("display","none");
			</c:if>
			<c:if test="${not empty template.connotation.audioUrl}" >
				$('#btn-create-audio').css("display","none");
			</c:if>
				// standard video
			<c:if test="${empty template.connotation.standardVideoUrl}" >
				$('#btn-delete-standardVideo').css("display","none");
			</c:if>
			<c:if test="${not empty template.connotation.standardVideoUrl}" >
				$('#btn-create-standardVideo').css("display","none");
				$('#btn-create-standardVideoPoster').css("display", "none");
			</c:if>
				// puzzle video
			<c:if test="${empty template.connotation.puzzleVideoUrl}" >
				$('#btn-delete-puzzleVideo').css("display","none");
			</c:if>
			<c:if test="${not empty template.connotation.puzzleVideoUrl}" >
				$('#btn-create-puzzleVideo').css("display","none");
				$('#btn-create-puzzleVideoPoster').css("display", "none");
			</c:if>	
			// practical video
			<c:if test="${empty template.connotation.practicalVideoUrl}" >
				$('#btn-delete-practicalVideo').css("display","none");
			</c:if>
			<c:if test="${not empty template.connotation.practicalVideoUrl}" >
				$('#btn-create-practicalVideo').css("display","none");
				$('#btn-create-practicalVideoPoster').css("display", "none");
			</c:if>	
			// end control button visable
		});
		
		function updatePictureUriCallback(url){
			$('#pictureUrl').attr('src',url);
			$('#btn-create-picture').css("display","none");
			$('#btn-delete-picture').css("display","inline");
		}
		function deletePictureUriCallback(){
			$('#pictureUrl').attr('src','');
			$('#btn-create-picture').css("display","inline");
			$('#btn-delete-picture').css("display","none");
		}
		function updateAudioUriCallback(url){
			$('#audioUrl').attr('src', url);
			$('#btn-create-audio').css("display","none");
			$('#btn-delete-audio').css("display","inline");
		}
		function deleteAudioUriCallback(){
			$('#audioUrl').attr('src', '');
			$('#btn-create-audio').css("display","inline");
			$('#btn-delete-audio').css("display","none");
		}
			//standard video
		function updateStandardVideoCallback(url){
			$('#standardVideoUrl').attr('src', url);
			$('#btn-create-standardVideo').css("display","none");
			$('#btn-delete-standardVideo').css("display","inline");
		}
		function updateStandardVideoPosterCallback(url){
			$('#standardVideoUrl').attr('poster', url);
			$('#btn-create-standardVideoPoster').css("display","none");
		}
		function deleteStandardVideoCallback(){
			$('#standardVideoUrl').attr('poster','');
			$('#standardVideoUrl').attr('src','');
			$('#btn-create-standardVideo').css("display","inline");
			$('#btn-create-standardVideoPoster').css("display","inline");
			$('#btn-delete-standardVideo').css("display","none");
		}
			// puzzle video
		function updatepuzzleVideoCallback(url){
			$('#puzzleVideoUrl').attr('src', url);
			$('#btn-create-puzzleVideo').css("display","none");
			$('#btn-delete-puzzleVideo').css("display","inline");
		}
		function updatepuzzleVideoPosterCallback(url){
			$('#puzzleVideoUrl').attr('poster', url);
			$('#btn-create-puzzleVideoPoster').css("display","none");
		}
		function deletepuzzleVideoCallback(){
			$('#puzzleVideoUrl').attr('poster','');
			$('#puzzleVideoUrl').attr('src','');
			$('#btn-create-puzzleVideo').css("display","inline");
			$('#btn-create-puzzleVideoPoster').css("display","inline");
			$('#btn-delete-puzzleVideo').css("display","none");
		}
			// practical video
		function updatepracticalVideoCallback(url){
			$('#practicalVideoUrl').attr('src', url);
			$('#btn-create-practicalVideo').css("display","none");
			$('#btn-delete-practicalVideo').css("display","inline");
		}
		function updatepracticalVideoPosterCallback(url){
			$('#practicalVideoUrl').attr('poster', url);
			$('#btn-create-practicalVideoPoster').css("display","none");
		}
		function deletepracticalVideoCallback(){
			$('#practicalVideoUrl').attr('poster','');
			$('#practicalVideoUrl').attr('src','');
			$('#btn-create-practicalVideo').css("display","inline");
			$('#btn-create-practicalVideoPoster').css("display","inline");
			$('#btn-delete-practicalVideo').css("display","none");
		}
</script>
</body>
</html>