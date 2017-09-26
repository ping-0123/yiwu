/**
 * 
 */

$(".modal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
})

function doDelete(url) {
	$('.delete-promt').modal('show');
	$('.delete-confirm').click(function() {
		deleteRequest(url);
	});
}

function deleteRequest(url) {
	$.ajax({
		url : url,
		type : 'DELETE',
		success : function(data) {
			window.location.reload();
		}
	});
};

$('.data-row').click(function() {
	$('.data-row').removeClass('selected-row');
	$(this).addClass('selected-row');
});

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

