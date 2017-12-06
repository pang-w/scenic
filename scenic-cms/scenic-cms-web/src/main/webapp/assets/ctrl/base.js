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
			  ,area: ['300px', '300px']
			  ,title: '登录'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '/layerLogin.html'
			});
	},
	layerChangePassword : function layerLogin() {
		layer.open({
			  type:  2//Page层类型
			  ,area: ['300px', '300px']
			  ,title: '修改密码'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '/layerChangePassword.html'
			});
	},
	
	layerAddProduct : function layerAddProduction() {
		
		layer.open({
			  type:  2//Page层类型
			  ,area: ['300px', '450px']
			  ,title: '添加商品'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: false //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '/layerAddProduct.html'
			});
	},
	layerEditProduct : function layerEditProduct(pid) {
		support.ajax("page/product/searchById", {id:pid}, function(response) {
			layer.open({
				  type:  2//Page层类型
				  ,area: ['300px', '450px']
				  ,title: '添加商品'
				  ,shade: 0.6 //遮罩透明度
				  ,maxmin: false //允许全屏最小化
				  ,anim: 1 //0-6的动画形式，-1不开启
				  ,content: '/layerEditProduct.html' + praseParam(response.data)
				});

		}, function(response) {
			layer.msg(response.msg);
		});
		
	},
	
	layerImage : function layerImage(url) {
		layer.open({
			  type:  1//Page层类型
			  ,area: ['400px', '300px']
			  ,title: '图片预览'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: url+'<img alt="user" width="90%" src="'+ url +'">'
			});
	},
	logout : function logout() {
		support.ajax("user/auth/logout", null,function(response) {
			location.href = "/index.html";
		});
	},
	  
	ajax : function ajax(url, data, callback, errCallback, beforeSend) {
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
				} else if (data.status == "3001") {
					location.href = "login.html";
				} else if (data.status == "3002") {
					
				}else {
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
	fileUploadAjax : function(url, type,file, callback, errCallback, beforeSend) {
		// FormData，新的form表单封装，具体可百度，但其实用法很简单，如下
		var data = new FormData();
		// 将文件加入到file中，后端可获得到参数名为“file”
		data.append("file", file);
		data.append("type", type);
		// ajax上传
		$.ajax({
			data : data,
			type : "POST",
			url : "../action/"+url,// div上的action
			cache : false,
			contentType : false,
			processData : false,

			// 成功时调用方法，后端返回json数据
			success : function(response) {
				if (response.status == "0000") {
					if (callback && typeof callback == "function") {
						callback(response);
					}
				} else if (response.status == "3001") {
					location.href = "login.html";
				} else if (response.status == "3002") {
					
				}else {
					if (errCallback && typeof errCallback == "function") {
						errCallback(response);
					} else {
						layer.msg(response.msg);
					}
				}
				

			},
		});
	}
};

function praseParam(paramList) {
	var result = '?';
	for (var key in paramList) {
		result = result + key + "=" +paramList[key]+"&";
    }
    return result;
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
