jQuery(function ($) {
    $("#addSol").click(function () {
        add();
    });
});
function add() {
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
            "sendDateEnd": $("#sendDateEnd").val(),
            "reportDateEnd": $("#reportDateEnd").val(),
            "testType": $("#testType").val(),
            "description": $("#description").val()
    };
    support.ajax("pro/add", data, function (data) {
            layer.msg(data.msg);
    });
}