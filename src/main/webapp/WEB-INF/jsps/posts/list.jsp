<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>DataTables | Gentelella</title>

<!-- Bootstrap -->
<link href="../backend/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../backend/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Datatables -->
<link
	href="../backend/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../backend/css/custom.min.css" rel="stylesheet">

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
						 	<button type="button" onclick="btnNew()" class="btn btn-primary" data-toggle="modal" data-target="#create">
						 		<span class="glyphicon glyphicon-plus" aria-hidden="false"></span>  新增  
						 	</button>
						</shiro:hasPermission>
						
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li> <a href=""><i class="fa fa-refresh"></i></a>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>职位</th>
									<th>描述</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${posts}" var="p">
									<tr id="${p.id}">
										<td>${p.name}</td>
										<td>${p.description}</td>
										<td>
											<shiro:hasPermission name="posts:update">
												<a  data-toggle="modal" data-target="#myModal" onclick="btnNew('${p.id}')"><i class="fa fa-pencil" title="修改"></i></a>
												<a data-toggle="modal" data-target="#myModal" ><i class="fa fa-navicon" title="设置岗位职责"></i>
											</shiro:hasPermission> 
											<shiro:hasPermission name="posts:delete">
												<a  data-toggle="modal" data-target="#myModal"><i class="fa fa-minus-circle" title="删除"></i> </a>
											</shiro:hasPermission>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>



		</div>
	</div>
	<!-- /page content -->
	
	
	<!-- 修改 modal -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增岗位</h4>
				</div>
					
					</br>

				<form id="demo-form2" data-parsley-validate
					class="form-horizontal form-label-left">

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="first-name">First Name <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="first-name" required="required"
								class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12"
							for="last-name">Last Name <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="last-name" name="last-name"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label for="middle-name"
							class="control-label col-md-3 col-sm-3 col-xs-12">Middle
							Name / Initial</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="middle-name" class="form-control col-md-7 col-xs-12"
								type="text" name="middle-name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Gender</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div id="gender" class="btn-group" data-toggle="buttons">
								<label class="btn btn-default" data-toggle-class="btn-primary"
									data-toggle-passive-class="btn-default"> <input
									type="radio" name="gender" value="male"> &nbsp; Male
									&nbsp;
								</label> <label class="btn btn-primary" data-toggle-class="btn-primary"
									data-toggle-passive-class="btn-default"> <input
									type="radio" name="gender" value="female"> Female
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Date
							Of Birth <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="birthday"
								class="date-picker form-control col-md-7 col-xs-12"
								required="required" type="text">
						</div>
					</div>
					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button class="btn btn-primary" type="button">Cancel</button>
							<button class="btn btn-primary" type="reset">Reset</button>
							<button type="submit" class="btn btn-success">Submit</button>
						</div>
					</div>

				</form>
				</br>
                </br>
			</div>
		</div>
	</div>



	<!-- jQuery -->
	<script src="../backend/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../backend/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Datatables -->
	<script
		src="../backend/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="../backend/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="../backend/js/custom.min.js"></script>
	<script type="text/javascript">
		function btnNew(string){
		}
		
	</script>
</body>
</html>