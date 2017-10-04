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
						<shiro:hasPermission name="users:create:*">
							<button type="button" data-remote="form" class="btn btn-primary" data-toggle="modal" data-target=".modal-create">
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
		<div class="modal-dialog modal-md">
			<div class="modal-content"></div>
		</div>
	</div>
	
	<!-- /end create modal -->

	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg modal-update" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /end update modal -->
	
	<!-- start setting modal -->
	<div class="modal fade bs-example-modal-lg modal-setting" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
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
   		var column_index_create_time =0;
   		var setting = 
			{
				"processing" : false,
				"serverSide" : true,
				"language" : {
					"url" : "../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : "输入用户名 员工姓名"
				},
				"ajax" : {
					"url" : "./datatable",
					"type" : "POST"
				},
				"columns" : [{
					"data" : "createTime",
					"visible" : false
				}, {
					"data" : "username",
					"title": "用户名"
				}, {
					"data" :"employee.name",
					"title":"员工姓名",
 					"render":function(data,type, row, meta){
						return data==null?"":data;
					}
				},{
					"data" : "dataStatus",
					"title": "状态",
					"render" : function(data, type, row, meta) {
						return translateDataStatus(data);
					}
				},{
					"data":"createTime",
					"title":"操作",
					"render": function(data, type, row, meta) {
						if(row.username=="Admin")
							return "";
						var html =  '';
						<shiro:hasPermission name="users:update:*">
							html = html + '<a href="' + row.id + '/form" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"></i></a>';
							html = html +  '<a href="#" onclick="showUserRoles(' + row.id + ')" data-toggle="modal" data-target=".modal-setting"> <i class="fa fa-navicon" title="设置用户角色"></i></a>';
						</shiro:hasPermission>
						<shiro:hasPermission name="users:delete:*">
							html = html  + '<a href="#" onclick="showDeleteModal(' + row.id + ')"> <small> <i class="fa fa-trash" title="删除"> </i> </small> </a>';
						</shiro:hasPermission>
						return html;
					}
				} ]
			}; //end setting
			
			
			// start ztree setting
			var zNodes
			var zSetting = {
			            data: {
			                simpleData: {
			                    enable: true
			                }
			            },
						check:{
							enable:true,
							chkStyle:"checkbox",
							chkboxType:{"Y":"ps","N":"s"}
						}            
			};
			
	        //end ztree setting
          var userId;
          function showUserRoles(_userId){
	        userId = _userId;
        	$.ajax({
        		"url": _userId + "/roleZtree",
        		"success":function(data){
        			if(data.result){
        				zNodes= data.data;
        				$.fn.zTree.init($("#tree"), zSetting, zNodes);
        			}
        		}
        	});
     	 }
        
          function saveUserRoles(){
	          	var _userId = userId;
	          	var _roleIds = new Array();
	          	
	          	var treeObj = $.fn.zTree.getZTreeObj("tree");
	          	var nodes = treeObj.getCheckedNodes(true);
	          	for(var i=0; i< nodes.length; i++){
	          		_roleIds[i] = nodes[i].id;
	          	}
	          	
	          	requestSaveUserRoles(_userId,_roleIds);
          }
          
          function requestSaveUserRoles(_userId,_roleIds){
        	  $.ajax({
          		"type":"PUT",
          		"url":_userId+ "/roles",
          		"data":{
          			"roleIds":_roleIds
          		},
          		traditional: true,
          		"success":function(data){
          			if(data.result){
          				flashSaveSuccessModal();
          				setTimeout(function(){
	          				$(".modal-setting").modal("hide");
          				},1500);
          			}else{
          				showSaveFailureModal(data.msg);
          			}
          		}
          	});
          }
  </script>
  
  <script src="../../backend/js/main.js"></script>
</body>
</html>