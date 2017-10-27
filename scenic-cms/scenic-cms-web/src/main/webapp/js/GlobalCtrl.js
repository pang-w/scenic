var support = {
    ajax: function (url, data, callback, errCallback, beforeSend) {
        var settings = {
            url: "../action/" + url,
            contentType: "application/json;charset=UTF-8",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            beforeSend: function () {
                $('.loadingmask').show();
            },
            success: function (data) {
                $('.loadingmask').hide();
                if (data.info.errorCode == "0000") {
                    if (callback && typeof callback == "function") {
                        callback(data);
                    }
                } else if (data.info.errorCode == "9998") {
                    location.href = "login.html";
                } else {
                    if (errCallback && typeof errCallback == "function") {
                        errCallback(data);
                    } else {
                        layer.msg(data.errorInfo.errorMessage);
                    }
                }
            }
        };
        $.ajax(settings);
    },

    syncAjax: function (url, data, callback, errCallback, beforeSend) {
        var settings = {
            url: "../action/" + url,
            contentType: "application/json;charset=UTF-8",
            type: "POST",
            data: JSON.stringify(data),
            async: false,
            dataType: "json",
            beforeSend: beforeSend,
            success: function (data) {
                if (data.errorInfo.errorCode == "0000") {
                    if (callback && typeof callback == "function") {
                        callback(data);
                    }
                } else if (data.errorInfo.errorCode == "9998") {
                    location.href = "login.html";
                } else {
                    if (errCallback && typeof errCallback == "function") {
                        errCallback(data);
                    } else {
                        layer.msg(data.errorInfo.errorMessage);
                    }
                }
            }
        };

        $.ajax(settings);
    },

    syncFileUploadAjax: function (url, data, callback, errCallback, beforeSend) {
        var settings = {
            type: 'POST',
            url: "../action/" + url,
            dataType: 'json',
            data: data,
            contentType: false,
            processData: false,
            timeout: 300000, // 超时设置5分钟
            beforeSend: function () {
                $('.loadingmask').show();
            },
            success: function (data) {
                $('.loadingmask').hide();
                if (data.errorInfo.errorCode == "0000") {
                    if (callback && typeof callback == "function") {
                        callback(data);
                    }
                } else if (data.errorInfo.errorCode == "9998") {
                    location.href = "login.html";
                } else {
                    if (errCallback && typeof errCallback == "function") {
                        errCallback(data);
                    } else {
                        layer.msg(data.errorInfo.errorMessage);
                    }
                }
            }
        };

        $.ajax(settings);
    },

    fileUploadAjax: function (url, filesId, data, callback, errCallback,
                              beforeSend) {
        $.ajaxFileUpload({
            url: "../action/" + url,
            secureuri: false,
            fileElementId: filesId,
            dataType: 'json',
            data: data,
            success: function (data, status) {
                if (data.errorInfo.errorCode == "0000") {
                    if (callback && typeof callback == "function") {
                        callback(data);
                    }
                } else if (data.errorInfo.errorCode == "9998") {
                    location.href = "login.html";
                } else {
                    if (errCallback && typeof errCallback == "function") {
                        errCallback(data);
                    } else {
                        layer.msg(data.errorInfo.errorMessage);
                    }
                }
            }
        });
    }
};
function urlParam() {
    var result = {};
    var url = location.search;
    url = decodeURI(url);
    if (url.indexOf("?") != -1) {
        var str = url.substr(url.indexOf("?") + 1);// 去掉?号
        var params = str.split("&");
        for (var i = 0; i < params.length; i++) {
            var kv = params[i].split("=");
            var key = kv[0];
            var value = kv[1];
            result[key] = value;
        }
    }
    return result;
}
