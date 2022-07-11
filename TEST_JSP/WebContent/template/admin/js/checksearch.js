$(document).ready(function() {
	$("#sm-s").click(function() {
		var data = document.getElementById("keyword");
		if (data.value != "") {
			return true;
		}
		alert("Bạn chưa nhập từ khóa tìm kiếm");
		return false;
	});
});