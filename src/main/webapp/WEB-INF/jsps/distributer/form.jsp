<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE HTML>
<html>
	<head>
		<title>Add Employee Form</title>
		<style type="text/css">@import url("<c:url value="/css/planRevenue/main.css"/>");</style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
	</head>
	<body>
		<div id="global">
		<form:form commandName="distributerApiView" action="modifyHeadIcon" method="post" enctype="multipart/form-data">
		  <fieldset>
		<form:hidden path = "id" value="3000018" />
		<input type="file" name="image"/>
        <input id="reset" type="reset" >
        <input id="submit" type="submit" />
        </fieldset>
     </form:form>
	
		</div>
			
	</body>
	
	
</html>