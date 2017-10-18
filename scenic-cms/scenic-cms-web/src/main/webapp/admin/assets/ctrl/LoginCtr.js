jQuery(function ($) {
    loadImage();
    $("#randimg").click(function () {
        loadImage();
        $("#verCode").val("");
    });
    $("#loginBtn").click(function () {
        login();
    });
});

function loadImage() {
    var num = Math.random();
    $("#randimg").attr("src", "../servlet/randCode?" + num);
}

function login() {
    var usrName = $("#usrLoginName").val();
    var usrPwd = $("#usrLoginPwd").val();
    var verCode = $("#verCode").val();
    // 将参数封装成json格式
    if (!usrName) {
        layer.tips('用户名不能为空！', "#usrLoginName");
        return;
    }
    if (!usrPwd) {
        layer.tips('密码不能为空！', "#usrLoginPwd");
        return;
    }
    if (!verCode) {
        layer.tips('验证码不能为空！', "#verCode");
        return;
    }
    var data = {
        "usrName": usrName,
        "usrPwd": usrPwd,
        "verCode": verCode
    };
    support.syncAjax("user/login", data, function (data) {
        if (data.respInfo.beforeUrl != null) {
            // 登录成功跳转到首页
            var beforeUrl = data.respInfo.beforeUrl;
            // 跳转路径
            location.href = beforeUrl;
        }
        if (data.respInfo.userModel != null) {
            // 登录成功跳转到首页
            var beforeUrl = data.respInfo.beforeUrl;
            // 跳转路径
            location.href = "./index.html";
        }
    }, function (data) {
        layer.msg(data.errorInfo.errorMessage);
        loadImage();
        $("#verCode").val("");
    });
}