$(document).ready(function () {
	$("#formCat").validate({
		rules: {
			"name": {
				required: true,
			}
		},
		messages: {
			"name": {
				required: "Nhập vô bạn ê",
			}
		}
	});
});
