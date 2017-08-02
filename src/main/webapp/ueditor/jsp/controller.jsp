<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
//	String rootPath = application.getRealPath( "/" );
	String rootPath="C:\\Program\ Files\\Tomcat\\apache-tomcat-8.0.39\\webapps\\resources\\ueditor\\";
	out.write( new ActionEnter( request, rootPath ).exec() );
	
%>