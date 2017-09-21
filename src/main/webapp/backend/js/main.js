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

function selectRow() {
	$(this).addClass(".Acti");
}