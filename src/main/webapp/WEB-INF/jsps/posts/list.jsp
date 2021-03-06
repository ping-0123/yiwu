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

<!-- boostrap datatable -->
<link href="../../assets/datatables/datatables.min.css" rel="stylesheet" > 
<!-- Font Awesome -->
<link href="../../assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- bootstrap dialog -->
<link href="../../assets/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet" >
<!-- datatable button -->
<link href="../../assets/datatable-plugins/Buttons-1.4.2/css/buttons.bootstrap.min.css" rel="stylesheet">
<link href="../../assets/datatable-plugins/Select-1.2.3/css/select.bootstrap.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="../../backend/css/custom.min.css" rel="stylesheet">
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
					<div class="x_content">
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
	
	<!-- create modal -->

	<!-- update modal -->
	<div class="modal fade bs-example-modal-lg modal-update" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- /update modal -->
		<!-- start setting modal -->
	<div class="modal fade bs-example-modal-lg modal-setting" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" >设置职位的系统角色</h4>
				</div>
				<div class="modal-body">
					&nbsp&nbsp&nbsp
					<button class="btn btn-primary btn-sm" onclick="savePostRoles()"><small>保存</small></button>
					<ul id="tree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<!-- /end setting modal -->

	<!-- / bootstrap modals -->
    
    <!-- jquery bootstrap datatable -->	
	<script src="../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="../../assets/bootstrap3-dialog/bootstrap-dialog.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Buttons-1.4.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Buttons-1.4.2/js/buttons.bootstrap.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Buttons-1.4.2/js/buttons.html5.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Buttons-1.4.2/js/buttons.flash.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Select-1.2.3/js/dataTables.select.min.js" type="text/javascript"></script>
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
				"select":true,
				"language" : {
					"url" : "../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : "输入职位名"
				},
				"dom":"<'row'<'col-sm-4' l><'col-sm-4' B><'col-sm-4' f>>tip",
				"buttons":[
				            {
				        	   "extend":"excel",
				        	   "text":"导 出",
				        	   "className":"btn-primary"
				           } 
				],
				"ajax" : {
					"url" : "./datatable",
					"type" : "POST"
				},
				"columns" : [{
					"data" : "createTime",
					"title":"创建日期",
					"visible" : false
				},{
					"title": "id",
					"data" : "id",
					"name" : "postId"
				}, {
					"data" : "name",
					"title": "职位名"
				},{
					"data":"createTime",
					"title":"操作",
					"render": function(data, type, row, meta) {
						var html =  '';
						if($('#updatePermission').val()){
							html = html + '<a href="' + row.id + '/form" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"></i></a>';
							html = html +  '<a href="#" onclick="showPostRoles(' + row.id + ')" data-toggle="modal" data-target=".modal-setting"> <i class="fa fa-navicon" title="设置岗位角色"></i></a>';
						}
						if($('#deletePermission').val()){
							html = html  + '<a href="#" onclick="showDeleteModal(' + row.id + ')"> <small> <i class="fa fa-trash" title="删除"> </i> </small> </a>';
						}
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
          var postId;
          function showPostRoles(_postId){
        	postId = _postId;
        	$.ajax({
        		"url": _postId + "/roleZtree",
        		"success":function(data){
        			if(data.result){
        				zNodes= data.data;
        				$.fn.zTree.init($("#tree"), zSetting, zNodes);
        			}
        		}
        	});
     	 }
        
          function savePostRoles(){
	          	var _postId = postId;
	          	var _roleIds = new Array();
	          	
	          	var treeObj = $.fn.zTree.getZTreeObj("tree");
	          	var nodes = treeObj.getCheckedNodes(true);
	          	for(var i=0; i< nodes.length; i++){
	          		_roleIds[i] = nodes[i].id;
	          	}
	          	
	          	requestSavePostRoles(_postId,_roleIds);
          }
          
          function requestSavePostRoles(_postId,_roleIds){
        	  $.ajax({
          		"type":"POST",
          		"url":_postId+ "/roles",
          		"data":{
          			"roleIds":_roleIds  ,
          			"_method":"PUT"
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
  
  <script src="${pageContext.request.contextPath}/backend/js/main.js"></script>
</body>
</html>