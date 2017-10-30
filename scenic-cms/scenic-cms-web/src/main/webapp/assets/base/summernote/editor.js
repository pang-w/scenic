jQuery(function($) {
	$("#saveArticle").click(function() {
		save();
	});
	$("#saveAndPreview").click(function() {
		saveAndPreview();
	});
	$("#publishArticle").click(function() {
		publish();
	});
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 300
		});
	});
	// onImageUpload callback
	$('#summernote').summernote(
			{
				height : 300,
				callbacks : {
					onImageUpload : function(files) {
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
			"title" : $("#aritcleTitle").val(),
			"content" : $("#summernote").summernote('code'),
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("edit/article/save", data, function(response) {
			$("#previewContent").html(response.data.content);
		});
	}
	function save() {
		var data = {
			"title" : $("#aritcleTitle").val(),
			"content" : $("#summernote").summernote('code'),
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("edit/article/save", data, function(response) {
			layer.msg(response);
		});
	}
	function publish() {
		var data = {
			"uuid" : $("#articleUuid").val()
		};
		support.ajax("edit/article/publish", data, function(response) {
			var uri = "/article/" + $("#articleUuid").val() + ".html";
			layer.msg(response);
		});
	}
	
});
