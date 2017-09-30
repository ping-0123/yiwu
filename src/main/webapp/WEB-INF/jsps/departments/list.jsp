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
div#right-menu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
div#right-menu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
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
					<div class="x_content">
						<table class="table-striped" width="100%">
						<tr valign="top">
							<td class="col-md-2 col-sm-4" >
								<div class="input-group">
								  <input  type="text" placeholder="输入部门名称" id="department-name" onkeydown="enterDoSearch(event);" class="form-control">
								  <span class="input-group-btn">
								  	<button type="button" onclick="doSearch();" class="btn btn-primary">
								  		<i class="glyphicon glyphicon-search"></i>
								  	</button>
								  </span>
								</div>
								
								 <div>
									<ul id="tree" class="ztree"></ul>
								 </div>
							</td>
							<td width="65%" align="center">
								<div id="departmentDetail"></div>			
							</td>
						</tr>
						
						</table>
					</div>
				</div>
			</div>



		</div>
	</div>
	<!-- /page content -->
	
	<!-- 右键菜单 -->
	<div id="right-menu">
		<ul>
			<shiro:hasPermission name="departments:create:*">
				<li id="menu-create" onclick="showCreateForm();">新增</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="departments:update:*">
				<li id="menu-update" onclick="showUpdateForm();">修改</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="departments:delete:*">
				<li id="menu-create" onclick="deleteDepartment();">删除</li>
			</shiro:hasPermission>
		</ul>
	</div>
	<!-- / end 右键菜单 -->
	
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
			// start ztree setting
			
			var zNodes;
			var zSetting = {
			            data: {
			                simpleData: {
			                    enable: true
			                }
			            },
			            callback:{
			            	onClick:ztreeOnClick,
			            	onDrop:ztreeOnDrop,
			            	beforeRightClick: ztreeBeforeRightClick,
			            	onRightClick:ztreeOnRightClick
			            },
			            edit:{
			            	enable:true,
			            	showRenameBtn:false,
			            	showRemoveBtn:false,
			            	drag:{
			            		isCopy:false,
			            		isMove:true,
			            		prev:false,
			            		next:false
			            	}
			            }
			};
	        //end ztree setting
	        
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
	        
	        function ztreeOnClick(event, treeId, treeNode, clickFlag){
	        	var departmentId = treeNode.id;
	        	showUpdateForm(departmentId);
	        }
	        
	        function ztreeOnDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
	        	var id = treeNodes[0].id;
	        	if(targetNode==null) return;
	        	var parentId = targetNode.id;
	         	$.ajax({
	        		"url":id,
	        		"type":"PUT",
	        		"data":{
	        			"parent.id":parentId
	        		},
	        		"success":function(data){
	        			if(data.result){
	        				;
	        			}else
	        				showUpdateFailureModal(data.msg);
	        		}
	        	}); 
			}
	        
	        function ztreeOnRightClick(event, treeId,treeNode){
	        	if(treeNode==null) return;
	        	var _ztree= $.fn.zTree.getZTreeObj(treeId);
	        	_ztree.selectNode(treeNode);
	        	
	        	operating_department_id = treeNode.id;
	        	showRightMenu(event.clientX, event.clientY);
	        }
	        
	        function showRightMenu(x,y){
	        	$("#right-menu ul").show();
	        	$("#right-menu").css({"top":y+"px","left":x+"px","visibility":"visible"});
	        	$("body").bind("mousedown",onBodyMouseDown);
	        }
	        
	        function hiddenRightMenu(){
	        	if($("#right-meun")) $("#right-menu").css({"visibility":"hidden"});
	        	$("body").unbind("mousedown", onBodyMouseDown);
	        }
	        
	      	function onBodyMouseDown(){
	      		if (!(event.target.id == "right-menu" || $(event.target).parents("#right-menu").length>0)) {
    				$("#right-menu").css({"visibility" : "hidden"});
    			}
	      	}
	        
	        function ztreeBeforeRightClick(treeId,treeNode){
				var rightClickable=false;		        
	        	<shiro:hasPermission name="departments:create:*">
	        		rightClickable=true;
				</shiro:hasPermission>
				<shiro:hasPermission name="departments:update:*">
					rightClickable=true;
				</shiro:hasPermission>
				<shiro:hasPermission name="departments:delete:*">
					rightClickable=true;
				</shiro:hasPermission>
				
				return rightClickable;
	        }
	        
	        function showCreateForm(id){
	        	var _id=(id==undefined?operating_department_id:id);
				if(_id==undefined)	return;
	        	$.ajax({
	        		"url": "createForm",
	        		"data":{
	        			"parentId": _id
	        		},
	        		"success":function(data){
	        			$("#departmentDetail").html(data);
	        		}
	        	});
	        	
	        	hiddenRightMenu();
	        }
	        
	        function showUpdateForm(id){
	        	var _id=(id==undefined?operating_department_id:id);
				if(_id==undefined)	return;
	        	$.ajax({
	        		"url": _id + "/updateForm",
	        		"success":function(data){
	        			$("#departmentDetail").html(data);
	        		}
	        	});
	        	
	        	hiddenRightMenu();
	        }
	        
	 		function deleteDepartment(id){
	 			var _id=(id==undefined?operating_department_id:id);
				if(_id==undefined)	return;
	 			showDeleteModal(_id);
	 			setTimeout(loadDepartmentZtree, 1500);
	 		}
	 		
	 		function doSearch(){
	 			 var _ztree = $.fn.zTree.getZTreeObj("tree");
	 			 _ztree.cancelSelectedNode();
	 			 var _value=$("#department-name").val();
	 			 if(_value=="" || _value==null || _value==undefined) return;
	 			 var _treeNodes = _ztree.getNodesByParamFuzzy("name",_value,null);
	 			 for(var i=0; i<_treeNodes.length;i++){
 					 _ztree.selectNode(_treeNodes[i],true, false);
	 			 }
	 		}
	 		
	 		function enterDoSearch(event){
	 			if(event.keyCode==13)
	 				doSearch();
	 		}
	 		
	 		 var operating_department_id,ztree,$ZTREE, ztreeId;
	        $(document).ready(function(){
	        	ztreeId="tree";
	        	$ZTREE=$("#tree")
	        	ztree=loadDepartmentZtree();
	        });
  </script>
  
  <script src="../../backend/js/main.js"></script>
</body>
</html>