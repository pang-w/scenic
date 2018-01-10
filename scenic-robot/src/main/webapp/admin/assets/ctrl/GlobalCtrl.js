$(function () {
    /** jsrender converts * */
    $.views.converters("showTrdType", function (val) {
        if (val == 'CO') {
            return '<i class="fa fa-flag blue bigger-110" style="width:50px">充值</i>';
        } else if (val == 'WD') {
            return '<i class="fa fa-flag purple bigger-110" style="width:50px">提现</i>';
        } else if (val == 'RF') {
            return '<i class="fa fa-flag red bigger-110" style="width:50px">退款</i>';
        } else if (val == 'RP') {
            return '<i class="fa fa-flag green bigger-110" style="width:50px">回款</i>';
        } else if (val == 'IV') {
            return '<i class="fa fa-flag grey bigger-110" style="width:50px">投资</i>';
        } else if (val == 'BN') {
            return '<i class="fa fa-flag pink bigger-110" style="width:50px">红包</i>';
        }
        return val;
    });
    /** jsrender converts * */
    $.views.converters("showTrdStatus", function (val) {
        if (val == '0') {
            return '<span class="label label-white middle">处理中</span>';
        } else if (val == '1') {
            return '<span class="label label-info label-white middle">已发送</span>';
        } else if (val == '8') {
            return '<span class="label label-success label-white middle">成功</span>';
        } else if (val == '9') {
            return '<span class="label label-danger label-white middle">失败</span>';
        }
        return val;
    });
    /** jsrender converts * */
    $.views.converters("showAccTrdStatus", function (val) {
        if (val == '0') {
            return '<span class="label">未记账</span>';
        } else if (val == '1') {
            return '<span class="label label-warning">已记账</span>';
        }
        return val;
    });
    /** jsrender converts * */
    $.views.converters("showChkStatus", function (val) {
        if (val == '0') {
            return '<span class="label label-danger arrowed-in">未检查</span>';
        } else if (val == '8') {
            return '<span class="label label-success arrowed">成功</span>';
        } else if (val == '9') {
            return '<span class="label label-danger arrowed-in">失败</span>';
        }
        return val;
    });
    /** jsrender converts * */
    $.views.converters("showSolStatus", function (val) {
        if (val == '0') {
            return '<span class="label label-danger arrowed-in">未检查</span>';
        } else if (val == '8') {
            return '<span class="label label-success arrowed">成功</span>';
        } else if (val == '9') {
            return '<span class="label label-danger arrowed-in">失败</span>';
        }
        return val;
    });
    /** jsrender converts * */
    $.views.converters("showPnrChlStatus", function (val) {
        if (val == 'S') {
            return '<span class="label label-success arrowed">成功</span>';
        } else if (val == 'F') {
            return '<span class="label label-danger arrowed-in">失败</span>';
        }
        return val;
    });
    /** jsrender converts * */
    $.views.converters("showSolutionStatus", function (val) {
        if (val == '1') {
            return '<span class="label label-danger label-white middle">需要修复</span>';
        } else if (val == '2') {
            return '<span class="label label-success label-white middle">不需要修复</span>';
        } else if (val == '0') {
            return '<span class="label label-white middle">方案无效</span>';
        }
        return val;
    });

    /** jsrender converts * */
    $.views.converters("dateFormat", function (val) {
        if (val) {
            return val.replace(/-/g, "/");
        }

        return val;
    });

    $.views.converters("rateFormat", function (val) {
        if (val) {
            var num = val * 100;
            return num.toFixed(2) + "%";
        }
        return val;
    });

    $.views.converters("amountFormat", function (val) {
        return support.formatAmount(val);
    });

    $.views.converters("formatString", function (val) {
        return support.stringFormat(val);
    });

    $.views.converters("formatIntro", function (val) {
        return support.introFormat(val);
    });

    $.views.converters("formatLean", function (val) {
        return support.leanFormat(val);
    });

    $.views.converters("formatTime", function (val) {
        return support.timeFormat(val);
    });

    $.views.converters("formatName", function (val) {
        return support.nameFormat(val);
    });

    /** watch extends * */
    $.fn.watch = function (callback) {
        return this.each(function () {
            $.data(this, 'originVal', $(this).val());

            $(this).on('keyup blur', function () {
                var originVal = $.data(this, 'originVal');
                if (originVal !== $(this).val()) {
                    callback($(this));
                    $.data(this, 'originVal', $(this).val());
                }
            });
        });
    };

    /** amount extends * */
    $.fn.amountVal = function () {
        var val = $(this).val();

        if (val) {
            return val.toString().replace(/,/g, "");
        }
        return NaN;
    };

    /** init validate objects * */
    validate.initValidateComp($("body"));

    /** disable copying and pasting on password control * */
    $("input[type='password']").each(function (i, obj) {
        $(obj).on("paste", function () {
            return false;
        });
    });

    $("input[type='amount']").watch(function (obj) {
        obj.val(support.typingFormatAmount($(obj).val()));
    });

});

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
    },

    typingFormatPositive: function (positive) {
        if (positive) {
            positive = positive.toString().replace(/[^1-9|\.]/g, '');
        }
        return positive;
    },

    typingFormatAmount: function (amount) {
        if (amount == null) {
            return "0.00";
        }
        amount = amount.toString().replace(/[^0-9|\.]/g, '');
        var suffix = "";
        var replaceStr = "";
        var i = amount.indexOf(".");
        if (i != -1) {
            replaceStr = amount.substring(i, amount.length);
            suffix = "." + replaceStr.replace(/[.]/g, "").substring(0, 2);
            amount = amount.replace(replaceStr, "");
        }

        amount = amount.toString().replace(/\$|\,/g, '');
        if (!amount || isNaN(amount)) {
            return null;
        }

        var sign = (amount == (amount = Math.abs(amount)));
        amount = Math.floor(amount * 100 + 0.50000000001);
        amount = Math.floor(amount / 100).toString();
        for (var i = 0; i < Math.floor((amount.length - (1 + i)) / 3); i++)
            amount = amount.substring(0, amount.length - (4 * i + 3)) + ','
                + amount.substring(amount.length - (4 * i + 3));
        return ((sign) ? '' : '-') + amount + suffix;
    },

    formatAmount: function (amount) {
        amount = support.typingFormatAmount(amount);
        if (amount.indexOf(".") < 0) {
            amount += ".00";
        }
        return amount;
    },
    stringFormat: function (objInfo) {
        if (objInfo && objInfo.length > 8) {
            return objInfo.substring(0, 8) + "...";
        }

        return objInfo;
    },
    introFormat: function (objInfo) {
        if (objInfo && objInfo.length > 33) {
            return objInfo.substring(0, 33) + "...";
        }
        return objInfo;

    },
    leanFormat: function (objInfo) {
        if (objInfo && objInfo.length > 22) {
            return objInfo.substring(0, 22) + "...";
        }
        return objInfo;

    },
    timeFormat: function (objInfo) {
        if (objInfo.length > 10) {
            return objInfo.substring(0, 10);
        } else {
            return objInfo;
        }
    },
    nameFormat: function (objInfo) {
        if (objInfo) {
            return objInfo + "**";
        } else {
            return "--";
        }
    },
    defaultTime: function (beginTime, endTime) {
        var start = new Date();
        var year = start.getFullYear();
        var month = start.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var sTime = year + "-" + month + "-01 00:00:00";
        var end = new Date(year, month, 0);
        var eTime = year + "-" + month + "-" + end.getDate() + " 23:59:59";

        eval("query." + beginTime + " = sTime;");
        eval("query." + endTime + " = eTime;");

        $(".start_time").val(sTime);
        $(".end_time").val(eTime);

        return
    },
    defaultDate: function (beginDate, endDate) {
        var start = new Date();
        var year = start.getFullYear();
        var month = start.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var sTime = year + "-" + month + "-01";
        var end = new Date(year, month, 0);
        var eTime = year + "-" + month + "-" + end.getDate();

        eval("query." + beginDate + " = sTime;");
        eval("query." + endDate + " = eTime;");

        $(".start_time").val(sTime);
        $(".end_time").val(eTime);

        return
    },
    loadUrlParm: function (parmName, keyName) {
        var title = keyName == null ? "(^|&)" : "(&" + keyName + "=)";
        var reg = new RegExp(title + parmName + "=([^&]*)(&|$)");
        var r = decodeURIComponent(decodeURIComponent(window.location.search)).substr(1).match(reg);
        if (r) {
            return unescape(r[2]);
        }
        return null;
    }
};

var validate = {
    validateType: {
        required: "required",
        minlength: "minlength",
        pattern: "pattern",
        email: "email",
        chinese: "chinese",
        mobile: "mobile",
        bankCardNo: "bankCardNo",
        idNo: "idNo",
        periodPattern: "periodPattern"
    },

    initValidateComp: function (container) {
        /** validate objects * */
        $("*[validatePass]").hide();
        $("*[validateAlert]").hide();

        $(container).find("form").bind("submit", function () {
            $(this).find("*[validate]").each(function (i, obj) {
                validate.execute($(obj));
            });
            if ($(this).find("*[fail]").size() > 0) {
                return false;
            }

            var submitFunc = $(this).attr("submit");
            if (submitFunc) {
                var res = eval(submitFunc + "()");
                return res;
            } else {
                return true;
            }
        });

        $(container).find("input[type='mobile']").each(function (i, obj) {
            validate.bind(obj, validate.validateType.mobile);
        });

        $(container).find("input[type='chinese']").each(function (i, obj) {
            validate.bind(obj, validate.validateType.chinese);
        });

        $(container).find("input[type='email']").each(function (i, obj) {
            validate.bind(obj, validate.validateType.email);
        });

        $(container).find("input[type='idNo']").each(function (i, obj) {
            validate.bind(obj, validate.validateType.idNo);
        });

        $(container).find("input[pattern]").each(function (i, obj) {
            validate.bind(obj, validate.validateType.pattern);
        });

        $(container).find("input[minlength]").each(function (i, obj) {
            validate.bind(obj, validate.validateType.minlength);
        });

        $(container).find("*[required]").each(function (i, obj) {
            validate.bind(obj, validate.validateType.required);
        });

        $(container).find("*[validate]").watch(function (obj) {
            validate.execute(obj);
        });

        $("input[type='positive']").watch(function (obj) {
            obj.val(support.typingFormatPositive($(obj).val()));
        });

        $(container).find("input[periodPattern]").each(function (i, obj) {
            validate.bind(obj, validate.validateType.periodPattern);
        });

    },

    bind: function (obj, validateType) {
        var existingFunc = $(obj).attr("validate");
        if (!existingFunc) {
            existingFunc = "";
        }
        existingFunc = "validate." + validateType + "|" + existingFunc;
        $(obj).attr("validate", existingFunc);
    },

    execute: function (obj) {
        var id = obj.attr("id");
        if (!obj.val()) {
            $("*[validateAlert][inputId='" + id + "']").hide();
            return;
        }
        var validateFunc = obj.attr("validate");
        var funcList = [];
        if (!validateFunc) {
            return;
        } else {
            funcList = validateFunc.split('|');
        }

        $("*[validateAlert][inputId='" + id + "']").hide();
        $("*[validatePass][inputId='" + id + "']").hide();
        var pass = true;
        for (var i = 0; i < funcList.length; i++) {
            if (!funcList[i]) {
                continue;
            }
            var str = "";

            if (obj.val().indexOf("'") != -1) {
                str = '("' + obj.val() + '", obj)';
            } else {
                str = "('" + obj.val() + "', obj)";
            }
            if (!eval(funcList[i] + str)) {
                pass = false;
                obj.removeAttr("pass");
                if (obj.is(":visible")) {
                    obj.attr("fail", "");
                }

                $(
                    "*[validateAlert='" + funcList[i] + "'][inputId='" + id
                    + "']").show();
                break;
            }
        }
        if (pass) {
            obj.removeAttr("fail");
            obj.attr("pass", "");
            $("*[validatePass][inputId='" + id + "']").show();
        }
    },

    required: function (input, obj) {
        if (input) {
            return true;
        }
        return false;
    },

    minlength: function (input, obj) {
        if (input.length < parseInt($(obj).attr("minlength"))) {
            return false;
        }
        return true;
    },

    pattern: function (input, obj) {
        if (input) {
            if (!input.match(new RegExp($(obj).attr("pattern")))) {
                return false;
            }
            return true;
        }
        return false;
    },

    email: function (input, obj) {
        if (input) {
            var reg = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z_-]{2,3}){1,2})$/;
            if (!reg.test(input)) {
                return false;
            }
            return true;
        }
        return false;
    },

    chinese: function (input, obj) {
        if (input) {
            if (!input.match(new RegExp("^[\u2E80-\uFE4F]+$"))) {
                return false;
            }
            return true;
        }
        return false;
    },

    mobile: function (input, obj) {
        if (input) {
            var reg = /^1\d{10}$/;
            if (!reg.test(input)) {
                return false;
            }
            return true;
        }
        return false;
    },

    bankCardNo: function (input, obj) {
        if (input) {
            var reg = /^(\d{10,20})$/;
            if (!reg.test(input)) {
                return false;
            }
            return true;
        }
        return false;
    },

    idNo: function (input, obj) {
        var num = input;
        if (!num) {
            return false;
        }

        num = num.toUpperCase();
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
            return false;
        }

        var len, re;
        len = num.length;
        if (len == 15) {
            re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
            var arrSplit = num.match(re);

            var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3]
                + '/' + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2]))
                && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
                && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                return false;
            }
        }
        if (len == 18) {
            re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
            var arrSplit = num.match(re);

            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/"
                + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
                && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
                && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                return false;
            } else {
                var valnum;
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
                    5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5',
                    '4', '3', '2');
                var nTemp = 0, i;
                for (i = 0; i < 17; i++) {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                valnum = arrCh[nTemp % 11];
                if (valnum != num.substr(17, 1)) {
                    return false;
                }
            }
        }
        return true;
    },

    periodPattern: function (input, obj) {
        if (input.length > 1) {

            if ($("#repType").val() != "5" && input < 30) {
                $("#repType").val("5");
                $("#repType").attr("disabled", true);
            } else {
                $("#repType").removeAttr("disabled");
            }
        }
        return true;
    }
};
function fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}
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
