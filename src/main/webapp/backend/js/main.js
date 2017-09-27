/**
 * 约定：$开头表示选择器
 */

var DELETE_URL,
	$TABLE =$('#yiwuDatatable'),
	TABLE,
	CLOUMN_CREATE_TIME=column_index_create_time===undefined?0:column_index_create_time;
	
$(document).ready(function(){
	TABLE = $TABLE.DataTable(setting);
	//添加搜索提示
	$('.input-sm').attr('placeholder', search_hint);
	
});

/**
 * 关闭模态框自动清除数据
 */
$(".modal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
})

/**
 * 显示 删除模态框
 * @param url
 * @returns
 */
function showDeleteModal(url) {
	DELETE_URL = url;
	BootstrapDialog.confirm({
		title:"删除",
		message:"删除之后将不能恢复， 确认删除?",
		type:BootstrapDialog.TYPE_WARNING,
		closable:true,
		draggable:true,
		btnCancelLabel:"取消",
		btnOKLabel:"确定",
		btnOKClass: "btn-warning",
		size:BootstrapDialog.SIZE_SMALL,
		callback:function(result){
			if(result){
				doDeleteRequest();
			}
		}
	});
	
}

/**
 * 显示保存失败提示框
 * @param message
 * @returns
 */
function showSaveFailureModal(message){
	var dlg = BootstrapDialog.show({
	    message: message,
	    type:BootstrapDialog.TYPE_INFO,
	    title:'保存失败',
	    size : BootstrapDialog.SIZE_SMALL,
	    closable:true
	});
	
	/*setTimeout(function(){
	    dlg.close();
	},2000);*/
}


/**
 * 
 */
function flashDeleteSuccessModal(){
	var dlg = BootstrapDialog.show({
	    message: '已成功删除',
	    type:BootstrapDialog.TYPE_SUCCESS,
	    title:'提示',
	    size : BootstrapDialog.SIZE_SMALL
	});
	
	setTimeout(function(){
	    dlg.close();
		TABLE.draw(); //刷新表
	},1000);
}

/**
 * 显示删除失败消息提示框
 * @param message
 * @returns
 */
function showDeleteFailureModal(message){
	var dlg = BootstrapDialog.show({
	    message: message,
	    type:BootstrapDialog.TYPE_INFO,
	    title:'删除失败',
	    size : BootstrapDialog.SIZE_SMALL,
	    closable:true
	});
}

/**
 * 显示更新失败消息提示框
 * @param message
 * @returns
 */
function showUpdateFailureModal(message){
	var dlg = BootstrapDialog.show({
	    message: message,
	    type:BootstrapDialog.TYPE_INFO,
	    title:'更新失败',
	    size : BootstrapDialog.SIZE_SMALL,
	    closable:true
	});
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
			if(data.result)
				flashDeleteSuccessModal();
			else
				showDeleteFailureModal(data.error);
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

function translateGender(gender){
	switch (gender) {
	case "MALE":
		return "男";
	case "FEMALE":
		return "女";
	default:
		return "保密";
	}
}

/**
 * 格式化日期
 * @param time
 * @param format
 * @returns
 */
function formatDate(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
