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
    		"name": $("#name").val(),
            "sex": $("#sex").val(),
            "age": $("#age").val(),
            "testResault": $("#testResault").val(),
            "caseId": $("#caseId").val(),
            "doctor": $("#doctor").val(),
            "department": $("#department").val(),
            "sendDate": $("#sendDate").val(),
            "reportDate": $("#reportDate").val(),
            "testType": $("#testType").val(),
            "description": $("#description").val(),
            "pageSize": "10",
            "pageIndex": page
    };
    support.ajax("pro/list", data, function (data) {
        if (!data.data) {
            $("#dataInfo").html("");
            $("#pagination").attr("style", "display: none");
            layer.msg("未找到符合条件的数据");
            return;
        }
        $("#pagination").attr("style", "");
        $("#dataInfo").html($("#dataList").render(data.data));
      //  loadPagination(info);
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

