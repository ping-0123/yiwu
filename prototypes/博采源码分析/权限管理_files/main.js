/**
 * 
 */
$(function() {

	BocAuthorization = {
		add : function() {
			App.Window.open({
				title : '添加角色',
				url : Config.ROOT + '/sysrole/edit.do',
				callback : function() {
					grid.reload();
				}
			});
		},
		edit : function(oid) {
			App.Window.open({
				title : '修改角色',
				url : Config.ROOT + '/sysrole/edit.do?oid=' + oid,
				callback : function() {
					grid.reload();
				}
			});
		},
		del : function(oids) {
			if (!oids)
				return MsgBox.warnDelete();
			App.MsgBox.confirm('删除后将无法恢复，确认要删除吗？', function() {
				App.Ajax.request({
					url : Config.ROOT + '/authoriz/deleteRole.do',
					params : {
						'roleId' : oids
					},
					success : function(resp) {
						grid.reload();
					}
				});
			});
		},
		saveRoleModule : function() {
			var rd = grid.getSelected();
			if (!rd)
				return MsgBox.msg('请选择角色');
			var nodes = moduletree.getCheckedNodes(), moduleIds = [];
			$(nodes).each(function(node) {
				moduleIds.push(this.oid);
			});
			App.Ajax.request({
				url : Config.ROOT + '/authoriz/saveRoleModuleRelation.do',
				params : {
					'roleId' : rd.oid,
					'moduleIds' : moduleIds.join(',')
				}
			});
		}
	};

	/*
	 * var tree = App.Tree.init({ id : "#bocauthorization_tree_id", url :
	 * Config.ROOT + '/sysrole/treeList.do', nameIsHTML: true, callback: {
	 * onClick: function(event, treeId, treeNode) {
	 * if(App.Tree.unbindClick(treeNode))return; var postData = {
	 * 'search_eql_role.oid' : treeNode.oid };
	 *  }, onNodeCreated: function(event, treeId, treeNode){ if(treeNode.type ==
	 * 'DISABLE'){ treeNode.name = treeNode.name + '(<font color="red">已禁用</font>)';
	 * var treeObj = $.fn.zTree.getZTreeObj(treeId);
	 * treeObj.updateNode(treeNode); } } }, compile:{ onEdit : function(node){
	 * BocAuthorization.edit(node.oid); }, onRemove : function(node){
	 * BocAuthorization.del(node.oid); } } });
	 */

	// 初始化角色表格
	var grid = App.Grid
			.init({
				multiselect : false,
				rownumbers : true,
				isPage : false,
				url : Config.ROOT + '/sysrole/list.do',
				colNames : [ '名称', '状态', '操作', 'oid' ],
				colModel : [
						{
							name : 'name',
							index : 'name'
						},
						{
							name : 'statusValue',
							index : 'statusFlag',
							width : 60
						},
						{
							name : 'oid',
							index : 'oid',
							width : 60,
							formatter : function(v, o, rd) {
								var html = '<a onClick="BocAuthorization.edit('
										+ v
										+ ')" style="text-decoration:none" class="ml-5" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>';
								if (v > 4) {
									html += '<a onClick="BocAuthorization.del('
											+ v
											+ ')" style="text-decoration:none" class="ml-5" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
								}
								return html;
							}
						}, {
							name : 'oid',
							hidden : true
						} ],
				onSelectRow : function(id) {
					moduletree.reAsyncChildNodes(null, "refresh");
				}
			});

	var moduletree = App.Tree
			.init({
				id : "#bocauthorization_module_tree_id",
				hasCheckbox : true,
				getAsyncUrl : function() {
					var rd = grid.getSelected(), roleId = '';
					if (rd)
						roleId = rd.oid;
					return Config.ROOT + '/authoriz/loadModuleTree.do?roleId='
							+ roleId;
				}
			});
});