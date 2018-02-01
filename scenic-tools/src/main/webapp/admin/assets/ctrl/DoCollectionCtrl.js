jQuery(function ($) {
    loadUrlParam();
    $("#sendColletion").click(function () {
        send("1");
    });
});
function send(page) {
    var data = {
    		"startDate": $("#startDate").val(),
    		"endDate": $("#endDate").val()
    };
    support.ajax("web/sendColletion", data, function (data) {
            layer.msg(data.respInfo.info);
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