var type = $("[name=type]").val();
$(function() {
        var E = window.wangEditor; //这里的id为<div id="editor"中的id.
		var editor = new E('#editor'); // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
		editor.customConfig.uploadImgServer = '/wangUpload';//配置属性名称，绑定请求的图片数据 //controller会用到，可以随便设置，但是一定要与controller一致 
		editor.customConfig.uploadFileName = 'wangUploadName'; 
		//创建编辑器
		editor.create();
	if(type==2){
	    editor.txt.html($(".temp").html());
  	}
	
	var validForm = {
		message: 'This value is not valid',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			blogTitle: {
				message: '表单校验失败',
				validators: {
					notEmpty: {
						message: '不能为空'
					},
				}
			},
		}
	}
	$('#thisForm').formValidation(validForm);
	$("#submit1").click(function() {
		var $form = $("#thisForm");
		var bv = $form.data('formValidation');
		bv.validate();
		if(bv.isValid()) {
			var checkArr = []; //选中的数组
			var checkStr; //选中的数组的字符串形式，用逗号隔开
			$("input[type='checkbox'][name='ck_tag']:checked").each(function() {
				var _this = $(this);
				console.log(_this.val());
				checkArr.push(_this.val());
			});
			checkStr = checkArr.join(",");
			console.log(checkStr);
			$("#tags").val(checkStr);
			$("#isPrivate").val($("#isPrivate").is(":checked")?1:0);
			if($("#blogAbstract").val().trim()==""){
				var blogAbstract=editor.txt.text().substring(0,140);
				$("#blogAbstract").val(blogAbstract);
			}
			$("#blogContent").val(editor.txt.html());
			$.ajax({
				type: 'post',
				url: './save',
				async : false,
				data: $('#thisForm').serialize(),
				dataType: 'html',
				success: function(data) {
					if(data > 0) {
						$.ajax({
							type: 'post',
							dataType : "html",
							url: '/blog/indexList2Redis',
							success: function(result) {
								console.log(result);
							}
						});
						alert("成功");
						location.href="./home";
					} else {
						alert("失败，请在新窗口中重新登录以获取session");
					}
				}

			});
		}
	});
	$("#publish").click(function() {
		var $form = $("#thisForm");
		var bv = $form.data('formValidation');
		bv.validate();
		if(bv.isValid()) {
			var checkArr = []; //选中的数组
			var checkStr; //选中的数组的字符串形式，用逗号隔开
			$(".check-wrap input[type='checkbox']:checked").each(function() {
				var _this = $(this);
				console.log(_this.val());
				checkArr.push(_this.val());
			});
			checkStr = checkArr.join(",");
			console.log(checkStr);
			$("#tags").val(checkStr);
			$("#isPrivate").val($("#isPrivate").is(":checked")?1:0);
			if(("#blogAbstract").val().trim()==""){
				var blogAbstract=ue.getContentTxt().substring(0,140);
				$("#blogAbstract").val(blogAbstract);
			}
			$.ajax({
				type: 'post',
				url: './publish',
				data: $('#thisForm').serialize(),
				dataType: 'html',
				success: function(data) {
					if(data > 0) {
						$.ajax({
							type: 'post',
							dataType : "html",
							url: '/blog/indexList2Redis',
							success: function(result) {
								console.log(result);
							}
						});
						alert("成功");
						location.href="./home";
					} else {
						alert("失败,session过期，请在新开页面重新登录系统后，再回到该页面保存");
					}
				}

			});
		}
	});
	$("#cancel").click(function() {
		location.href='./home';
	});
});

	function HTMLDecode(text) { 
		var temp = document.createElement("div"); 
		temp.innerHTML = text; 
		var output = temp.innerText || temp.textContent; 
		temp = null; 
		return output; 
	} 
	$('#blogAbstract').bind('input propertychange', function(){
		var current_num=$(this).val().length;
			if(current_num<=150){
			$(".current-num").text(150 - $(this).val().length);
			}else{
				$(this).val($(this).val().slice(0,150));
			}
	})
