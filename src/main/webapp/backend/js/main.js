/**
 * 约定：$开头表示选择器
 */

var DELETE_URL, $TABLE = $('#yiwuDatatable'),
	TABLE, CLOUMN_CREATE_TIME = (column_index_create_time === undefined) ? 0 :
	column_index_create_time;
var QINIU_UPLOAD_URL = "http://up-z2.qiniu.com";
var WEB_BASE_URL = "http://192.168.0.115:9090/"
var BASE_DELETE_FILE_URL = "http://192.168.0.115:9090/yiwu/system/qiniu/"

$(document).ready(function() {
	if($TABLE == undefined)
		return;;
	TABLE = $TABLE.DataTable(setting);
});

/**
 * 关闭模态框自动清除数据
 */
$(".modal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
})

/**
 * 
 * @param table
 * @returns
 */
function refreshDataTable(table) {
	if(table == null)
		TABLE.draw(false);
	table.draw(false);
}

/**
 * 显示 删除模态框
 * @param url
 * @param callback 删除成功后回调函数
 * @returns
 */
function showDeleteModal(url, callback) {
	BootstrapDialog.confirm({
		title: "删除",
		message: "删除之后将不能恢复， 确认删除?",
		type: BootstrapDialog.TYPE_WARNING,
		closable: true,
		draggable: true,
		btnCancelLabel: "取消",
		btnOKLabel: "确定",
		btnOKClass: "btn-warning",
		size: BootstrapDialog.SIZE_SMALL,
		callback: function(result) {
			if(result) {
				doDeleteRequest(url, callback);
			}
		}
	});

}

/**
 * 显示保存失败提示框
 * 
 * @param message
 * @returns
 */
function showSaveFailureModal(message) {
	var dlg = BootstrapDialog.show({
		message: message,
		type: BootstrapDialog.TYPE_INFO,
		title: '保存失败',
		size: BootstrapDialog.SIZE_SMALL,
		closable: true
	});

	/*
	 * setTimeout(function(){ dlg.close(); },2000);
	 */
}

/**
 * 
 */
function flashDeleteSuccessModal(callback) {
	var dlg = BootstrapDialog.show({
		message: '已成功删除',
		type: BootstrapDialog.TYPE_SUCCESS,
		title: '提示',
		size: BootstrapDialog.SIZE_SMALL
	});

	setTimeout(function() {
		dlg.close();
		if(callback != null)
			callback();
	}, 1000);
}

function flashSaveSuccessModal() {
	var dlg = BootstrapDialog.show({
		message: '保存成功',
		type: BootstrapDialog.TYPE_SUCCESS,
		title: '提示',
		size: BootstrapDialog.SIZE_SMALL
	});
	setTimeout(function() {
		dlg.close();
	}, 1000);
}

/**
 * 显示删除失败消息提示框
 * 
 * @param message
 * @returns
 */
function showDeleteFailureModal(message) {
	var dlg = BootstrapDialog.show({
		message: message,
		type: BootstrapDialog.TYPE_INFO,
		title: '删除失败',
		size: BootstrapDialog.SIZE_SMALL,
		closable: true
	});
}

/**
 * 闪现修改成功
 * 
 * @returns
 */
function flashUpdateSuccessModal(msg) {
	var dlg = BootstrapDialog.show({
		message: msg == null ? '修改成功' : msg,
		type: BootstrapDialog.TYPE_SUCCESS,
		title: '提示',
		size: BootstrapDialog.SIZE_SMALL
	});
	setTimeout(function() {
		dlg.close();
	}, 1000);
}

/**
 * 显示更新失败消息提示框
 * 
 * @param message
 * @returns
 */
function showUpdateFailureModal(message) {
	var dlg = BootstrapDialog.show({
		message: message,
		type: BootstrapDialog.TYPE_INFO,
		title: '更新失败',
		size: BootstrapDialog.SIZE_SMALL,
		closable: true,
		draggalbe: true,

	});
}

/**
 * 
 * @param {Object} token
 * @param {Object} fnconfirmcallback
 */
function showDeopzoneModel(token, fnconfirmcallback) {
	var messageHtml = '<div class="dropzone" id="dropzone"><div class="am-text-success dz-message">将文件拖拽到此处<br>或点此打开文件管理器选择文件</div></div>'
	var fileKey;
	var fileName;
	var dlg = BootstrapDialog.confirm({
		message: messageHtml,
		type: BootstrapDialog.TYPE_DEFAULT,
		title: '上传',
		size: BootstrapDialog.SIZE_SMALL,
		closable: true,
		draggable: true,
		btnCancelLabel: '取消',
		btnOKLabel: '确认',
		btnOKClass: 'btn-success',
		onshown: function(dialogRef) {
			var dropzone = new Dropzone('#dropzone', {
				url: QINIU_UPLOAD_URL,
				method: "POST",
				params: {
					"token": token
				},
				addRemoveLinks: true,
				dictRemoveLinks: "x",
				dictRemoveFile: "删除",
				dictMaxFilesExceeded: "已超出允许的最大上传文件数量",
				maxFiles: 1,
				filesizeBase: 1024,
				success: function(file, response, e) {
					fileKey = response.key;
					fileName = file.name;
				},
				init: function() {
					this.on("removedfile", function(file) {
						deleteQiniuFile(fileKey);
					});
				}
			});
		},
		callback: function(result) {
			if(result) {
				fnconfirmcallback(fileKey);
			} else {
				// 删除在云上的文件
				if(fileKey != null)
					deleteQiniuFile(fileKey);
			}
		}
	});

}

/**
 * 
 * @param {Object} token
 * @param {Object} saveUrl
 * @param {Object} confirmcallback
 */
function showQiniuDropzoneModal(token, saveUrl, confirmcallback) {
	var messageHtml = '<div class="dropzone" id="dropzone"><div class="am-text-success dz-message">将文件拖拽到此处<br>或点此打开文件管理器选择文件</div></div>'
	var fileKey;
	var fileName;
	var dlg = BootstrapDialog.confirm({
		message: messageHtml,
		type: BootstrapDialog.TYPE_DEFAULT,
		title: '上传',
		size: BootstrapDialog.SIZE_SMALL,
		closable: true,
		draggable: true,
		btnCancelLabel: '取消',
		btnOKLabel: '确认',
		btnOKClass: 'btn-success',
		onshown: function(dialogRef) {
			var dropzone = new Dropzone('#dropzone', {
				url: QINIU_UPLOAD_URL,
				method: "POST",
				params: {
					"token": token
				},
				addRemoveLinks: true,
				dictRemoveLinks: "x",
				dictRemoveFile: "删除",
				dictMaxFilesExceeded: "已超出允许的最大上传文件数量",
				maxFiles: 1,
				filesizeBase: 1024,
				success: function(file, response, e) {
					fileKey = response.key;
					fileName = file.name;
				},
				init: function() {
					this.on("removedfile", function(file) {
						deleteQiniuFile(fileKey);
					});
				}
			});
		},
		callback: function(result) {
			if(result) {
				// 保存文件
				$.ajax({
					type: "POST",
					url: saveUrl,
					async: true,
					data: {
						"_method": "PUT",
						"fileKey": fileKey,
						"fileName": fileName
					},
					success: function(result) {
						if(result) {
							console.log("result data is " + result.data);
							confirmcallback(result.data);
						} else {
							showSaveFailureModal(result.msg);
						}
					}
				});
			} else {
				// 删除在云上的文件
				if(fileKey != null)
					deleteQiniuFile(fileKey);
			}
		}
	});
}

function deleteQiniuFile(fileKey) {
	$.ajax({
		type: "delete",
		url: BASE_DELETE_FILE_URL + fileKey,
		async: true,
		success: function(data) {
			console.log("删除成功");
		}
	});
}

/**
 * 发送删除请求
 * 
 * @param url
 * @returns
 */
function doDeleteRequest(url, callback) {
	$.ajax({
		url: url,
		type: 'DELETE',
		success: function(data) {
			if(data.result) {
				flashDeleteSuccessModal(callback);
			} else {
				showDeleteFailureModal(data.msg);
			}
		}
	});

};

/**
 * 刷新当前页面
 * 
 * @returns
 */
function refreshCurrentPage() {
	location.reload();
}

/**
 * 转换数据状态
 * 
 * @param dataStatus
 * @returns
 */
function translateDataStatus(dataStatus) {
	switch(dataStatus) {
		case "NORMAL":
			return "正常";
		case "FORBID":
			return "禁用";
		default:
			return "删除";
	}
}

function translateGender(gender) {
	switch(gender) {
		case "MALE":
			return "男";
		case "FEMALE":
			return "女";
		default:
			return "保密";
	}
}

function translateCourseType(courseType) {
	switch(courseType) {
		case "CLOSED":
			return "封闭式";
		case "OPENED":
			return "开放式";
		case "PRIVATE":
			return "私教课"
		default:
			return "";
	}
}

/**
 * 
 * @param {Object}
 *            subCourseType
 * @return {String}
 */
function translateSubCourseType(subCourseType) {
	switch(subCourseType) {
		case "OPEN_A":
			return "开放式A"
		case "OPEN_B":
			return "开放式B"
		case "OPEN_CJ":
			return "少儿集训初级"
		case "OPEN_ZJ":
			return "少儿集训中级"
		case "CLOSED":
			return "封闭式"
		case "PRIVATE":
			return "私教课"
		default:
			return "";
	}
}

/**
 * 
 * @param contractStatus
 * @returns
 */
function translateContractStatus(contractStatus) {
	switch(contractStatus) {
		case "UN_PAYED":
			return "待付款";
		case "UN_VERIFIED":
			return "未确认";
		case "VERIFIED":
			return "已确认";
		case "UN_CHECKED":
			return "未审核";
		case "UN_CHECKED":
			return "未开始";
		case "CHECKED":
			return "已审核";
		case "UN_PASSED":
			return "审核未通过";
		case "LEFT":
			return "请假";
		case "RETURNED_PREMIUM":
			return "退费";
		case "FORBIDDEN":
			return "禁用";
		case "EXPIRED":
			return "到期";

		default:
			return "";
	}
}

/**
 * 转换产品卡类型
 * 
 * @param type
 * @returns
 */
function translateProductCardType(type) {
	switch(type) {
		case "ADULT_MEMBER_CARD":
			return "成人会员卡";
		case "CHILDREN_MEMBER_CARD":
			return "少儿会员卡";
		case "GROUP_MEMEBER_CARD":
			return "团体会员卡";
		case "DANCE_SERVICE_CARD":
			return "舞蹈服务卡";
		case "COACH_TRAIN_CARD":
			return "教师培训卡";
		case "EXAM_TRAIN_CARD":
			return "考辅培训卡";
		case "CERTIFICATION_FEE":
			return "认证费";
		case "INCIDENTALS":
			return "杂费";
		case "GOODS":
			return "商品";
		case "DEPOSIT":
			return "定金";

		default:
			return "";
	}
}

/**
 * 格式化日期
 * 
 * @param time
 * @param format
 * @returns
 */
function formatDate(time, format) {
	var t = new Date(time);
	var tf = function(i) {
		return(i < 10 ? '0' : '') + i
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
		switch(a) {
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