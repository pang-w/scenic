jQuery(function ($) {
    loadUrlParam();
    $("#btnSearch").click(function () {
        search("1");
    });
});
function search(page) {
    var data = {
        "cno": $("#cno").val(),
        "isuNum": $("#isuNum").val(),
        "status": $("#status").val(),
        "pageSize": "10",
        "pageIndex": page
    };
    support.ajax("procession/issuse", data, function (data) {
        var info = data.respInfo.info;
        if (!info.totalPage) {
            $("#dataInfo").html("");
            $("#pagination").attr("style", "display: none");
            layer.msg("未找到符合条件的数据");
            return;
        }
        $("#pagination").attr("style", "");
        $("#dataInfo").html($("#dataList").render(info.dataList));
        loadPagination(info);
    });
}

function loadUrlParam() {
    var param = urlParam();
    var flag = false;
    if (param.cno) {
        $("#cno").val(param.cno);
        flag = true;
    }
    if (param.isuNum) {
        $("#isuNum").val(param.isuNum);
        flag = true;
    }
    if(flag) {
        search("1");
    }
}

function goColleciton(cno) {
    window.location = "collection.html?cno=" + cno;
}
function goSolutionIns(isuNum) {
        window.location = "solutionIns.html?isuNum=" + isuNum;
}