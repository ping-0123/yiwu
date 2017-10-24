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
	<div class="modal-body" style="min-height: 400px">
		<ul class="nav nav-tabs">
			<li><a href="#header" data-toggle="tab">顶部图片</a></li>
			<li><a href="#certificate" data-toggle="tab">资质＆证书</a></li>
			<li><a href="#daily" data-toggle="tab">生活照</a></li>
			<li><a href="#dance" data-toggle="tab">舞蹈视频</a></li>
		</ul>

		<br /> <br />

		<div id="tab-content" class="tab-content container">
			<!-- header pane -->
			<div class="tab-pane fade in active" id="header">
				<c:if test="${empty headerMedia}">
					<shiro:hasPermission name="coachMedia:create:*">
						<a  href="./createForm?coachId=${coachId }&tag=HEADER" data-toggle="modal"  data-target=".modal-create"><button class="btn btn-primary">新增</button></a>
					</shiro:hasPermission>
				</c:if>
				<c:if test="${not empty headerMedia }">
					<shiro:hasPermission name="coachMedia:delete:*">
						<button onclick="showDeleteModal(${headerMedia.id})" class="btn btn-primary">删除</button>
					</shiro:hasPermission>
				</c:if>

				<br /> <img alt="" class="img-responsive" src="${headerMedia.url }">
			</div>

			<!-- certificate tab pane -->
			<div class="tab-pane fade " id="certificate">
				<div id="carousel-certificate" class="carousel slide">
					<ol class="carousel-indicators">
						<c:forEach items="${certificateMedias}" var="media" varStatus="status">
							<c:if test="${status.index eq 0 }">
								<li data-target="#carousel-certificate"  data-slide-to=${status.index} class="active"></li>
							</c:if>
							<c:if test="${status.index lt 0 }">
								<li data-target="#carousel-certificate"  data-slide-to=${status.index}></li>
							</c:if>
						</c:forEach>
					</ol>
					<div class="carousel-inner">
						<c:forEach items="${certificateMedias}" var="media" varStatus="status">
							<c:if test="${status.index eq 0 }">
								<div class="item active">
									<img alt=""  src="${media.url }">
								</div>
							</c:if>
							<c:if test="${status.index lt 0 }">
								<div class="item">
									<img alt=""  src="${media.url }">
								</div>
							</c:if>
						</c:forEach>
					</div>
					
					<a class="carousel-control left" href="#carousel-certificate" 
				        data-slide="prev">&lsaquo;
				    </a>
				    <a class="carousel-control right" href="#carousel-certificate" 
				        data-slide="next">&rsaquo;
				    </a>
				</div>
			</div>
			<!-- daily tab pane -->
			<div class="tab-pane fade " id="daily">this is daily</div>
			<!-- dance tab pane -->
			<div class="tab-pane fade " id="dance">this is dance</div>
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
		
		
		$(".modal-create").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});
		
		function refreshModal(){
			$(".modal-detail").modal({
				remote:"details?coachId=${coachId}"
			});
		}
		
/*  		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e){
			$('#carousel-certificate').carousel('cycle');
		});  */
	</script>
</body>
</html>