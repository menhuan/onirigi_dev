$(function() {
	common.showMessage($("#message").val());
});

function search(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").attr("method","GET");
	$("#mainForm").submit();
}

function remove(id) {
	if(confirm("确定要删除这条广告吗？")) {
		$("#id").val(id);
		$("#mainForm").attr("action", "/ad/remove");
		$("#mainForm").submit();
	}
}

function modifyInit(id) {
	$("#id").val(id);
	$("#mainForm").attr("action", "/ad/modifyInit");
	$("#mainForm").submit();
}

function add() {
	window.location.href = '/ad/addInit';
}