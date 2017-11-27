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
<link href="../../assets/font-awe	some/css/font-awesome.min.css" rel="stylesheet">

<!-- my datatable -->
<link href="../../assets/datatables/datatables.min.css" rel="stylesheet" > 
<link href="../../assets/datatable-plugins/Select-1.2.3/css/select.bootstrap.min.css" rel="stylesheet">
<!-- bootstrap dialog -->
<link href="../../assets/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet" >
	<!-- dropzone -->
<link href="../../assets/dropzone/min/dropzone.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="../../backend/css/custom.min.css" rel="stylesheet">
<!-- Yiwu Theme Style -->
<link href="../../backend/css/main.css" rel="stylesheet">

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
	<div class="modal fade bs-example-modal-lg modal-detail" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				
			</div>
		</div>
	</div>
	
	<!-- create modal -->

	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg modal-update" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /update modal -->
	

	<!-- / bootstrap modals -->
    
	<!-- jQuery -->
	<!-- jquery, bootstrap datatable -->
	<script src="../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Select-1.2.3/js/dataTables.select.min.js" type="text/javascript"></script>
	<script src="../../assets/bootstrap3-dialog/bootstrap-dialog.min.js" type="text/javascript"></script>
	<!-- dropzone -->
	<script src="../../assets/dropzone/min/dropzone.min.js" type="text/javascript"></script>
	<!-- validator -->
	<script src="../../assets/validator/validator.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="../../backend/js/custom.min.js"></script>
	
   
   <script type="text/javascript">
   		// start datatable setting
   		var column_index_create_time =0;
   		var setting = 
			{
				"processing" : false,
				"serverSide" : true,
				"select":true,
				"language" : {
					"url" : "../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : "会员卡号,呢称,姓名,手机号码"
				},
				"dom":"<'row'<'col-sm-4 col-md-4' l><'col-sm-8 col-md-8' f>>tip",
				"ajax" : {
					"url" : "./datatable",
					"type" : "POST"
				},
				"columns" : [{
					"data" : "memberCard",
					"title" : "会员卡号",
					"visible" : true
				},{
					"title": "呢称",
					"data" : "nickName",
					"name" : ""
				}, {
					"title" :"姓名",
					"data":"name"
				},{
					"data" : "phoneNo",
					"title": "手机号码"
				},{
					"data" : "gender",
					"title": "性别",
					"render":function(data, type, row, meta){
						return translateGender(data);
					}
				},{
					"data" : "birthday",
					"title": "生日"
				},{
					"data" : "superDistributerId",
					"title": "分销归属",
					"searchable":false,
					"render":function(data, type,row,meta){
						return row.superDistributerName;
					}
				},{
					"data" : "superDistributerMemberCard",
					"title": "分销归属人会员卡号"
				},{
					"data": "serverId",
					"title": "销售归属",
					"render": function(data, type, row, meta){
						return row.serverName;
					}
				},{
					"data":"id",
					"title":"操作",
					"render": function(data, type, row, meta) {
						var html =  '';
						<shiro:hasPermission name="distributers:update:*">
							html = html +  '<a href="' + row.id + '/updateForm" data-toggle="modal" data-target=".modal-update"> [修改]</a>';
						</shiro:hasPermission>	
						<shiro:hasPermission name="orders:view:*">
							html = html + '<a href="' + row.id + '/orders/list" > [查看订单]  </a>';
						</shiro:hasPermission>
						
						return html;
					}
				} ]
			}; //end datatable setting
			
  </script>
  
  <script src="../../backend/js/main.js"></script>
</body>
</html>