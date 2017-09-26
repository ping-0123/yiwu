<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ping" uri="http://yinzhiwu.com/yiwu/tags/ping"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>DataTables | Gentelella</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/backend/vendors/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/backend/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- my datatable -->
<link href="${pageContext.request.contextPath}/backend/css/datatables.min.css" rel="stylesheet" >
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/backend/css/custom.min.css" rel="stylesheet">
<!-- Yiwu Theme Style -->
<link href="${pageContext.request.contextPath}/backend/css/main.css" rel="stylesheet">

</head>

<body class="">

	<!-- page content -->
	<div class="">

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<!-- data-remote="${pageContext.request.contextPath}/system/posts/edit" -->
						<shiro:hasPermission name="posts:create:*">
							<button type="button" data-remote="form" class="btn btn-primary" data-toggle="modal" data-target=".create-form">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
							</button>
						</shiro:hasPermission>

						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i></a></li>
							<li><a href=""> <i class="fa fa-refresh"></i></a></li>
							<li><a class="close-link"> <i class="fa fa-close"></i>
							</a></li>
						</ul>

						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="yiwuDatatable" class="table table-bordered table-hover table-condensed" style=" overflow:outo">
							<thead>
								<tr>
									<th>职位</th>
									<th>描述</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>

							<%-- <tbody>
								<c:forEach items="${posts}" var="p">
									<tr class="data-row" >
										<td>${p.name}</td>
										<td>${p.description}</td>
										<td>${ping:getDataStatusName(p.dataStatus) }</td>
										<td><shiro:hasPermission name="posts:update">
												<a href="${p.id}/form" data-toggle="modal" data-target=".edit-form"> <i class="fa fa-pencil" title="修改"></i></a>
												<a href="${p.id}/form" data-toggle="modal" data-target=".edit-form"> <i class="fa fa-navicon" title="设置岗位职责"></i></a>

											</shiro:hasPermission> <shiro:hasPermission name="posts:delete">
												<a href="#" onclick="doDelete(${p.id})"><small><i class="fa fa-trash" title="删除"> </i> </small> </a>
											</shiro:hasPermission></td>
									</tr>
								</c:forEach>
							</tbody> --%>
						</table>
					</div>
				</div>
			</div>



		</div>
	</div>
	<!-- /page content -->

	<!-- bootstrap modals -->
	<!-- create modal -->
	<div class="modal fade bs-example-modal-lg create-form" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	
	<!-- create modal -->

	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg edit-form" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /update modal -->
	

	<!-- delete modal -->
	<div class="modal fade bs-example-modal-sm delete-promt" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">提示</h4>
				</div>
				<div class="modal-body">
					<p>删除之后将不能恢复， 确认要删除吗?</p>
				</div>
				<div class="modal-footer">
					<button  type="button" class="btn btn-success delete-confirm" data-dismiss="modal">确认</button>
					<button  type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>

			</div>
		</div>


	</div>
	<!-- / delete Modal -->
	<!-- / bootstrap modals -->
    
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/backend/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/backend/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- validator -->
	<script src="${pageContext.request.contextPath}/backend/vendors/validator/validator.js"></script>

	<script src="${pageContext.request.contextPath}/backend/js/datatables.min.js" type="text/javascript"></script>
	<!-- Custom Theme Scripts -->
	<script src="${pageContext.request.contextPath}/backend/js/custom.min.js"></script>
	<!-- Yiwu Theme Scripts -->
	<script src="${pageContext.request.contextPath}/backend/js/main.js"></script>
   
   <script type="text/javascript">
		$(document).ready(function() {
			$('#yiwuDatatable').DataTable({
				"processing" : false,
				"serverSide" : true,
				"ajax" : {
					"url" : "http://localhost:9090/yiwu/system/posts/table",
					"type" : "POST"
				},
				"columns" : [ {
					"data" : "id"
				}, {
					"data" : "name"
				}, {
					"data" : "dataStatus",
					"render" : function(data, type, row, meta) {
						return translateDataStatus(data);
					}
				},{
					"data":"id",
					"render": function(data, type, row, meta) {
						return '<a href="https://www.baidu.com">aa</a>';
					}
				} ]
			});
		});
		
	
			
		
		
  </script>
</body>
</html>