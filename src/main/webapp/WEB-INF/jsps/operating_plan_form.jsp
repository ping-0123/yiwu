<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Add Oprating Plan</title>
		<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
	</head>
	<body>
		<div id="global">
		<form:form commandName="product" action="save" method="post">
			<fieldset>
				<legend>Add an product</legend>
				<p>
					<label for="name">name: </label>
					<form:input path="name" tabindex="1"/>
				</p>
				<p>
					<label for="description">description: </label>
					<form:input path="description" tabindex="2"/>
				</p>
				<p>
					<form:errors path="description" cssClass="error"/>
				</p>
				<p>
					<label for="price">price: </label>
					<form:input path="price" tabindex="3" />
				</p>
				<p>
					<label for="productionDate">productionDate: </label>
					<form:input path="productionDate" tabindex="4" />
				</p>
				<p id="buttons">
					<input id="reset" type="reset" tabindex="4">
					<input id="submit" type="submit" tabindex="5" value="Add Product">
				</p>
			</fieldset>
		</form:form>
		</div>
	</body>
</html>