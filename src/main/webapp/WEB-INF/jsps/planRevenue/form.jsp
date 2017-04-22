<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Add Employee Form</title>
		<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
	</head>
	<body>
		<div id="global">
		<form:form commandName="plan" action="../planRevenue" method="post">
			<fieldset>
				<legend>Add an Monthly Operating Plan</legend>
				<p>
					<label for="storeId">门店: </label>
					<form:input path="storeId" tabindex="1"/>
				</p>
				<p>
					<label for="year">Year: </label>
					<form:input path="year" tabindex="2"/>
				</p>
				<p>
					<form:errors path="month" cssClass="error"/>
				</p>
				<p>
					<label for="month">Month: </label>
					<form:input type="Date" path="month" tabindex="3" />
				</p>
				<p>
					<label for="productTypeId">productTypeId: </label>
					<form:input path="productType.id" tabindex="4" />
				</p>
				<p>
					<label for="amount">Amount: </label>
					<form:input path="amount" tabindex="5" />
				</p>
				<p id="buttons">
					<input id="reset" type="reset" tabindex="4">
					<input id="submit" type="submit" tabindex="5" value="Add Plan">
				</p>
			</fieldset>
		</form:form>
		
	
		</div>
			<p>${plan.year}</p>
	</body>
</html>