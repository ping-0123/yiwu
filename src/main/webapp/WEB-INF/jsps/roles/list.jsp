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

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/backend/vendors/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/backend/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- my datatable -->
<link href="${pageContext.request.contextPath}/backend/DataTables/datatables.min.css" rel="stylesheet">
<link href="../../assets/datatable-plugins/Select-1.2.3/css/select.bootstrap.min.css" rel="stylesheet">
<!-- bootstrap dialog -->
<link href="${pageContext.request.contextPath}/backend/css/bootstrap-dialog.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/backend/css/custom.min.css" rel="stylesheet">
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
					<!-- title -->
					<div class="x_title">
						<shiro:hasPermission name="roles:create:*">
								<button type="button" data-remote="form" class="btn btn-primary" data-toggle="modal" data-target=".modal-create">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
								</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="roles:update:*">
							<input type="hidden" id="updatePermission" value="true" />
						</shiro:hasPermission>
						<shiro:hasPermission name="roles:delete:*">
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
					<!-- /end title -->
					
					<!-- content -->
					<div class="x_content " >
					
						<!-- table -->
							<div class="Conleft col-md-9 col-xs-9 pull-left" >
							
							<table id="yiwuDatatable" class="table table-bordered table-hover table-condensed table-roles" width="100%">
	
							</table>
							</div>
						<!-- /end table -->
						
						<!-- resource tree -->
						<div class="conRight col-md-3 col-xs-3 pull-left">
							<div>
								<shiro:hasPermission name="roles:update:*">
									<button class="btn btn-primary btn-sm" onclick="saveRoleResources()">
										<small>保存</small>
									</button>
								</shiro:hasPermission>
							</div>
						
							<div >
								<ul id="tree" class="ztree"></ul>
							</div>
						</div>
						<!-- /end resource tree -->
					
					
					</div>
					<!-- /end context -->

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
	<%-- 已继承在datatables.min.js里	<script src="${pageContext.request.contextPath}/backend/vendors/jquery/dist/jquery.min.js"></script> --%>
	<!-- Bootstrap -->
	<%-- <script src="${pageContext.request.contextPath}/backend/vendors/bootstrap/dist/js/bootstrap.min.js"></script> --%>
	<script src="${pageContext.request.contextPath}/backend/js/datatables.min.js" type="text/javascript"></script>
	<script src="../../assets/datatable-plugins/Select-1.2.3/js/dataTables.select.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/backend/js/bootstrap-dialog.min.js" type="text/javascript"></script>

	<!-- validator -->
	<script src="${pageContext.request.contextPath}/backend/vendors/validator/validator.js"></script>
	<!-- ztree -->
	<script src="../../assets/jquery-ztree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="${pageContext.request.contextPath}/backend/js/custom.min.js"></script>
	<script src="../../backend/js/main.js"></script>

	<script type="text/javascript">
	
		//START datatable SETTING
		var column_index_create_time = 0;
		var setting = {
			"processing" : false,
			"serverSide" : true,
			"select":true,
			"language" : {
				"url" : "../../backend/config/i18n/datatable-chinese.json",
				"searchPlaceholder" : "输入角色名"
			},
			/*  			"buttons" : [ {
			 "extend" : "create"
			 } ], */
			"ajax" : {
				"url" : "./datatable",
				"type" : "POST"
			},
			"columns" : [
					{
						"data" : "createTime",
						"visible" : false
					},
					{
						"title" : "角色名",
						"data" : "name",
						"name" : "name"
					},
					{
						"data" : "dataStatus",
						"title" : "状态",
						"render" : function(data, type, row, meta) {
							return translateDataStatus(data)
						}
					},
					{
						"data" : "createTime",
						"title" : "操作",
						"render" : function(data, type, row, meta) {
							var html = '';	
							<shiro:hasPermission name="roles:update:*">
								html = html
										+ '<a href="' + row.id + '/form" data-toggle="modal" data-target=".modal-update"> <i class="fa fa-pencil" title="修改"></i></a>';
							</shiro:hasPermission>
							<shiro:hasPermission name="roles:delete:*">
								html = html
										+ '<a href="#" onclick="showDeleteModal('
										+ row.id
										+ ')"> <small> <i class="fa fa-trash" title="删除"> </i> </small> </a>';
							</shiro:hasPermission>
							return html;
						}
					} ]
		}; //end datatable setting
		
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
		
        $(document).ready(function(){
        	loadResourceTree();
        	if($TABLE==undefined) return;
        	TABLE = $TABLE.DataTable(setting);
        	$('#yiwuDatatable tbody').on('click', 'tr', function(){
        		var rowData = TABLE.row(this).data();
        		var roleId =JSON.parse(JSON.stringify(rowData)).id;
        		freshResourceTree(roleId);
        	});
        });
        //end ztree setting
        
        
        function loadResourceTree(){
        	$.ajax({
        		"url":"../resources/ztree",
        		"success":function(data){
        			if(data.result){
	        			zNodes = data.data;
			            $.fn.zTree.init($("#tree"), zSetting, zNodes);
        			}
        		}
        	});
        }
        
        function freshResourceTree(roleId){
        	$.ajax({
        		"url":roleId + "/resourceZTree",
	        	"success":function(data){
	    			if(data.result){
	        			zNodes = data.data;
			            $.fn.zTree.init($("#tree"), zSetting, zNodes);
	    			}
	    		}
        	});
        }
        
        
        function saveRoleResources(){
        	var roleId;
        	var resourceIds = new Array();
        	var rowData = TABLE.row($('.selected')).data();
        	if(rowData==undefined){
        		alert("no rows selected");
        		return;
        	}else{
        		roleId =JSON.parse(JSON.stringify(rowData)).id;
        	}
        	
        	var treeObj = $.fn.zTree.getZTreeObj("tree");
        	var nodes = treeObj.getCheckedNodes(true);
        	for(var i=0; i< nodes.length; i++){
        		resourceIds[i] = nodes[i].id;
        	}
        	
        	requestSaveRoleResources(roleId,resourceIds);
        }
        
        /**
         * 保存对权限的修改
         * @param roleId
         * @param resourceIds
         * @returns
         */
        function requestSaveRoleResources(roleId, resourceIds){
        	$.ajax({
        		"type":"PUT",
        		"url":roleId + "/resources",
        		"data":{
        			"resourceIds":resourceIds
        		},
        		traditional: true,
        		"success":function(data){
        			if(data.result){
        				flashSaveSuccessModal();
        			}else{
        				showSaveFailureModal(data.msg);
        			}
        		}
        	});
        }

	</script>
	
</body>
</html>