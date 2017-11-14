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
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}

		return uuid.join('');
	},

	layerLogin : function layerLogin() {
		layer.open({
			  type:  2//Page层类型
			  ,area: ['500px', '300px']
			  ,title: '登录'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '/layerLogin.html'
			});
	},
	layerAddProduction : function layerAddProduction() {
		layer.open({
			  type:  2//Page层类型
			  ,area: ['500px', '600px']
			  ,title: '添加商品'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: false //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '/layerAddProduction.html'
			});
	},
	layerImage : function layerImage(url) {
		layer.open({
			  type:  1//Page层类型
			  ,area: ['500px', '300px']
			  ,title: '图片预览'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '<img alt="user" width="90%" src="'+ url +'">'
			});
	},
	logout : function logout() {
		support.ajax("user/auth/logout", null,function(response) {
			location.href = "/index.html";
		});
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
