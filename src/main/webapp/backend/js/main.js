/**
 * 
 */


/**
 * 关闭模态框自动清除数据
 */
$(".modal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
})

/**
 * 删除操作
 * @param url
 * @returns
 */
function doDelete(url) {
	$('.delete-promt').modal('show');
	$('.delete-confirm').click(function() {
		deleteRequest(url);
	});
}

/**
 * 发送删除请求
 * @param url
 * @returns
 */
function deleteRequest(url) {
	$.ajax({
		url : url,
		type : 'DELETE',
		success : function(data) {
			var dlg = BootstrapDialog.show({
			    message: '已成功删除'
			});
			setTimeout(function(){
			    dlg.close();
			},1000);
			window.location.reload();
		}
	});
};

/**
 * 高亮表格选中行
 */
$('.data-row').click(function() {
	$('.data-row').removeClass('selected-row');
	$(this).addClass('selected-row');
});


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

