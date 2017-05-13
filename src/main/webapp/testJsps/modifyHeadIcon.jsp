<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form commandName="d"  action="<%=request.getContextPath() %>/api/distributer/modifyHeadIcon" method="post" enctype="multipart/form-data">
		 <fieldset>
		<input type="text" name ="id", value="3000018" />
		<p>
		<input type="file" path="image"/>
		</p>
		<p id="button">
        <input id="reset" type="reset">
        <input id="submit" type="submit" />
        </p>
        </fieldset>
     </form:form>
</body>
</html>