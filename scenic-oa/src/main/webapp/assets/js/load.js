$(function () {
    $("#navbar").load("components/navbar.html");
    $("#sidebar").load("components/sidebar.html");
    $("#footer").load("components/footer.html");
    showLoginUser();
  //  loadMenu();
    $(document).on('click', '.user-menu a[data-target]', function (e) {
        e.preventDefault();
        var target = $(this).data('target');
        layer.open({
            title: ' <i class="ace-icon fa fa-key"></i>修改密码',
            type: 1,
            shadeClose: true,
            area: ['300px', 'auto'],
            content: $(target)
        });
    });
});
function showLoginUser() {
    support.ajax("user/loginUser", {}, function (data) {
        $("#loginUsr").html(data.data.username);
    }, function () {
        layer.msg(data.msg);
    });
}
function logout() {
    support.ajax("user/logout", {}, function (data) {
        location.href = "login.html";
    }, function () {
        layer.msg("注销失败,请稍后重试!");
    });
}
function loadMenu() {
    support.ajax("user/loadMenu", {}, function (data) {
        var menus = data.respInfo.menus;
        var html = '';
        if (menus && menus.length > 0) {
            for (var i = 0; i < menus.length; i++) {
                var menu = menus[i];
                html += parseMenu(menu);
            }
        }
        $("#menuRoot").html(html);
        loadMenuIcon();
    }, function () {
        layer.msg("加载菜单失败,请稍后重试!");
    });
}

function parseMenu(menu) {
    var subMenus = menu.subMenus;
    var html = '';
    html += '<li class="" id="menu' + menu.resId + '">';
    if (subMenus.length > 0) {
        html += '<a href="#" class="dropdown-toggle">';
    } else {
        html += '<a href="' + menu.resUri + '">';
    }
    if (menu.parentId == 0) {
        html += '<i class="menu-icon" id="icon' + menu.resId + '"></i>';
        html += '<span class="menu-text">' + menu.resName + '</span>';
    } else {
        html += '<i class="menu-icon fa fa-caret-right" id="icon' + menu.resId + '"></i>';
        html += menu.resName;
    }
    html += '</a>';
    html += '<b class="arrow"></b>';
    if (subMenus.length > 0) {
        html += '<ul class="submenu">';
        for (var i = 0; i < subMenus.length; i++) {
            html += parseMenu(subMenus[i]);
        }
        html += '</ul>';
    }
    html += '</li>';
    return html;
}
function loadMenuIcon() {
    $("#icon1").attr("class", "menu-icon fa fa-tachometer");
    $("#icon2").attr("class", "menu-icon fa fa-file");
    $("#icon3").attr("class", "menu-icon fa fa-search");
    $("#icon9").attr("class", "menu-icon fa fa-cogs");
    $("#icon11").attr("class", "menu-icon fa fa-gavel");
    $("#icon14").attr("class", "menu-icon fa fa-refresh");
    
    if (typeof(activeMenu) == "undefined") {
        $("#menu1").attr("class", "active");
    } else {
        for (var i = 0; i < activeMenu.length; i++) {
            if (i != activeMenu.length - 1) {
                $("#menu" + activeMenu[i]).attr("class", "active open");
            } else {
                $("#menu" + activeMenu[i]).attr("class", "active");
            }
        }
    }
}
function chnagePwd() {
    var newPwd = $("#newPwd").val();
    var conPwd = $("#conPwd").val();
    if (!newPwd) {
        layer.tips('新密码不能为空！', "#newPwd");
        return;
    }
    if (!conPwd) {
        layer.tips('确认密码不能为空！', "#conPwd");
        return;
    }
    if (newPwd != conPwd) {
        layer.tips('确认密码与原密码不一致！', "#conPwd");
        return;
    }
    var param = {
        "password": newPwd,
    };
    support.ajax("user/changePwd", param, function (data) {
        logout();
    });
}