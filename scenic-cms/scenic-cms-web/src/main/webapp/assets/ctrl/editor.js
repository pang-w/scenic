jQuery(function($) {
	checkLogged();
	
	$("#loginAction").click(function() {
		login();
	});
	$("#logoutAction").click(function() {
		logout();
	});
	$("#layerLoginAction").click(function() {
		support.layerLogin();
	});
	$("#likeSignatureBtn").click(function() {
		var data = {
				"belikedUser" : $("#signatureLikedUsername").text()
			};
		support.ajax("user/signature/likeSignature", data,function(response) {
			if (response.status == "0000") {
				location.reload();
			}
		});

		
	});
	
	$("#collapseArticleAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseArticle').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$("#collapseAttentionAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseAttention').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$("#collapseProductAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseProduct').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$("#collapseImageAction").click(function () {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						checkLogged();
						$('#collapseImage').slideToggle("slow");
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	
	function logout() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("user/auth/logout", null,function(response) {
			location.href = "/index.html";
		});
	}
	function login() {
		var data = {
			"username" : $("#iukusername").val(),
			"password" : $("#iukpassword").val()
		};
		support.ajax("user/auth/login", data, function(response) {
			checkLogged();
		});
	}
	
	
	
	$("#saveAndPreview").click(function() {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						saveAndPreview();
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
		
	});
	$("#publishArticle").click(function() {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						publish();
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
		
	});
	$("#unpublishArticle").click(function() {
		support.ajax("user/auth/checkLogged", null,
				function(response) {
					if(response.status!="0000"){
						layer.msg(response.msg);
					}else{
						unpublish();
					}
				},
				function(response) {
					layer.msg(response.msg);
				}
		);
	});
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'IUKISS....',
			height : 300,
			disableDragAndDrop : false
		});
	});
	
	// onImageUpload callback
	$('#summernote').summernote(
			{
				placeholder : 'IUKISS....',
				height : 300,
				disableDragAndDrop : false,
				callbacks : {
					onImageUpload : function(files) {
						$(".note-toolbar.btn-toolbar").append('正在上传图片');
						var $files = $(files);
						$files.each(function() {
							var file = this;
							// FormData，新的form表单封装，具体可百度，但其实用法很简单，如下
							var data = new FormData();
							// 将文件加入到file中，后端可获得到参数名为“file”
							data.append("file", file);
							data.append("description", "描述");
							// ajax上传
							$.ajax({
								data : data,
								type : "POST",
								url : "/action/user/article/saveImg",// div上的action
								cache : false,
								contentType : false,
								processData : false,

								// 成功时调用方法，后端返回json数据
								success : function(response) {
									// 封装的eval方法，可百度
									// 获取后台数据保存的图片完整路径
									var imageUrl = response.data.url;

									// 插入到summernote
									$('#summernote').summernote('insertImage',
											imageUrl, function($image) {
												$image.css('maxWidth', '80%');
												$image.css('align', 'center');
											});

								},
							});
						});

					}
				}
			});
	
	function saveAndPreview() {
		var data = {
			"title" : $("#articleTitle").val(),
			"content" : $("#summernote").summernote('code'),
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("edit/article/save", data, function(response) {
			$("#previewTitle").html(response.data.title);
			$("#previewContent").html(response.data.content);
		});
	}

	function publish() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("edit/article/publish", data, function(response) {
			layer.msg(response.msg);
		});
	}
	function unpublish() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("edit/article/unpublish", data, function(response) {
			layer.msg(response.msg);
		});
	}
	
});



function insertText(){
	var range = $('#summernote').summernote('createRange');
	$('#summernote').summernote('focus');
	$('#summernote').summernote('editor.insertText', 'hello world');

}