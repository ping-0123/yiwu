/**
 * 约定：$开头表示选择器
 */

var DELETE_URL,
	$TABLE =$('#yiwuDatatable'),
	TABLE;

$(document).ready(function(){
	TABLE = $TABLE.DataTable(setting);
});

/**
 * 关闭模态框自动清除数据
 */
$(".modal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
})

/**
 * 显示删除模态框
 * @param url
 * @returns
 */
function showDeleteModal(url) {
	$('.delete-promt').modal('show');
	DELETE_URL = url;
}

/**
 * 发送删除请求
 * @param url
 * @returns
 */
function doDeleteRequest() {
	$.ajax({
		url : DELETE_URL,
		type : 'DELETE',
		success : function(data) {
			var dlg = BootstrapDialog.show({
			    message: '已成功删除',
			    title:'提示',
			    size : BootstrapDialog.SIZE_SMALL
			});
			
			setTimeout(function(){
			    dlg.close();
			    TABLE.draw(); //刷新表
			},1000);
			
		}
	});
};


/**
 * 转换数据状态
 * @param dataStatus
 * @returns
 */
function translateDataStatus(dataStatus) {
	switch (dataStatus) {
	case "NORMAL":
		return "正常";
	case "FORBID":
		return "禁用";
	default:
		return "删除";
	}
}

