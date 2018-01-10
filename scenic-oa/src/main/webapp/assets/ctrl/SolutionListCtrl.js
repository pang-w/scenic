jQuery(function ($) {
    loadUrlParam();
    $("#btnSearch").click(function () {
        search("1");
    });
    $("#createExcel").click(function () {
    	createExcel();
    });
    $("#dowloadExcel").click(function () {
    	location.href="http://img.iukiss.com/oa/zzuli/data.xlsx"
    });
    $("#btnChangeSol").click(function () {
    	var data = {
    			"caseId": $("#caseId").text(),
    			"name": $("#name").val(),
                "sex": $("#sex").val(),
                "age": $("#age").val(),
                "testResault": $("#testResault").val(),
                "doctor": $("#doctor").val(),
                "department": $("#department").val(),
                "testType": $("#testType").val(),
                "description": $("#description").val(),
        };
        support.ajax("pro/update", data, function (data) {
                layer.msg(data.errorInfo.errorMessage);
                search("1");
        });
    });
});
function createExcel() {
    var data = {
    		"name": $("#name").val(),
            "sex": $("#sex").val(),
            "age": $("#age").val(),
            "testResault": $("#testResault").val(),
            "caseId": $("#caseId").val(),
            "doctor": $("#doctor").val(),
            "department": $("#department").val(),
            "sendDateStart": $("#sendDateStart").val(),
            "sendDateEnd": $("#sendDateEnd").val(),
            "reportDateStart": $("#reportDateStart").val(),
            "reportDateEnd": $("#reportDateEnd").val(),
            "testType": $("#testType").val(),
            "description": $("#description").val(),
    };
    support.ajax("pro/createExcel", data, function (rs) {
    	layer.msg(rs.msg);
    });
}
function search(page) {
    var data = {
    		"name": $("#name").val(),
            "sex": $("#sex").val(),
            "age": $("#age").val(),
            "testResault": $("#testResault").val(),
            "caseId": $("#caseId").val(),
            "doctor": $("#doctor").val(),
            "department": $("#department").val(),
            "sendDateStart": $("#sendDateStart").val(),
            "sendDateEnd": $("#sendDateEnd").val(),
            "reportDateStart": $("#reportDateStart").val(),
            "reportDateEnd": $("#reportDateEnd").val(),
            "testType": $("#testType").val(),
            "description": $("#description").val(),
            "pageIndex": page
    };
    support.ajax("pro/list", data, function (rs) {
        if (rs.data.dataList==null) {
            $("#dataInfo").html("");
            $("#pagination").attr("style", "display: none");
            layer.msg("未找到符合条件的数据");
            return;
        }
        $("#pagination").attr("style", "");
        $("#dataInfo").html($("#dataList").render(rs.data.dataList));
        loadPagination(rs.data);
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

function removeSol(caseId) {
	var data = {
    		"caseId": caseId
    };
    support.ajax("pro/remove", data, function (data) {
            layer.msg(data.msg);
            search($("#pageIndex").html());
    });
}
function doChangePro(page){
	var data = {
			"caseId": $("#editcaseId").text(),
			"name": $("#editname").val(),
            "sex": $("#editsex").val(),
            "age": $("#editage").val(),
            "testResault": $("#edittestResault").val(),
            "doctor": $("#editdoctor").val(),
            "department": $("#editdepartment").val(),
            "testType": $("#edittestType").val(),
            "description": $("#editdescription").val(),
            "pageIndex": page
    };
    support.ajax("pro/update", data, function (data) {
            layer.msg(data.msg);
            search($("#pageIndex").html());
    });
};
function changeSol(id
		,name
		,sex
		,age
		,testResault
		,caseId
		,doctor
		,department
		,sendDate
		,reportDate
		,testType
		,description) {
    var data = {
    		"id":id,
    		"name":name,
    		"sex":sex,
    		"age":age,
    		"testResault":testResault,
    		"caseId":caseId,
    		"doctor":doctor,
    		"department":department,
    		"sendDate":sendDate,
    		"reportDate":reportDate,
    		"testType":testType,
    		"description":description
    } 
	 $("#changeInfo").html($("#changeSol").render(data));
}

