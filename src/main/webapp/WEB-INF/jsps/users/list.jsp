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

<title>岗位设置</title>

<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/backend/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- my datatable -->
<link href="${pageContext.request.contextPath}/assets/datatables/datatables.min.css" rel="stylesheet" > 
<!-- bootstrap dialog -->
<link href="${pageContext.request.contextPath}/backend/css/bootstrap-dialog.min.css" rel="stylesheet" >
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/backend/css/custom.min.css" rel="stylesheet">
<!-- Yiwu Theme Style -->
<link href="${pageContext.request.contextPath}/backend/css/main.css" rel="stylesheet">

<style>
.dataTables_filter{width:100%!important;}
</style>

</head>

<body class="">

	<!-- page content -->
	<div class="">

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<shiro:hasPermission name="posts:create:*">
							<button type="button" data-remote="form" class="btn btn-primary" data-toggle="modal" data-target=".modal-create">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
							</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="posts:update:*">
							<input type="hidden" id="updatePermission" value="true" />
						</shiro:hasPermission>
						<shiro:hasPermission name="posts:delete:*">
							<input type="hidden" id="deletePermission" value="true" />
						</shiro:hasPermission>
						
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i></a></li>
							<li><a href=""> <i class="fa fa-refresh"></i></a></li>
							<li><a class="close-link"> <i class="fa fa-close"></i>
							</a></li>
						</ul>

						<div class="clearfix"></div>
					</div>
					<div class="x_content table-responsive">
						<table id="yiwuDatatable" class="table table-bordered table-hover table-condensed" width="100%">

						</table>
					</div>
				</div>
			</div>



		</div>
	</div>
	<!-- /page content -->

	<!-- bootstrap modals -->
	<!-- create modal -->
	<div class="modal fade bs-example-modal-lg modal-create" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	
	<!-- /end create modal -->

	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg modal-update" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /end update modal -->
	
	<!-- start setting modal -->
	<div class="modal fade bs-example-modal-lg modal-setting" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<button class="btn btn-primary btn-sm" onclick="saveUserRoles()"><small>保存</small></button>
				<ul id="tree" class="ztree"></ul>
			</div>
		</div>
	</div>
	<!-- /end setting modal -->
	
	<!-- /end bootstrap modals -->
    
	<!-- jQuery -->
	<script src="../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<!-- datatable -->	
	<script src="${pageContext.request.contextPath}/backend/js/bootstrap-dialog.min.js" type="text/javascript"></script>

	<!-- validator -->
	<script src="${pageContext.request.contextPath}/backend/vendors/validator/validator.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="${pageContext.request.contextPath}/backend/js/custom.min.js"></script>
	
   
   <script type="text/javascript">
   		var column_index_create_time =0;
   		var setting = 
			{
				"processing" : false,
				"serverSide" : true,
				"language" : {
					"url" : "../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : "输入用户名"
				},
				"ajax" : {
					"url" : "http://localhost:9090/yiwu/system/users/datatable",
					"type" : "POST"
				},
				"columns" : [{
					"data" : "createDate",
					"visible" : false
				}, {
					"data" : "username",
					"title": "用户名"
				}, {
					"data" :"employee",
					"title":"员工姓名",
					"render":function(data,type, row, meta){
						if(data==null)
							return "";
						return data.name;
					}
				},{
					"data" : "dataStatus",
					"title": "状态",
					"render" : function(data, type, row, meta) {
						return translateDataStatus(data);
					}
				},{
					"data":"createDate",
					"title":"操作",
					"render": function(data, type, row, meta) {
						if(row.username=="Admin")
							return "";
						var html =  '';
						if($('#updatePermission').val()){
							html = html + '<a href="' + row.id + '/form" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"></i></a>';
							html = html +  '<a href="#" data-toggle="modal" data-target=".modal-setting"> <i class="fa fa-navicon" title="设置用户角色"></i></a>';
						}
						if($('#deletePermission').val()){
							html = html  + '<a href="#" onclick="showDeleteModal(' + row.id + ')"> <small> <i class="fa fa-trash" title="删除"> </i> </small> </a>';
						}
						return html;
					}
				} ]
			}; //end setting
			
  </script>
  
  <script src="${pageContext.request.contextPath}/backend/js/main.js"></script>
</body>
</html>