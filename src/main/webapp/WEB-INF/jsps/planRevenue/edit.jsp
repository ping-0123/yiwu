<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE HTML>
<html>
	<div>
		<form:form commandName="plan" action="../${plan.id}" method="post">
			<p>
				<form:hidden path="storeId" value="${plan.storeId}"/>
				<form:hidden path="year" value="${plan.year}"/>
				<form:hidden path="month" value="${plan.month}"/>
				<form:hidden path="productTypeId" value="${plan.productTypeId}"/>
			<p>
			<p>
			门店：${plan.storeName} <br />
			月份：${plan.year}年${plan.month}月 <br />
			产品线:${plan.productTypeName} <br />
			原始计划营业额: ${plan.amount} <br />
			</p>
			<p>
			<label>修改后的营业额:</label>
			<form:input type="text" path="amount" />
			</p>
			<p id="button">
				<input id="submit" type="submit" tabindex="5" value="保存" />
			</p>
		</form:form>
		
	</div>
</html>