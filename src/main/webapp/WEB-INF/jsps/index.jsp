<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>yiwu后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout-default-latest.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/JQuery zTree v3.5.15/css/zTreeStyle/zTreeStyle.css">
</head>
<body>

<iframe name="content" class="ui-layout-center"
        src="welcome" frameborder="0" scrolling="auto"></iframe>
<div class="ui-layout-north">欢迎&nbsp<shiro:principal/>&nbsp进入音之舞后台管理系统，<a href="logout">退出</a></div>
<div class="ui-layout-south"> 放置其他一些信息</div>
<div class="ui-layout-west">
    功能菜单<br/>
    <div>
    	<ul id="menuTree" class="ztree"></ul>
    </div>
    
    <!--
    <div>
	    <c:forEach items="${menus}" var="m">
	        <a href="${pageContext.request.contextPath}/${m.url}" target="content">${m.name}</a><br/>
	    </c:forEach>
    </div>   -->
    
</div>


<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.layout-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/JQuery zTree v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script>
	var setting = {
        view: {
            dblClickExpand: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };
	
    var zNodes =[
        <c:forEach items="${menus}" var="m">
                { 	id:"${m.id}", 
                	pId:"${m.parent.id}", 
                	name:"${m.name}", 
                	<c:if test="${m.url != '' && m.url != null}"> 
                		url:"${m.url}" ,
                		target:"content" 
                	</c:if>
              	},
        </c:forEach>
    ];
	$.fn.zTree.init($("#menuTree"), setting, zNodes);		
	function onClick(e, treeId, treeNode){}
	
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>