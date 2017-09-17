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
    <!-- Datatables -->
    <link href="../backend/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">

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
                          <th>用户名</th>
                          <th>状态</th>
                          <th>操作</th>
                        </tr>
                      </thead>

                      <tbody>
                      		<c:forEach items="${users}" var="u">
                      			<tr>
                      				
                      				<td>${u.employee.name}</td>
                      				<td>${u.username}</td>
                      				<td>${u.dataStatus}</td>
                      				<td>
                      					<shiro:hasPermission name="users:update">
                      						<a href="users/${u.id}/password/edition">修改</a>
                      					</shiro:hasPermission>
                      					<shiro:hasPermission name="users:delete">删除</shiro:hasPermission>
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
    <!-- Datatables -->
    <script src="../backend/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="../backend/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../backend/js/custom.min.js"></script>

  </body>
</html>