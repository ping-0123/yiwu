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
						<table id="yiwuDatatable" class="table table-bordered table-hover table-condensed" width="100%">

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
   <script type="text/javascript">
   		var column_index_create_time=0;
   		var setting = 
			{
				"processing" : false,
				"serverSide" : true,
				"select":true,
				"language" : {
					"url" : "../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : "输入模板名,舞种名"
				},
				"ajax" : {
					"url" : "./datatable?courseTempId=${courseTemplateId}" ,
					"type" : "POST"
				},
				"columns" : [{
					"title" :"课程模板Id",
					"data":"courseTemplateId"
				},{
					"data" : "ordinalNo",
					"title": "课时序号"
				},{
					"data":"id",
					"title":"操作",
					"render": function(data, type, row, meta) {
						var html =  '';
						<shiro:hasPermission name="courseTemplates:view:*">
							html = html + '<a href="' + row.id + '/updateForm" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"></i>  </a>';
						</shiro:hasPermission>
						
						return html;
					}
				} ]
			}; //end datatable setting
			
  </script>
  
  
</body>
</html>