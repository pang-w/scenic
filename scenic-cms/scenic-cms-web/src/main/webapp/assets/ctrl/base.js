var support = {

	uuid : function uuid(len, radix) {
		var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
				.split('');
		var uuid = [], i;
		radix = radix || chars.length;

		if (len) {
			// Compact form
			for (i = 0; i < len; i++)
				uuid[i] = chars[0 | Math.random() * radix];
		} else {
			// rfc4122, version 4 form
			var r;

			// rfc4122 requires these characters
			uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
			uuid[14] = '4';

			// Fill in random data. At i==19 set the high bits of clock sequence
			// as
			// per rfc4122, sec. 4.1.5
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}

		return uuid.join('');
	},
	ajax : function(url, data, callback, errCallback, beforeSend) {
		var settings = {
			url : "/action/" + url,
			contentType : "application/json;charset=UTF-8",
			type : "POST",
			data : JSON.stringify(data),
			dataType : "json",
			beforeSend : function() {
				$('.loadingmask').show();
			},
			success : function(data) {
				$('.loadingmask').hide();
				if (data.status == "0000") {
					if (callback && typeof callback == "function") {
						callback(data);
					}
				} else if (data.status == "4003") {
					location.href = "login.html";
				} else {
					if (errCallback && typeof errCallback == "function") {
						errCallback(data);
					} else {
						layer.msg(data.msg);
					}
				}
			}
		};
		$.ajax(settings);
	},

	syncAjax : function(url, data, callback, errCallback, beforeSend) {
		var settings = {
			url : "../action/" + url,
			contentType : "application/json;charset=UTF-8",
			type : "POST",
			data : JSON.stringify(data),
			async : false,
			dataType : "json",
			beforeSend : beforeSend,
			success : function(data) {
				if (data.status == "0000") {
					if (callback && typeof callback == "function") {
						callback(data);
					}
				} else if (data.status == "4003") {
					location.href = "login.html";
				} else {
					if (errCallback && typeof errCallback == "function") {
						errCallback(data);
					} else {
						layer.msg(data.msg);
					}
				}
			}
		};

		$.ajax(settings);
	},

	syncFileUploadAjax : function(url, data, callback, errCallback, beforeSend) {
		var settings = {
			type : 'POST',
			url : "../action/" + url,
			dataType : 'json',
			data : data,
			contentType : false,
			processData : false,
			timeout : 300000, // 超时设置5分钟
			beforeSend : function() {
				$('.loadingmask').show();
			},
			success : function(data) {
				$('.loadingmask').hide();
				if (data.status == "0000") {
					if (callback && typeof callback == "function") {
						callback(data);
					}
				} else if (data.status == "4003") {
					location.href = "login.html";
				} else {
					if (errCallback && typeof errCallback == "function") {
						errCallback(data);
					} else {
						layer.msg(data.msg);
					}
				}
			}
		};

		$.ajax(settings);
	},

	fileUploadAjax : function(url, filesId, data, callback, errCallback,
			beforeSend) {
		$.ajaxFileUpload({
			url : "../action/" + url,
			secureuri : false,
			fileElementId : filesId,
			dataType : 'json',
			data : data,
			success : function(data, status) {
				if (data.status == "0000") {
					if (callback && typeof callback == "function") {
						callback(data);
					}
				} else if (data.status == "4003") {
					location.href = "login.html";
				} else {
					if (errCallback && typeof errCallback == "function") {
						errCallback(data);
					} else {
						layer.msg(data.msg);
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
