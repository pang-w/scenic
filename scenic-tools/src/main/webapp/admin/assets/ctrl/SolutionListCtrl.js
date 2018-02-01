jQuery(function ($) {
    loadUrlParam();
    $("#btnSearch").click(function () {
        search("1");
    });
    $("#btnChangeSol").click(function () {
    	 doChangeSol();
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
            "excType": $("#excType").val(),
            "solLevel": $("#solLevel").val(),
            "solDes": $("#solDes").val(),
            "pageSize": "10",
            "pageIndex": page
    };
    support.ajax("procession/solution", data, function (data) {
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
function doChangeSol() {
    var data = {
    		"sno": $("#ch_sno").val(),
            "sysGroup": $("#ch_sysGroup").val(),
            "isuCode": $("#ch_isuCode").val(),
            "solStatus": $("#ch_solStatus").val(),
            "maxTryTime": $("#ch_maxTryTime").val(),
            "exeCron": $("#ch_exeCron").val(),
            "exeType": $("#ch_exeType").val(),
            "solLevel": $("#ch_solLevel").val(),
            "solDes": $("#ch_solDes").val(),
    };
    support.ajax("procession/updateSol", data, function (data) {
            layer.msg(data.errorInfo.errorMessage);
            search("1");
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

function removeSol(sno) {
	var data = {
    		"sno": sno
    };
    support.ajax("web/removeSol", data, function (data) {
            layer.msg(data.respInfo.info);
            search("1")
    });
}
function changeSol(sno,solLevel,sysGroup,isuCode,maxTryTime,exeCron,exeType,solStatus,solDes) {
	var data = {
			"sno":sno,
			"solLevel":solLevel,
			"sysGroup":sysGroup,
			"isuCode":isuCode,
			"maxTryTime":maxTryTime,
			"exeCron":exeCron,
			"exeType":exeType,
			"solStatus":solStatus,
			"solDes":solDes
	};
	 $("#changeInfo").html($("#changeSol").render(data));
}

