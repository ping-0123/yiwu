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
		<form:form commandName="plan" action="../planRevenue" method="post">
			<fieldset>
				<legend>添加营业计划</legend>

				<p>
					<label for="storeId">门店: </label>
					<form:select id="storeId" path="storeId" items="${stores}" itemLabel="name" itemValue="id"/>
				</p>

				<p>
					<form:errors path="month" cssClass="error"/>
				</p>
				<p>
					<label for="month">月份: </label>
					<input id="input_month" type="month"  tabindex="3" value=""/>
				</p>
					<form:hidden  id="year" path="year"/>
					<form:hidden id="month" path="month"/>
				<p>
					<label for="productTypeId">产品线: </label>
					<form:select id="productType" path="productType.id" items="${productTypes}" itemLable="name" itemValue="id" />
				</p>
				<p>
					<label for="amount">营业额: </label>
					<form:input path="amount" tabindex="5" />
				</p>
				<p id="buttons">
					<input id="reset" type="reset" tabindex="4">
					<input id="submit" type="submit" tabindex="5" value="添加">
				</p>
			</fieldset>
		</form:form>
	
		</div>
			
	</body>
	
	<script type="text/javascript">
		var v_defaultDate;
		if ($('#input_month').val() == ""){
			v_defaultDate = new Date();}
		else{
			v_defaultDate = new Date($('#input_month').val());
		}
	//	document.getElementById('input_month').valueAsDate = v_Today;
		$('#year').val(v_defaultDate.getFullYear());
		$('#month').val(v_defaultDate.getMonth()+ 1);
		
		
		$('#input_month').change(function(){
			var v_date = new Date($(this).val());
			$('#year').val(v_date.getFullYear());
			$('#month').val(v_date.getMonth()+ 1);
		});
	</script>
</html>