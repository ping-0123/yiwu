/**
 * 首页
 */
$(function(){

	BocAdmin = {
		updatePwd: function(){
			App.Window.open({
				title	: '修改密码',
				url		: Config.ROOT+'/sysuser/password.do',
				width	: 400,
				height	: 350
			});
		},
		updateInfo: function(){
			App.Window.open({
				title	: '修改个人信息',
				url		: Config.ROOT+'/sysuser/edit.do?oid='+$("#userId").val()
			});
		}
	}
	
});