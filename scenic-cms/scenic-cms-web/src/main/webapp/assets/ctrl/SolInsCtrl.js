jQuery(function ($) {
    loadUrlParam();
    $("#btnSearch").click(function () {
        search("1");
    });
});
function search(page) {
    var data = {
    		"sno": $("#sno").val(),
    		"ino": $("#ino").val(),
    		"isuNum": $("#isuNum").val(),
            "sysGroup": $("#sysGroup").val(),
            "isuCode": $("#isuCode").val(),
            "solStatus": $("#solStatus").val(),
            "solLevel": $("#solLevel").val(),
            "solDes": $("#solDes").val(),
            "pageSize": "10",
            "pageIndex": page
    };
    support.ajax("procession/solutionIns", data, function (data) {
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
    if (param.isuNum) {
        $("#isuNum").val(param.isuNum);
        flag = true;
    }
    if(flag) {
        search("1");
    }
}

function manualExeSol(ino,solStatus) {
	var data = {
    		"ino": ino,
    		"solStatus": solStatus
    };
    support.ajax("web/manualExeSol", data, function (data) {
            layer.msg(data.respInfo.info);
            search("1");
    });
}
function goIssues(isuNum) {
    window.location = "issues.html?isuNum=" + isuNum;
}
