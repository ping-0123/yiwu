if (App == null) {
    App = {};
}


/***
 * 树型结构定义，需要jQuery zTree支持
 * 用法：
 * 		App.Tree.init(config);
 * 			@param {} config{
 * 				url			: 加载数据的url地址
 * 				otherParam	: 附加参数
 * 				hasCheckbox : 是否有checkbox
 * 				chkboxType	: 选择类型,参见官方实例
 * 				autoCheckTrigger: 自动关联勾选时是否触发beforeCheck/onCheck事件回调函数，默认true。如果开启触发，对于节点较多的树将会影响性能，可关闭
 * 				openRootNode: 是否展开根节点，默认false
 * 				initSelectedIds: 设置已选择的值,只有在hasCheckbox为true时有效
 * 				callback	: 回调函数
 * 			}
 * 
 * 更多用法请参考jQuery zTree
 * 
 * @author tongpuxin
 */
App.Tree = {
	setting : function(config){
		config = $.extend({callback : {}, compile :{}}, config);
		var onNodeCreatedFn = config.callback.onNodeCreated;
		var callback = $.extend(config.callback || {}, {
			onNodeCreated : function(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				if(!treeNode.parentId && config.openRootNode==true){
					treeObj.expandNode(treeNode, true);
				}
				//设置初始值
				if(config.hasCheckbox==true && config.initSelectedIds){//显示复选框
					var initSelectedIds = ',' + config.initSelectedIds + ',';
					if(initSelectedIds.indexOf(',' + treeNode.oid + ',') > -1){
						treeNode.checked = true;
						treeObj.updateNode(treeNode);
					}
				}
				if($.isFunction(onNodeCreatedFn)){
					onNodeCreatedFn(event, treeId, treeNode);
				}
			}
		});
		var setting = {
	        check: {
	            enable: config.hasCheckbox ? config.hasCheckbox : false,
	            //默认选中checkbox选中方式
	            chkboxType : config.chkboxType ? config.chkboxType : { "Y" : "ps", "N" : "ps" },
	            autoCheckTrigger : config.autoCheckTrigger ? config.autoCheckTrigger : true
	        },
	        edit: {
	        	enable: true,
				drag: {
					isCopy: false,
					isMove: config.isMove ? isMove :false
				},
	            showRemoveBtn : false,
    			showRenameBtn : false
	        },
	        view: {
	            dblClickExpand: false,
	            showLine: true,
	            selectedMulti: false,
	            fontCss : config.fontCss ? config.fontCss : null,
	            nameIsHTML: config.nameIsHTML ? config.nameIsHTML : false,
	            
            	addHoverDom: function(treeId, treeNode) {
            		var sObj = $("#" + treeNode.tId + "_span");
            		if (treeNode.editNameFlag 
            			|| $("#editBtn_"+treeNode.tId).length>0 
            			|| $("#removeBtn_"+treeNode.tId).length>0) return;
            		var str = "";
            		if(config.compileType){
            			if(config.compileType==treeNode.type){
            				if($.isFunction(config.compile.onEdit)){
			        			str += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
		        			}
		        			if($.isFunction(config.compile.onRemove)){
					        	str += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'></span>";
		            		}
            			}
            		}else{
            			if($.isFunction(config.compile.onEdit)){
			        		str += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
	        			}
	        			if($.isFunction(config.compile.onRemove)){
				        	str += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'></span>";
	            		}
            		}
            		
            		sObj.after(str);
            		if($.isFunction(config.compile.onEdit)){
            			var btn = $("#editBtn_"+treeNode.tId);
				        if (btn) btn.bind("click", function(){
				        	treeNode.unbindClick = false;
				            config.compile.onEdit(treeNode);
				        });
            		}
            		if($.isFunction(config.compile.onRemove)){
            			var btn = $("#removeBtn_"+treeNode.tId);
				        if (btn) btn.bind("click", function(){
				        	treeNode.unbindClick = false;
				            config.compile.onRemove(treeNode);
				        });
            		}
			    },
           	 	removeHoverDom: function(treeId, treeNode) {
			        $("#removeBtn_"+treeNode.tId).unbind().remove();
			        $("#editBtn_"+treeNode.tId).unbind().remove();
			    },
			    //节点后面添加的dom addDiyDom=fn(treeId, treeNode)
			    addDiyDom : config.addDiyDom ? config.addDiyDom :null
	        },
	        async: {
				enable: true,
				url: config.getAsyncUrl || config.url,
				dataFilter: function(treeId, parentNode, childNodes) {
					if (!childNodes) return null;
					for (var i=0, l=childNodes.length; i<l; i++) {
						childNodes[i].open = childNodes[i].expanded;
					}
					return childNodes;
				},
				//异步加载参数
				autoParam: config.autoParam || ["oid=parentId"],
				otherParam: config.otherParam
			},
	        callback: callback
	    };
	    return setting;
	},
	/**
	 * 初始化树结构
	 * @param {} config	配置参数
	 * @return {}	树
	 */
	init : function(config){
		config = $.extend({type : 'ajax', openRootNode: false}, config);
		var treeId = config.id || "#tree";
		if(treeId.indexOf('#') == -1)treeId = '#' + treeId;
		var tree = $.fn.zTree.init($(treeId), App.Tree.setting(config));
		return tree;
	},
	/**
	 * 获取选择的树节点
	 * @param {} zTree 树
	 * @return {} 已选择的节点
	 */
	getSelected : function(zTree){
		var nodes;
		if(typeof zTree == "string"){
			zTree = $.fn.zTree.getZTreeObj(zTree);
		}
		if(zTree){
			nodes = zTree.getSelectedNodes();
		}
		return nodes;
	},
	/**
	 * 检测treeNode是否不允许点击
	 * @param {} treeNode
	 * @return {Boolean}
	 */
	unbindClick: function(treeNode){
		if(treeNode && treeNode.unbindClick == false){
			treeNode.unbindClick = true;
			return true;
		}
		return false;
	}
};

/**
 * 定义下拉选择树，需要jQuery zTree支持
 * 用法：
 * 		App.Tree.init(config);
 * 		@param {} config{
 * 			treeId 		: "dict_inputTree",//显示树的html元素
 *			inputId 	: "parentId",//隐藏值域的隐藏域id
 *			inputName 	: "parentName",//显示已选择项的html元素id
 *			menuContent : "menuContent"
 * 		}
 * 		<input id="parentId" type="hidden" name="parent.oid" value="${entity.parent.oid}">
 *		<input id="parentName" type="text" name="parent.name" value="${entity.parent.name}" class="input" style="width:100%;"/>
 *		<div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color: white;border:1px solid gray;">
 *			<ul id="dict_inputTree" class="ztree"></ul>
 *		</div>
 *
 * 更多用法请参考jQuery zTree
 * 
 * @author tongpuxin
 */
App.InputTree = {
	/**
	 * 初始化树结构
	 * @param {} config	配置参数
	 * @return {}	树
	 */
	init : function(config){
		config = $.extend({cascade:false}, config);
		var tree = App.InputTree.load(config);
		$('#'+config.inputName).click(function(){
			App.InputTree.show(config);
		});
		return tree;
	},
	/**
	 * private
	 * @param {} config
	 */
	load : function(config){
		var treeConfig = $.extend(config, {
			id : "#"+config.treeId,
			type : 'ajax',
			url : config.url
		});
		var callback = $.extend(config.callback || {}, {
			onClick: function(e, treeId, treeNode) {
				if($.isFunction(config.verifyClick)){
					var valid = config.verifyClick(e, treeId, treeNode);
					if(valid==false){
						return;
					}
				}
				if($.isFunction(config.onClick)){
					config.onClick(e, treeId, treeNode);
				}else{
					$("#"+config.inputId).prop("value", treeNode.oid || treeNode.id || '');
					$("#"+config.inputName).prop("value", treeNode.name);
					App.InputTree.hide(config);
				}
				if($.isFunction(config.afterClick)){
					config.afterClick(e, treeId, treeNode);
				}
			},
			onCheck: function(e, treeId, treeNode){
				if($.isFunction(config.onCheck)){
					config.onCheck(e, treeId, treeNode);
				}else{
					var ids = $("#"+config.inputId).val();
					var names = $("#"+config.inputName).val();
					if(treeNode.checked){
						var id = treeNode.oid || treeNode.id || '';
						if(id){
							if(ids){
								ids += ','; names += ',';
							}
							ids += treeNode.oid || treeNode.id;
							names += treeNode.name;
						}
					}else{
						ids = ',' + ids + ',';
						names = ',' + names + ',';
						ids = ids.replace(',' + (treeNode.oid || treeNode.id) + ',', ',');
						names = names.replace(',' + treeNode.name + ',', ',');
						if(ids.startWith(','))ids = ids.substr(1);
						if(ids.endWith(','))ids = ids.substr(0, ids.length-1);
						if(names.startWith(','))names = names.substr(1);
						if(names.endWith(','))names = names.substr(0, names.length-1);
					}
					$("#"+config.inputId).prop("value", ids);
					$("#"+config.inputName).prop("value", names);
				}
				if($.isFunction(config.afterCheck)){
					config.afterCheck(e, treeId, treeNode);
				}
			},
			onAsyncSuccess: function (event, treeId, treeNode, msg){
				if($.isFunction(config.onAsyncSuccess)){
					config.onAsyncSuccess(event, treeId, treeNode);
					return;
				}
				/* TODO 此段有问题（应是解决级联选择时自动选择下级的功能）
				if(config.hasCheckbox){
					var selected = $("#"+config.inputId).prop("value");
					if(!String.isEmpty(selected) && !String.isEmpty(config.id)){
						var treeObj = $.fn.zTree.getZTreeObj(config.id.substring(1,config.id.length));
						if(treeObj){
							var arr = selected.split(",");
							if(config.chkboxType){
								App.InputTree.checkhaschkboxType(treeObj,config,arr);
							}else{
								App.InputTree.checknochkboxType(treeObj,arr);
							}
						}
					}
				}*/
			}
		});
		treeConfig.callback = callback;
		return App.Tree.init(treeConfig);
	},
	/**
	 * privte
	 * @param {} treeObj
	 * @param {} arr
	 */
	checknochkboxType : function (treeObj,arr){
		treeObj.expandAll(true);
		var nodes = treeObj.getNodes();
		for( var i = 0;i<nodes.length;i++){
			var childNodes = nodes[i].children;
			if(childNodes && childNodes.length > 0){
				for(var j = 0;j<childNodes.length;j++){
					App.InputTree.checkNodes(treeObj,childNodes[j],arr);
				}
			}
			if(arr.indexOf(nodes[i].id)> -1){
				treeObj.checkNode(nodes[i], true, true);
			}
		}
	},
	/**
	 * privte
	 * @param {} treeObj
	 * @param {} config
	 * @param {} arr
	 */
	checkhaschkboxType : function(treeObj,config,arr){
		treeObj.setting.check.chkboxType = { "Y" : "", "N" : "" };
		treeObj.expandAll(true);
		var nodes = treeObj.getNodes();
		for( var i = 0;i<nodes.length;i++){
			App.InputTree.checkNodes(treeObj,nodes[i],arr)
		}
		treeObj.setting.check.chkboxType = config.chkboxType;
	},
	/**
	 * privte
	 * @param {} treeObj
	 * @param {} treeNode
	 * @param {} arr
	 */
	checkNodes : function (treeObj,treeNode,arr){
		if(arr.indexOf(treeNode.id)> -1){
			treeObj.checkNode(treeNode, true, true);
		}
		var nodes = treeNode.children;
		if(null != nodes && nodes.length > 0 ){
			for( var i = 0;i<nodes.length;i++){
				if(null != nodes[i].children && nodes[i].children.length > 0){
					App.InputTree.checkNodes(treeObj,nodes[i],arr);
				}else{
					if(arr.indexOf(nodes[i].id)> -1){
						treeObj.checkNode(nodes[i], true, true);
					}
				}
			}
		}
	},
	/**
	 * privte
	 * 显示树
	 * @param {} config
	 */
	show : function(config){
		if(config.cascade==true){
			var parentVal = $("#"+config.cascadeParent).val();
			if(!parentVal){
				alert(config.cascadePrompt || 'parentVal值为空');
//				if(config.cascadePrompt){SW.Msg.prompt({type:1,msg:config.cascadePrompt});}
				return;
			}
			if(config.parentVal!=parentVal){
				var tempconfig = $.extend({}, config);
				tempconfig.url = config.url + '?'+config.cascadeParams+"="+parentVal,
				App.InputTree.load(tempconfig);
				config.parentVal = parentVal;
			}
		}
		var parent = $("#"+config.inputName);
		var parentOffset = $("#"+config.inputName).offset();
		$("#"+config.menuContent).css({
//										left:parentOffset.left + "px", 
//										top:(parentOffset.top + parent.outerHeight()-10) + "px",
										'z-index': 99999,//position定位如果有重叠的时候，z-index愈大，就显示在最上面
										'max-height': 300 + "px",
										overflow: 'auto'
								  }).slideDown("fast");
		$("#"+config.treeId).css({width: parent.width()-2+"px"}).slideDown("fast");
		$("body").bind("mousedown", function(event){ App.InputTree.onBodyDown(event, config.menuContent) });
	},
	/**
	 * privte
	 * 隐藏树
	 * @param {} config
	 */
	hide : function(config){
		$("#"+config.menuContent).fadeOut("fast");
		$("body").unbind("mousedown", function(event){ App.InputTree.onBodyDown(event, config.menuContent) });
	},
	/**
	 * privte
	 * @param {} event
	 * @param {} name
	 */
	onBodyDown: function(event, name){
		if (!(event.target.id == "menuBtn" || event.target.id == name || $(event.target).parents("#"+name).length>0)) {
			App.InputTree.hide({menuContent: name});
		}
	},
	/**
	 * 根据节点值选中节点
	 * @param {} treeId	树的id
	 * @param {} nodeValue	节点值
	 * @return {}	已选中的节点
	 */
	selectNode: function(treeId, nodeValue){
		if(treeId && nodeValue){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var nodes = treeObj.getNodes();
			if(nodes && nodes.length>0){
				for(var i=0; i<nodes.length; i++){
					if(nodeValue == nodes[i].value){
						 treeObj.selectNode(nodes[i]);
						 return nodes[i];
					}
				}
			}
		}
	},
	/**
	 * 获得节点的全路径
	 * @param treeNode 节点
	 * @param result 默认为""
	 */
	getPath : function (treeNode,result){
		result="/"+(treeNode.oid || treeNode.id)+result;
		if(String.isNotEmpty(treeNode.parentId)){
			 return App.InputTree.getPath(treeNode.getParentNode(),result);
		}else{
			return result; 
		}
	}
};