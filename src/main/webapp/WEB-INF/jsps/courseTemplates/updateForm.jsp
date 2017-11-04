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
				<li>
					<a href="#basic" data-toggle="tab">基本信息</a>
				</li>
				<li>
					<a href="#connotation" data-toggle="tab">课程内容</a>
				</li>
				<li>
					<a href="#picture" data-toggle="tab">课程图片</a>
				</li>
				<li>
					<a href="#audio" data-toggle="tab">课程音乐</a>
				</li>
				<li>
					<a href="#video" data-toggle="tab">课程视频</a>
				</li>
			</ul>
			<!-- end tab nav -->

			<br />

			<!-- tab content -->
			<div class="tab-content container">
				<!-- basic tab pane -->
				<div class="tab-pane fade in active" id="basic">
					<form id="form-update" method="POST" action="./${template.id}" class="form-horizontal form-label-left">
						<input type="hidden" name="_method" value="PUT">
						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4"> 模板名 <span class="required">*</span></label>
							<div class="col-md-10 col-sm-10 col-xs-8">
								<input name="name" class="form-control" type="text" value="${template.name }">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4"> 舞种 *</label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<select name="dance.id" class="form-control" required>
									<option value="${template.danceId }">${template.danceName }</option>
								</select>
							</div>
							<label class="control-label col-md-2 col-sm-2 col-xs-4"> 舞种等级 <span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8	">
								<select name="danceGrade.id" class="form-control" required>
									<option value="${template.danceGradeId }">${template.danceGradeName }</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4">课程类型<span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<select name="courseType" id="courseType" class="form-control " required>
									<option value="${template.courseType}">${ping:getCourseTypeName(template.courseType)}</option>
								</select>
							</div>
							<label class="control-label col-md-2 col-sm-2 col-xs-4">中类<span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<select name="subCourseType" id="subCourseType" class="form-control col-md-3 col-sm-3 col-xs-6" required>
									<option value="${template.subCourseType }">${ping:getCourseTypeName(template.courseType) }</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4">课程总节数<span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<input name="times" value=${template.times } class="form-control" required type="number">
							</div>
							<label class="control-label col-md-2 col-sm-2 col-xs-8">每节课时长<span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<input name="hoursPerTime" value="${template.times }" class="form-control" placeholder="单位:小时" required type="text">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4">开课最小学员数量 <span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<input name="minStudentCount" value=${template.minStudentCount } class="form-control" required type="number">
							</div>
							<label class="control-label col-md-2 col-sm-2 col-xs-4">可容纳学员数量 <span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<input name="maxStudentCount" value=${template.maxStudentCount} class="form-control" required type="number">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4">有效开始日期 <span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<input name="effectiveStart" value=${template.effectiveStart } class="form-control" required type="date">
							</div>
							<label class="control-label col-md-2 col-sm-2 col-xs-12">有效结束日期 <span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<input name="effectiveEnd" value=${template.effectiveEnd } class="form-control" required type="date">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4"> 可使用范围 (子部门可用)<span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<select name="usableDepartment.id" class="form-control" required>
									<option value=${template.usableDepartmentId }>${template.usableDepartmentName }</option>
								</select>
							</div>
							<label class="control-label col-md-2 col-sm-2 col-xs-4"> 教材提供者<span class="required">*</span></label>
							<div class="col-md-4 col-sm-4 col-xs-8">
								<select name="provider.id" class="form-control" required>
									<option value="${template.providerId }">${template.providerName }</option>
								</select>
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
				<!-- end basic tab pane -->

				<!-- connotation tab pane  -->
				<div class="tab-pane fade" id="connotation">
					<form id="form-connotation" method="POST" action="./${template.id}" class="form-horizontal form-label-left">
						<input type="hidden" name="_method" value="PUT">
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
							<label class="control-label col-md-2 col-sm-2 col-xs-4">帮助信息</label>
							<div class="col-md-10 col-sm-10 col-xs-8">
								<textarea class="form-control" name="connotation.helpInfomation" rows="2">${template.connotation.helpInfomation }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-4 ">舞蹈简介</label>
							<div class="col-md-10 col-sm-10 col-xs-8">
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
				<div class="tab-pane fade " id="picture">
					<button id="btn-create-picture"  onclick="showQiniuDropzoneModal('${uploadToken}','${template.id}/connotation/pictureUri',updateShownPicture)" class="btn btn-primary">上传</button>
					<button id="btn-delete-picture"  onclick="showDeleteModal('${template.id}/connotation/pictureUri',deleteShownPictureCallback)" class="btn btn-danger">删除</button>
					<div>
						<img id="pictureUrl" alt="" class="img-responsive" src="${template.connotation.pictureUrl }" width="200">
					</div>
				</div>
				<!-- end picture tab pane -->

				<!-- audio tab pane  -->
				<div class="tab-pane fade" id="audio">
					<div class="container">
						<div class="row">
							<button id="btn-create-audio"  onclick="showQiniuDropzoneModal('${uploadToken}','${template.id}/connotation/audioUri',updateAudio)" class="btn btn-primary">上传</button>
							<button id="btn-delete-audio"  onclick="showDeleteModal('${template.id}/connotation/audioUri',deleteAudioCallback)" class="btn btn-danger">删除</button>
						</div>
						
						<div class="row">
							<audio  id="audioUrl" alt="" class="img-responsive" src="${template.connotation.audioUrl }"  controls="controls" width="200">
						</div>
					</div>
				</div>
				<!-- end audio tab pane -->

				<!-- video tab pane  -->
				<div class="tab-pane fade" id="video">
					<button id="btn-create-video"  onclick="showQiniuDropzoneModal('${uploadToken}','${template.id}/connotation/videoUri', updateVideo)" class="btn btn-primary">上传视频</button>
					<button id="btn-create-videoPoster"  onclick="showQiniuDropzoneModal('${uploadToken}','${template.id}/connotation/videoPosterUri',updateVideoPoster)" class="btn btn-primary">上传视频封面</button>
					<button id="btn-delete-video"  onclick="showDeleteModal('${template.id}/connotation/videoUri',deleteVideoCallback)" class="btn btn-danger">删除</button>
					<div>
						<video id="videoUrl" width="320" height="240" controls="controls" poster="${template.connotation.videoPosterUrl }" src="${template.connotation.videoUrl }">
						</video>
					</div>
				</div>
				<!-- end video tab pane -->
			</div>
			<!--  end tab-content -->
		</div>
		<!-- end modal body -->

		<script type="text/javascript">
			// start document ready
			$(document).ready(function() {
				
				//  update form bootstrap validator
				$('#form-update').bootstrapValidator({
					feedbackIcons: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
						name: {
							validators: {
								notEmpty: {},
								stringLength: {
									min: 2,
									max: 30
								}
							}
						},
						times: {
							validators: {
								notEmpty: {}
							}
						},
						minStudentCount: {
							validators: {
								greaterThan: {
									value: 4
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
						url: $form.attr('action'),
						type: $form.attr('method'),
						data: $form.serialize(),
						dataType: 'json',
						success: function(data) {
							if(data.result) {
								console.log('excuted in success form bv');
								flashUpdateSuccessModal();
							} else {
								showUpdateFailureModal(data.msg);
							}
						}
					});
				});
				// end update form bootstrap validator

				loadHtmlForSubCourseType($('#courseType').val());

				$('#courseType').change(function() {
					loadHtmlForSubCourseType($(this).val());
				});
			
				// start form-connotation submit
				$('#form-connotation').submit(function() {
					$.ajax({
						url: $(this).attr('action'),
						type: $(this).attr('method'),
						data: $(this).serialize(),
						dataType: 'json',
						success: function(data) {
							if(data.result) {
								console.log('executed in form connotation');
								flashUpdateSuccessModal();
							} else {
								showUpdateFailureModal(data.msg);
							}
						}
					});

					return false;
				});
				//end form-connotation submit
				
				// control button visable
					//picture 
				var btn_create_picture_visable = false;
				var btn_delete_picture_visable = false;
				<c:if test="${empty template.connotation.pictureUrl}">
					btn_create_picture_visable = true;
					btn_delete_picture_visable = false;
				</c:if>
				<c:if test="${not empty template.connotation.pictureUrl}">
					btn_create_picture_visable = false;
					btn_delete_picture_visable = true;
				</c:if>
				if(!btn_create_picture_visable){
					$('#btn-create-picture').css("display","none");
				}
				if(!btn_delete_picture_visable)
				{
					$('#btn-delete-picture').css("display","none");
				}
					//audio
				var btn_create_audio_visable=false;
				var btn_delete_audio_visable=false;
				<c:if test="${empty template.connotation.audioUrl}">
					btn_create_audio_visable=true;
					btn_delete_audio_visable=false;
				</c:if>
				<c:if test="${not empty template.connotation.audioUrl}">
					btn_create_audio_visable=false;
					btn_delete_audio_visable=true;
				</c:if>
				if(!btn_create_audio_visable){
					$('#btn-create-audio').css("display","none");
				}
				if(!btn_delete_audio_visable)
				{
					$('#btn-delete-audio').css("display","none");
				}
					//video
				<c:if test="${empty template.connotation.videoUrl}" >
					$('#btn-delete-video').css("display","none");
				</c:if>
				<c:if test="${not empty template.connotation.videoUrl}" >
					$('#btn-create-video').css("display","none");
					$('#btn-create-video-poster').css("display", "none");
				</c:if>
				// end control button visable

			});
			// end document ready

			//start function loadHtmlForSubCourseType
			function loadHtmlForSubCourseType(v_courseType) {
				var html;
				$.ajax({
					url: './subCourseTypes?courseType='+ v_courseType,
					type: "GET",
					success: function(response) {
						if(response.result) {
							var types = response.data;
							for(var i = 0; i < types.length; i++) {
								html = html + '<option value="' + types[i] + '">' +
									translateSubCourseType(types[i]) +
									'</option>';
							}
							$('#subCourseType').html(html);
						}
					}
				});
			}
			// end function loadHtmlForSubCourseType
			
			// callback functions
				//picture
			function updateShownPicture(url){
				$('#pictureUrl').attr('src',url);
				$('#btn-create-picture').css("display","none");
				$('#btn-delete-picture').css("display","inline");
			}
			function deleteShownPictureCallback(){
				$('#pictureUrl').attr('src','');
				$('#btn-create-picture').css("display","inline");
				$('#btn-delete-picture').css("display","none");
			}
				//audio
			function updateAudio(url){
				$('#audioUrl').attr('src',url);
				$('#btn-create-audio').css("display","none");
				$('#btn-delete-audio').css("display","inline");
			}
			function deleteAudioCallback(){
				$('#audioUrl').attr('src','');
				$('#btn-create-audio').css("display","inline");
				$('#btn-delete-audio').css("display","none");
			}
				//video
			function updateVideo(url){
				$('#videoUrl').attr('src', url);
				$('#btn-create-video').css("display","none");
				$('#btn-delete-video').css("display","inline");
			}
			function updateVideoPoster(url){
				$('#videoUrl').attr('poster', url);
				$('#btn-create-videoPoster').css("display","none");
			}
			function deleteVideoCallback(){
				$('#videoUrl').attr('poster','');
				$('#videoUrl').attr('src','');
				$('#btn-create-video').css("display","inline");
				$('#btn-create-videoPoster').css("display","inline");
				$('#btn-delete-video').css("display","none");
			}
			//end callbacks
		</script>
	</body>

</html>