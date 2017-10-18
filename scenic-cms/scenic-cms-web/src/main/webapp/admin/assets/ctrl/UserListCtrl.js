var addUserBoxIndex = -1;
jQuery(function ($) {
    $("#btnSearch").click(function () {
        search("1");
    });
    $("#btnAddUser").click(function () {
        addUserBoxIndex = layer.open({
            title: ' <i class="ace-icon fa fa-user"></i>添加用户',
            type: 1,
            shadeClose: true,
            area: ['300px', 'auto'],
            content: $("#add-user-box")
        });
    });
});
function search(page) {
    var data = {
        "usrName": $("#usrName").val(),
        "pageSize": "10",
        "pageIndex": page
    };
    support.ajax("user/list", data, function (data) {
        var info = data.respInfo.users;
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
function addUser() {
    var newUsrName = $("#newUsrName").val();
    var newRealName = $("#newRealName").val();
    if (!newUsrName) {
        layer.tips('登录名不能为空！', "#newUsrName");
        return;
    }
    var param = {
        "usrName": newUsrName,
        "realName": newRealName
    };
    support.ajax("user/addUser", param, function (data) {
        $("#usrName").val(newUsrName);
        if(addUserBoxIndex != -1) {
            layer.close(addUserBoxIndex);
        }
        $("#newUsrName").val("");
        $("#newRealName").val("");
        search(1);
    });
}
function deleteUser(userId, userName) {
    layer.confirm("确认删除用户【" + userName + "】么？", {btn :['删除', '关闭']}, function () {
        support.ajax("user/deleteUser", {"usrId" : userId}, function (data) {
            layer.msg("删除成功");
            var pageIndex = $("#pageIndex").html();
            search(pageIndex);
        });
    });
}