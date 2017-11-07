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

<title>标准课时模板</title>

<!--css stylesheet -->
<link href="../../../../assets/font-awe	some/css/font-awesome.min.css" rel="stylesheet">
<link href="../../../../assets/datatables/datatables.min.css" rel="stylesheet" > 
<link href="../../../../assets/datatable-plugins/Select-1.2.3/css/select.bootstrap.min.css" rel="stylesheet">
<link href="../../../../assets/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet" >
<link href="../../../../assets/bootstrap-validator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="../../../../assets/dropzone/min/dropzone.min.css" rel="stylesheet">
<link href="../../../../backend/css/custom.min.css" rel="stylesheet">
<link href="../../../../backend/css/main.css" rel="stylesheet">
<style>
.dataTables_filter{width:100%!important;}
</style>
<!-- end css stylesheet -->

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
						<table id="lessonTemplate-datatable" class="table table-bordered table-hover table-condensed" width="100%">

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->

	<!-- bootstrap modals -->
	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg modal-update-lessonTemplate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /update modal -->
	

	<!-- / bootstrap modals -->
    
	<!-- jQuery -->
	<!-- jquery, bootstrap datatable -->
	<script src="../../../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="../../../../assets/datatable-plugins/Select-1.2.3/js/dataTables.select.min.js" type="text/javascript"></script>
	<script src="../../../../assets/bootstrap3-dialog/bootstrap-dialog.min.js" type="text/javascript"></script>
	<script src="../../../../assets/bootstrap-validator/js/bootstrapValidator.min.js" type="text/javascript"></script>
	<script src="../../../../assets/bootstrap-validator/js/language/zh_CN.js" type="text/javascript"></script>
	<script src="../../../../assets/dropzone/min/dropzone.min.js" type="text/javascript"></script>
	<script src="../../../../backend/js/plupload.full.min.js"></script>
	<script src="../../../../backend/js/qiniu.min.js"></script>
	<script src="../../../../backend/js/custom.min.js"></script>
	
   <script type="text/javascript">
   		var column_index_create_time=0;
   		$(document).ready(function(){
   			// lessonTemplate_datatable_setting 
	    	var lessonTemplate_datatable_setting = 
			{
				"processing" : false,
				"serverSide" : true,
				"select":true,
				"language" : {
					"url" : "../../../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : ""
				},
				"ajax" : {
					"url" : "./datatable",
					"type" : "POST"
				},
				"columns" : [{
					"title" :"课程模板Id",
					"data":"courseTemplateId",
					"visible":false
				},{
					"title":"课程模板",
					"data":"courseTemplateName"
				},{
					"data" : "ordinalNo",
					"title": "课时序号"
				},{
					"data":"id",
					"title":"操作",
					"render": function(data, type, row, meta) {
						var html =  '';
						<shiro:hasPermission name="courseTemplates:update:*">
							html = html + '<a href="' +  row.id + '/updateForm" data-toggle="modal" data-target=".modal-update-lessonTemplate"> <i class="fa fa-pencil" title="修改"></i>  </a>';
						</shiro:hasPermission>
						
						return html;
					}
				} ]
			}; //end lessonTemplate_datatable_setting setting
			
			var table = $("#lessonTemplate-datatable").DataTable(lessonTemplate_datatable_setting);
			
			$(".modal-update-lessonTemplate").on("hidden.bs.modal", function(){
				$("#lessonTemplate-datatable").DataTable().draw(false);
			});
   		});
			
  </script>
  
   <script src="../../../../backend/js/main.js"></script>
  
</body>
</html>
</html>