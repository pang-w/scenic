$("#pagination").load("components/pagination.html", function() {
	$("#pageFirst").click(function() {
		search("1");
	});
	$("#pagePrevious").click(function() {
		var page = $("#pageIndex").html();
		search(parseInt(page) - 1);
	});
	$("#pageNext").click(function() {
		var page = $("#pageIndex").html();
		search(parseInt(page) + 1);
	});
	$("#pageLast").click(function() {
		var totalPage = $("#totalPage").html();
		search(totalPage);
	});
	$("#pageGo").click(function() {
		var page = $("#goPageIndex").val();
		var totalPage = $("#totalPage").html();
		if (page > 0 && page <= totalPage) {
			search(page);
		} else {
			alert("请输入正确的页数");
		}
	});
});
function loadPagination(pagObj) {
	if (pagObj.pageIndex == 1) {
		$("#pageFirst").attr("disabled", "disabled");
		$("#pagePrevious").attr("disabled", "disabled");
	} else {
		$("#pageFirst").removeAttr("disabled");
		$("#pagePrevious").removeAttr("disabled");
	}
	if (pagObj.pageIndex == pagObj.totalPage) {
		$("#pageNext").attr("disabled", "disabled");
		$("#pageLast").attr("disabled", "disabled");
	} else {
		$("#pageNext").removeAttr("disabled");
		$("#pageLast").removeAttr("disabled");
	}
	$("#totalPage").html(pagObj.totalPage);
	$("#pageSize").html(pagObj.pageSize);
	$("#totalCount").html(pagObj.totalCount);
	$("#pageIndex").html(pagObj.pageIndex);
	$("#pageIndexEnd").html(pagObj.totalPage);
	$("#goPageIndex").val("");
}
