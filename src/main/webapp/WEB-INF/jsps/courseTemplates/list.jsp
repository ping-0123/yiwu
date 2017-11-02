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

<!--css stylesheet -->
<link href="../../assets/font-awe	some/css/font-awesome.min.css" rel="stylesheet">
<link href="../../assets/datatables/datatables.min.css" rel="stylesheet" > 
<link href="../../assets/datatable-plugins/Select-1.2.3/css/select.bootstrap.min.css" rel="stylesheet">
<link href="../../assets/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet" >
<link href="../../assets/bootstrap-validator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="../../assets/dropzone/min/dropzone.min.css" rel="stylesheet">
<link href="../../backend/css/custom.min.css" rel="stylesheet">
<link href="../../backend/css/main.css" rel="stylesheet">
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
						<shiro:hasPermission name="courseTemplates:create:*">
							<button type="button" data-remote="./createForm" class="btn btn-primary" data-toggle="modal" data-target=".modal-create">
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
	
	<!-- lessonTemplate model -->
	<div class="modal fade bs-example-modal-lg modal-lessonTemplate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<!-- x title -->
							<div class="x_title">
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"> <i class="fa fa-close"></i>
									</a></li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<!-- /end x title -->
							<!-- datatable -->
							<div class="x_content table-responsive">
								<table id="lessonTemplate-datatable" class="table table-bordered table-hover table-condensed" width="100%">
		
								</table>
							</div>
							<!-- /end datatable -->
						</div>
					</div>
				</div>
				
				<div class="modal fade bs-example-modal-lg modal-update-lessonTemplate" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content"></div>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	<!-- /lessonTemplate model -->

	<!-- / bootstrap modals -->
    
	<!-- jQuery -->
	<!-- jquery, bootstrap datatable -->
	<script src="../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Select-1.2.3/js/dataTables.select.min.js" type="text/javascript"></script>
	<script src="../../assets/bootstrap3-dialog/bootstrap-dialog.min.js" type="text/javascript"></script>
	<script src="../../assets/bootstrap-validator/js/bootstrapValidator.min.js" type="text/javascript"></script>
	<script src="../../assets/bootstrap-validator/js/language/zh_CN.js" type="text/javascript"></script>
	<script src="../../assets/dropzone/min/dropzone.min.js" type="text/javascript"></script>
	<!-- Custom Theme Scripts -->
	<script src="../../backend/js/custom.min.js"></script>
	
   
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
					"url" : "./datatable",
					"type" : "POST"
				},
				"columns" : [{
					"title": "模板名",
					"data" : "name",
					"name" : ""
				},{
					"title" :"舞种名",
					"data":"danceName"
				},{
					"data" : "danceGradeName",
					"title": "舞种等级"
				},{
					"data" : "courseType",
					"title": "课程类型",
					"render":function(data, type, row, meta){
						return translateCourseType(data);
					}
				},{
					"data" : "subCourseType",
					"title": "课程中类",
					"render": function(data,type,row, meta){
						return translateSubCourseType(data);
					}
				},{
					"data" : "times",
					"title": "课程总节数"
				},{
					"data" : "hoursPerTime",
					"title": "每节课时长"
				},{
					"data" : "minStudentCount",
					"title": "最小学生数量"
				},{
					"data" : "maxStudentCount",
					"title": "最大学员数量"
				},{
					"data" : "effectiveStart",
					"title": "有效开始日期",
					"render":function(data,type,row, meta){
						return formatDate(data, "yyyy-MM-dd");
					}
				},{
					"data" : "effectiveEnd",
					"title": "有效结束日期",
					"render":function(data,type,row, meta){
						return formatDate(data, "yyyy-MM-dd");
					}
				},{
					"data" : "providerName",
					"title": "教材提供商"
				},{
					"data":"id",
					"title":"操作",
					"render": function(data, type, row, meta) {
						var html =  '';
						<shiro:hasPermission name="courseTemplates:view:*">
							html = html + '<a href="' + row.id + '/updateForm" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"></i>  </a>';
						</shiro:hasPermission>
						
						<shiro:hasPermission name="courseTemplates:update:*">
							html = html +  '<a href="#" onclick="showLessonTemplate(' + row.id + ')" data-toggle="modal" data-target=".modal-lessonTemplate">  <i class="fa fa-navicon" title="查看标准模板课时"></i></a>';
						</shiro:hasPermission>
						
						<shiro:hasPermission name="courseTemplates:delete:*">
							html = html +  '<a onclick="showDeleteModal(' + row.id + ',refreshDataTable)"> <small> <i class="fa fa-trash" title="删除"> </i> </small></a>';
						</shiro:hasPermission>
						return html;
					}
				} ]
			}; //end datatable setting
			
			var lessonTemplateDatatable;
			var lessonTemplate_datatable_setting = 
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
							html = html + '<a href="' + row.courseTemplateId +'/lessonTemplates/' +  row.id + '/updateForm" data-toggle="modal" data-target=".modal-update-lessonTemplate"> <i class="fa fa-pencil" title="修改"></i>  </a>';
						</shiro:hasPermission>
						
						return html;
					}
				} ]
			}; //end lessonTemplate_datatable_setting setting
			
			
			
			function showLessonTemplate(v_courseTemplateId){
				lessonTemplate_datatable_setting.ajax.url="./" +v_courseTemplateId + "/lessonTemplates/datatable";
				lessonTemplateDatatable=$("#lessonTemplate-datatable").DataTable(lessonTemplate_datatable_setting);
			}
  </script>
  
  <script src="../../backend/js/main.js"></script>
</body>
</html>