jQuery(function ($) {
    $("#addSol").click(function () {
        add();
    });
});
function add() {
    var data = {
            "sysGroup": $("#sysGroup").val(),
            "isuCode": $("#isuCode").val(),
            "solStatus": $("#solStatus").val(),
            "maxTryTime": $("#maxTryTime").val(),
            "exeCron": $("#exeCron").val(),
            "exeType": $("#exeType").val(),
            "solLevel": $("#solLevel").val(),
            "solDes": $("#solDes").val(),
    };
    support.ajax("web/addSol", data, function (data) {
            layer.msg(data.respInfo.info);
    });
}