<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="select">
	<form:form commandName="plan" action="./list" method="get">
		<p>
		<label for="storeId">门店: </label>
		<form:select id="storeId" path="storeId" items="${allStores}" itemLabel="name" itemValue="id"/>
		</p>
		<p>
			<label for="month">月份: </label>
			<input id="input_month" type="month"  tabindex="3" value="${plan.year}-0${plan.month}" />
			<form:hidden  id="year" path="year"/>
			<form:hidden id="month" path="month"/>
		</p>
		
		<p>
			<label for="productTypeId">产品线: </label>
			<form:select id="productType" path="productType.id" items="${allProductTypes}" itemLable="name" itemValue="id" />
		</p>
		<p id="buttons">
			<input id="submit" type="submit" tabindex="5" value="查询">
		</p>
	</form:form>
	</div>
	<div id="list">

	<table border="1">
		<thead>
		<tr>
			<td>门店</td>
			<td>年份</td>
			<td>月份</td>
			<td>产品线</td>
			<td>营业额</td>
			<td>修改</td>
		</tr>
		</thead>
		<c:forEach items="${list}" var="p">
		<tr>
			<td>${p.storeName}</td>
			<td>${p.year}</td>
			<td>${p.month}</td>
			<td>${p.productTypeName}</td>
			<td>${p.amount}</td>
			<td><a href="edit/${p.id}">修改</a></td>
		</tr>
		</c:forEach>
	</table>
	
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