<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ztree-v3.5.15/css/zTreeStyle/zTreeStyle.css">
</head>
<body>

<ul id="tree" class="ztree"></ul>

<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/jquery-ztree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script>
    $(function () {
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback : {
                onClick : function(event, treeId, treeNode) {
                    parent.frames['content'].location.href = treeNode.id+"/maintain";
                }
            }
        };

        var zNodes =[
            <c:forEach items="${organizationList}" var="d">
                { 	id:"${d.id}", 
	               	pId:"${d.superior.id}", 
	               	name:"${d.name}", 
	               	open:"false"},
            </c:forEach>
        ];

        $(document).ready(function(){
            $.fn.zTree.init($("#tree"), setting, zNodes);
        });
    });
</script>
</body>
</html>