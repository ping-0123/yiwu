<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ping" uri="http://yinzhiwu.com/yiwu/tags/ping"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>教师资料</title>
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
		<div class="modal-body" style="min-height: 720px">
			<ul id="media-tab" class="nav nav-tabs">
				<li>
					<a href="#header" data-toggle="tab">顶部图片</a>
				</li>
				<li>
					<a href="#certificate" data-toggle="tab">资质＆证书</a>
				</li>
				<li>
					<a href="#daily" data-toggle="tab">生活照</a>
				</li>
				<li>
					<a href="#dance" data-toggle="tab">舞蹈视频</a>
				</li>
			</ul>

			<br /> <br />

			<div id="tab-content" class="tab-content container">
				<!-- header pane -->
				<div class="tab-pane fade in active" id="header">
					<c:if test="${empty headerMedia}">
						<shiro:hasPermission name="coachMedia:create:*">
							<button onclick="showDropzoneModel('${uploadToken}',uploadHeader)" id="btn-create-header" class="btn btn-primary">新增</button>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${not empty headerMedia }">
						<shiro:hasPermission name="coachMedia:delete:*">
							<button id="btn-delete-header" onclick="showDeleteModal(${headerMedia.id},afterDeleteHeaderImg)" class="btn btn-primary btn-danger">删除</button>
						</shiro:hasPermission>
					</c:if>

					<br /> <img alt="" class="img-responsive" src="${headerMedia.url }" width="200">
				</div>

				<!-- certificate tab pane -->
				<div class="tab-pane fade " id="certificate">

					<div>
						<shiro:hasPermission name="coachMedia:create:*">
							<button class="btn btn-primary" onclick="uploadCertificate()">新增</button>
						</shiro:hasPermission>
					</div>
					<div>
						<c:forEach items="${certificateMedias }" var="pic">
							<div class="container">
								<div class="row">
									<img alt="" class="img-responsive" src="${pic.url }" width="200">
								</div>
								<div class="row">
									<shiro:hasPermission name="coachMedia:delete:*">
										<button onclick="showDeleteModal('${pic.id}',refreshModal)" class="btn btn-xs btn-danger">删除</button>
									</shiro:hasPermission>
								</div>

								<br />
							</div>
						</c:forEach>
					</div>
				</div>
				<!--
				<div class="tab-pane fade " id="certificate">
					<div>
						<shiro:hasPermission name="coachMedia:create:*">
							<button onclick="showDeopzoneModel()" class="btn btn-primary">新增</button></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="coachMedia:delete:*">
							<button onclick="showDeleteModal(${headerMedia.id})" class="btn btn-primary">删除</button>
						</shiro:hasPermission>
					</div>
					<div id="carousel-certificate" class="carousel slide">
						
						<ol class="carousel-indicators">
							<c:forEach items="${certificateMedias}" var="media" varStatus="status">
								<c:if test="${status.index eq 0 }">
									<li data-target="#carousel-certificate" data-slide-to=${status.index} class="active"></li>
								</c:if>
								<c:if test="${status.index lt 0 }">
									<li data-target="#carousel-certificate" data-slide-to=${status.index}></li>
								</c:if>
							</c:forEach>
						</ol>
						<div class="carousel-inner">
							<c:forEach items="${certificateMedias}" var="media" varStatus="status">
								<c:if test="${status.index eq 0 }">
									<div class="item active">
										<img alt="" src="${media.url }">
									</div>
								</c:if>
								<c:if test="${status.index lt 0 }">
									<div class="item">
										<img alt="" src="${media.url }">
									</div>
								</c:if>
							</c:forEach>
						</div>

						<a class="carousel-control left" href="#carousel-certificate" data-slide="prev">&lsaquo;
						</a>
						<a class="carousel-control right" href="#carousel-certificate" data-slide="next">&rsaquo;
						</a>
					</div>
				</div>
				-->

				<!-- daily tab pane -->
				<div class="tab-pane fade " id="daily">
					<div>
						<shiro:hasPermission name="coachMedia:create:*">
							<button class="btn btn-primary" onclick="uploadDaily()">新增</button>
						</shiro:hasPermission>
					</div>
					<div>
						<c:forEach items="${dailyMedias }" var="pic">
							<div class="container">
								<div class="row">
									<img alt="" class="img-responsive" src="${pic.url }" width="200">
								</div>
								<div class="row">
									<shiro:hasPermission name="coachMedia:delete:*">
										<button onclick="deleteDaily('${pic.id}', 'daily')" class="btn btn-xs btn-danger">删除</button>
									</shiro:hasPermission>
								</div>

								<br />
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- dance tab pane -->
				<div class="tab-pane fade " id="dance">
					<div>
						<shiro:hasPermission name="coachMedia:create:*">
							<button onclick="showDropzoneModel('${uploadToken}',uploadDanceVideo)" class="btn btn-primary">新增</button>
						</shiro:hasPermission>
					</div>

					<div>
						<c:forEach items="${danceMedias }" var="video">
							<div class="container">
								<div class="row">
									<video width="320" height="240" controls="controls" poster="${video.videoPosterUrl }" src="${video.url }">
									</video>
								</div>
								<div class="row">
									<c:if test="${empty video.videoPosterUri }">
										<button onclick="uploadVideoPoster('${video.id}')" class="btn btn-xs btn-primary ">上传封面视频</button>
									</c:if>
									<shiro:hasPermission name="coachMedia:delete:*">
										<button onclick="showDeleteModal( '${video.id}',refreshModal)" class="btn btn-xs btn-danger">删除</button>
									</shiro:hasPermission>
								</div>

								<br />
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- end modal body -->

			<!-- create modal -->
			<div class="modal fade bs-example-modal-lg modal-create" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-md">
					<div class="modal-content"></div>
				</div>
			</div>
			<!-- end create modal -->

			<script type="text/javascript">
				$(document).ready(

				);

				/**
				 * 上传头部文件
				 * @param {Object} fileKey
				 */
				function uploadHeader(fileKey) {
					$.ajax({
						url: "./",
						data: {
							"coach.id": '${coachId}',
							"uri": fileKey,
							"type": "IMAGE",
							"tag": "HEADER"
						},
						type: "POST",
						success: function(data) {
							if(data.result) {
								flashUpdateSuccessModal();
								refreshModal();
							} else {
								showUpdateFailureModal(data.msg);
							}
						}
					});

				}

				/**
				 * 上传教师证书
				 */
				function uploadCertificate() {
					showDropzoneModel('${uploadToken}', function(fileKey) {
						$.ajax({
							type: "post",
							url: "./",
							async: true,
							data: {
								"coach.id": "${coachId}",
								"uri": fileKey,
								"type": "IMAGE",
								"tag": "CERTIFICATE"
							},
							success: function(data) {
								uploadSucces(data);
							}
						});
					});
				}

				/**
				 * 上传教师日常图片
				 */
				function uploadDaily() {
					showDropzoneModel('${uploadToken}', function(fileKey) {
						$.ajax({
							type: "post",
							url: "./",
							async: true,
							data: {
								"coach.id": "${coachId}",
								"uri": fileKey,
								"type": "IMAGE",
								"tag": "DAILY"
							},
							success: function(data) {
								uploadSucces(data);
							}
						});
					});
				}

				function deleteDaily(id, tab_id) {
					showDeleteModal(id, function() {
						refreshModal();
						$('#media-tab a[href="#daily"]').tab('show');
					});
				}

				/**
				 * 
				 * @param {Object} data
				 */
				function uploadSucces(data) {
					if(data.result) {
						flashUpdateSuccessModal();
						refreshModal();
					} else {
						showUpdateFailureModal(data.msg);
					}
				}

				/**
				 * 上传舞蹈视频
				 * @param {Object} fileKey
				 */
				function uploadDanceVideo(fileKey) {
					$.ajax({
						url: "./",
						type: "POST",
						data: {
							"coach.id": "${coachId}",
							"uri": fileKey,
							"type": "VIDEO",
							"tag": "DANCE"
						},
						success: function(data) {
							if(data.result) {
								flashUpdateSuccessModal();
								refreshModal();
							} else {
								showUpdateFailureModal(data.msg);
							}
						}
					});
				}

				/**
				 * 上传舞蹈视频封面图片
				 * @param {Object} id
				 */
				function uploadVideoPoster(id) {
					showDropzoneModel('${uploadToken}', function(fileKey) {
						$.ajax({
							type: "PUT",
							url: id + "/videoPosterUri",
							async: true,
							data: {
								"videoPosterUri": fileKey
							},
							success: function(data) {
								if(data.result) {
									flashUpdateSuccessModal();
									refreshModal();
								} else {
									showUpdateFailureModal(data.msg);
								}
							}
						});
					});
				}

				function afterDeleteHeaderImg() {
					refreshModal();
				}

				function refreshModal() {
					$(".modal-detail").removeData("bs.modal");
					$(".modal-detail").modal({
						remote: "details?coachId=${coachId}"
					});
					
				}
			</script>
	</body>

</html>