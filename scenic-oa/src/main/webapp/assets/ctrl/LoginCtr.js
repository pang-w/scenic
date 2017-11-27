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
   /* if (!verCode) {
        layer.tips('验证码不能为空！', "#verCode");
        return;
    }*/
    var data = {
        "username": usrName,
        "password": usrPwd,
        "verCode": verCode
    };
    support.syncAjax("user/login", data, function (rs) {
        if (rs.status == "0000") {
        	 location.href = "./index.html";
        }else{
        	layer.msg(rs.msg);
        }

    }, function (rs) {
        layer.msg(rs.msg);
        loadImage();
        $("#verCode").val("");
    });
}