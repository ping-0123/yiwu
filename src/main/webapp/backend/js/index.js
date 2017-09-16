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
                {   id:"${m.id}", 
                	pId:"${m.parent.id}", 
                	name:"${m.name}", 
                	<c:if test="${m.url != '' && m.url != null}"> 
                		url:"${pageContext.request.contextPath}/system/${m.url}" ,
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