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
<link href="../../../../assets/font-awe	some/css/font-awesome.min.css" rel="stylesheet">

<!-- my datatable -->
<link href="../../../../assets/datatables/datatables.min.css" rel="stylesheet" > 
<link href="../../../../assets/datatable-plugins/Select-1.2.3/css/select.bootstrap.min.css" rel="stylesheet">
<!-- bootstrap dialog -->
<link href="../../../../assets/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet" >
	<!-- dropzone -->
<link href="../../../../assets/dropzone/min/dropzone.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="../../../../backend/css/custom.min.css" rel="stylesheet">
<!-- Yiwu Theme Style -->
<link href="../../../../backend/css/main.css" rel="stylesheet">

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
						<div>${distributer.name } 的订单</div>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i></a></li>
							<li><a href=""> <i class="fa fa-refresh"></i></a></li>
							<li><a class="close-link"> <i class="fa fa-close"></i>
							</a></li>
						</ul>

						<div class="clearfix"></div>
					</div>
					<div class="x_content table-responsive">
						<shiro:hasPermission name="orders:create:*">
							<button type="button" data-remote="./createForm" class="btn btn-primary" data-toggle="modal" data-target=".modal-create">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
							</button>
						</shiro:hasPermission>
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
	

	<!-- / bootstrap modals -->
    
	<!-- jQuery -->
	<!-- jquery, bootstrap datatable -->
	<script src="../../../../assets/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="../../../../assets/datatable-plugins/Select-1.2.3/js/dataTables.select.min.js" type="text/javascript"></script>
	<script src="../../../../assets/bootstrap3-dialog/bootstrap-dialog.min.js" type="text/javascript"></script>
	<!-- dropzone -->
	<script src="../../../../assets/dropzone/min/dropzone.min.js" type="text/javascript"></script>
	<!-- validator -->
	<script src="../../../../assets/validator/validator.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="../../../../backend/js/custom.min.js"></script>
	
   
   <script type="text/javascript">
   		// start datatable setting
   		var column_index_create_time =0;
   		var setting = 
			{
   				"scrollX":true,
				"processing" : false,
				"serverSide" : true,
				"select":true,
				"language" : {
					"url" : "../../../../backend/config/i18n/datatable-chinese.json",
					"searchPlaceholder" : "会员卡号,呢称,姓名,手机号码"
				},
				"dom":"<'row'<'col-sm-4 col-md-4' l><'col-sm-8 col-md-8' f>>tip",
				"ajax" : {
					"url" : "./datatable",
					"type" : "POST"
				},
				"columns" : [{
					"data" : "productId",
					"title" : "产品Id",
					"visible" : false
				},{
					"title": "产品名",
					"data" : "productName",
					"name" : ""
				}, {
					"title" :"标价",
					"data":"markedPrice"
				},{
					"data" : "count",
					"title": "购买数量"
				},{
					"data" : "discount",
					"title": "折扣"
				},{
					"data" : "payedAmount",
					"title": "金额"
				},{
					"data" : "payedDate",
					"title": "成交日期",
					"searchable":false
				},{
					"data" : "storeName",
					"title": "门店"
				},{
					"data": "contract.contractNo",
					"title": "合约编号"
				},{
					"data": "contract.start",
					"title": "开始日期"
				},{
					"data": "contract.end",
					"title": "结束日期"
				},{
					"data": "contract.validityTimes",
					"title": "有效次数"
				},{
					"data": "contract.remainTimes",
					"title": "剩余次数"
				},{
					"data": "contract.withHoldTimes",
					"title": "待结算次数"
				},{
					"data": "contract.type",
					"title": "合约类型",
					"render":function(data,type,row,meta){
						return translateCourseType(data);
					}
				},{
					"data": "contract.subType",
					"title": "合约中类",
					"render":function(data, type, row,meta){
						return translateSubCourseType(data);
					}
				},{
					"data": "contract.validStoreIds",
					"title": "有效门店"
				},{
					"data": "contract.status",
					"title": "合约状态",
					"render":function(data,type,row,meta){
						return translateContractStatus(data);
					}
				},{
					"data":"id",
					"title":"操作",
					"render": function(data, type, row, meta) {
						var html =  '';
						<shiro:hasPermission name="orders:create:*">
							html = html +  '<a href="' + row.id + '/updateForm" data-toggle="modal" data-target=".modal-update"> [付款]</a>';
						</shiro:hasPermission>	
						<shiro:hasPermission name="orders:view:*">
							html = html + '<a href="details?coachId=' + row.id + '" data-toggle="modal" data-target=".modal-detail"> [查看订单]  </a>';
						</shiro:hasPermission>
						
						return html;
					}
				} ]
			}; //end datatable setting
			
			
			
			
		/**
		 * 
		 * @param contractStatus
		 * @returns
		 */
		function translateContractStatus(contractStatus){
			switch (contractStatus) {
			case "UN_PAYED":
				return "待付款";
			case "UN_VERIFIED":
				return "未确认";
			case "VERIFIED":
				return "已确认";
			case "UN_CHECKED":
				return "未审核";
			case "UN_CHECKED":
				return "未开始";
			case "CHECKED":
				return "已审核";
			case "UN_PASSED":
				return "审核未通过";
			case "LEFT":
				return "请假";
			case "RETURNED_PREMIUM":
				return "退费";
			case "FORBIDDEN":
				return "禁用";
			case "EXPIRED":
				return "到期";
			
			default:
				return "";
			}
		}
  </script>
  
  <script src="../../../../backend/js/main.js"></script>
</body>
</html>