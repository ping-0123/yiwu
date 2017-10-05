<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title></title>
<link rel="stylesheet" href="../../assets/datatable-plugins/Bootstrap-3.3.7/css/bootstrap.min.css">
<link href="../../assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/css.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-treetable/stylesheets/jquery.treetable.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-treetable/stylesheets/jquery.treetable.theme.default.css">
<style>
#table th, #table td {
	font-size: 14px;
	padding: 8px;
}
li{float:left;list-style:none;}
</style>
</head>
<body>

	<div class="x_panel">
		<div class="x_title row">
			<div class="col-md-9 col-sm-9">
				<shiro:hasPermission name="resources:create:*">
					<button type="button" data-remote="./createForm" class="btn btn-primary" data-toggle="modal" data-target=".modal-create">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
					</button>
				</shiro:hasPermission>
			</div>
			
			<div class="col-md-3 col-sm-3">
				<ul class="nav navbar-right panel_toolbox">
					<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i></a></li>
					<li><a href=""> <i class="fa fa-refresh"></i></a></li>
					<li><a class="close-link"> <i class="fa fa-close"></i>
					</a></li>
				</ul>
			</div>

			<div class="clearfix"></br></div>
		</div>
		
		<div class="x_content">
		<table id="table">
			<thead>
				<tr>
					<th>名称</th>
					<th>类型</th>
					<th>URL路径</th>
					<th>权限字符串</th>
					<th>Bootstrap图标</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resources}" var="r">
					<tr data-tt-id='${r.id}' <c:if test="${not r.isRootNode()}">data-tt-parent-id='${r.parent.id}'</c:if>>
						<td>${r.name}</td>
						<td>${r.type}</td>
						<td>${r.url}</td>
						<td>${r.permission}</td>
						<td>${r.icon }</td>
						<td><shiro:hasPermission name="resources:create:*">
								<c:if test="${r.type ne 'BUTTON'}">
									<a href="./createForm?parentId=${r.id}" data-toggle="modal" data-target=".modal-create"> <i class="fa fa-plus" title="添加子节点"> </i></a>
								</c:if>
							</shiro:hasPermission> <shiro:hasPermission name="resources:update:*">
								<a href="${r.id }/updateForm" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"> </i></a>
							</shiro:hasPermission> 
							<shiro:hasPermission name="resources:delete:*">
								<a href="#" onclick="showDeleteModal(./${r.id})"> <i class="fa fa-trash" title="删除"></i></a>
							</shiro:hasPermission>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div> <!-- /end x-content -->

	</div> <!-- /end x-panel -->
	
		<!-- create modal -->
	<div class="modal fade bs-example-modal-lg modal-create" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- create modal -->

	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg modal-update" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /update modal -->

	<script src="${pageContext.request.contextPath}/backend/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/jquery-treetable/javascripts/src/jquery.treetable.js"></script>
	<script src="../../assets/datatable-plugins/Bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../../backend/js/custom.min.js"></script>
	<script src="../../backend/js/main.js"></script>
	<script>
		$(function() {
			$("#table").treetable({
				expandable : true
			}).treetable("expandNode", 1);
			
			$(".deleteBtn").click(function() {
				if (confirm("确认删除吗?")) {
					location.href = "${pageContext.request.contextPath}/resources/"
							+ $(this).data("id") + "/delete";
				}
			});
			
		});
	</script>
</body>
</html>