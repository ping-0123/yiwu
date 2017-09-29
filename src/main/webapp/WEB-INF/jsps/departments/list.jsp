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

<title>用户列表</title>

<!-- Font Awesome -->
<link href="../../assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- boostrap datatable -->
<link href="../../assets/datatables/datatables.min.css" rel="stylesheet" > 
<!-- bootstrap dialog -->
<link href="../../assets/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet" >
<!-- Custom Theme Style -->
<link href="../../backend/css/custom.min.css" rel="stylesheet">
<!-- Yiwu Theme Style -->
<link href="../../backend/css/main.css" rel="stylesheet">
<!-- ztree -->
<link rel="stylesheet" href="../../assets/jquery-ztree-v3.5.15/css/zTreeStyle/zTreeStyle.css">
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
						<ul id="tree" class="ztree"></ul>
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
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" >设置用户角色</h4>
				</div>
				<div class="modal-body">
					&nbsp&nbsp&nbsp
					<button class="btn btn-primary btn-sm" onclick="saveUserRoles()"><small>保存</small></button>
					<ul id="tree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div> 
	<!-- /end setting modal -->
	
	<!-- /end bootstrap modals -->
    
	<!-- jquery bootstrap datatable -->	
	<script src="../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="../../assets/bootstrap3-dialog/bootstrap-dialog.min.js" type="text/javascript"></script>
	<!-- ztree -->
	<script src="../../assets/jquery-ztree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
	<!-- validator -->
	<script src="../../assets/validator/validator.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="../../backend/js/custom.min.js"></script>
   <script type="text/javascript">
   			column_index_create_time=0;
			// start ztree setting
			var zNodes
			var zSetting = {
			            data: {
			                simpleData: {
			                    enable: true
			                }
			            }
			};
	        //end ztree setting
	        
	        $(document).ready(function(){
	        	loadDepartmentZtree();
	        });
	        
	        function loadDepartmentZtree(){
	        	$.ajax({
	        		"url": "ztree",
	        		"success":function(data){
	        			if(data.result){
	        				zNodes= data.data;
	        				$.fn.zTree.init($("#tree"), zSetting, zNodes);
	        			}
	        		}
	        	});
	        }
  </script>
  
  <script src="../../backend/js/main.js"></script>
</body>
</html>