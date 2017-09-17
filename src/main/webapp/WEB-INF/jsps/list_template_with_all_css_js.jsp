<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="../backend/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../backend/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../backend/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../backend/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="../backend/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="../backend/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../backend/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../backend/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="../backend/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

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
                    <h2>Default Example <small>Users</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li>
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>姓名</th>
                          <th>性别</th>
                          <th>手机号码</th>
                          <th>所在部门</th>
                          <th>职位</th>
                          <th>入司日期</th>
                          <th>操作</th>
                        </tr>
                      </thead>

                      <tbody>
                      		<c:forEach items="${employees}" var="e">
                      			<tr>
                      				<td>${e.name}</td>
                      				<td>${e.gender.cnGender}</td>
                      				<td>${e.cellphone}</td>
                      				<td>
	                      				<c:forEach items="${e.employeePosts }" var="ep">
	                      					${ep.department.name }
	                      					&nbsp
	                      				</c:forEach>
                      				</td>
                      				<td>
                      					<c:forEach items="${e.employeePosts }" var="ep">
	                      					${ep.post.name }
	                      					&nbsp
	                      				</c:forEach>
                      				</td>
                      				<td>${ e.createTime}</td>
                      				<td>
                      					<shiro:hasPermission name="employees:update">修改&nbsp设置岗位</shiro:hasPermission>
                      					<shiro:hasPermission name="employees:delete">删除</shiro:hasPermission>
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

    <!-- jQuery -->
    <script src="../backend/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../backend/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../backend/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../backend/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="../backend/vendors/iCheck/icheck.min.js"></script>
    <!-- Datatables -->
    <script src="../backend/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="../backend/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="../backend/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="../backend/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="../backend/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="../backend/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="../backend/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="../backend/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="../backend/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="../backend/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="../backend/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="../backend/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="../backend/vendors/jszip/dist/jszip.min.js"></script>
    <script src="../backend/vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="../backend/vendors/pdfmake/build/vfs_fonts.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../backend/js/custom.min.js"></script>

  </body>
</html>