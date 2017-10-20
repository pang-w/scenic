$(function () {
    $("#navbar").load("components/navbar.html");
    $("#sidebar").load("components/sidebar.html");
    $("#footer").load("components/footer.html");
    $("#menuRoot").html('<li class="" id="menu1"><a href="admin.html"><i class="menu-icon fa fa-caret-right" id="icon1"></i>Abc</a><b class="arrow"></b></li>');
    showLoginUser();
   // loadMenu();
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
    support.ajax("a", {}, function (data) {
        $("#loginUsr").html(data);
    }, function () {
        layer.msg("注销失败,请稍后重试!");
    });
}
function logout() {
    support.ajax("user/loginout", {}, function (data) {
        location.href = "login.html";
    }, function () {
        layer.msg("注销失败,请稍后重试!");
    });
}
function loadMenu() {
        var menus = createMenuData();
        var html = '';
        if (menus && menus.length > 0) {
            for (var i = 1; i < menus.length; i++) {
                var menu = menus[i];
                html += parseMenu(menu);
            }
        }
        $("#menuRoot").html('<li class="" id="menu1"><a href="admin.html"><i class="menu-icon fa fa-caret-right" id="icon1"></i>Abc</a><b class="arrow"></b></li>');
      //  loadMenuIcon();
}
function createMenuData() {
	var menu = [];
	menu[1]={subMenus:[],"resId":"1",resUri:'admin.html',resName:"Abc"};
	menu[2]={subMenus:[],"resId":"2",resUri:'admin.html',resName:"Abc"};
	return menu;
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
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    var conPwd = $("#conPwd").val();
    if (!oldPwd) {
        layer.tips('原密码不能为空！', "#oldPwd");
        return;
    }
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
        "usrPwd": oldPwd,
        "usrNewPwd": newPwd
    };
    support.ajax("user/changePwd", param, function (data) {
        logout();
    });
}