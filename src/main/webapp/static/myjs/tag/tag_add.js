$(function() {
	var type=$("[name=type]").val();
	var validForm={
		message : 'This value is not valid',
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			tagName : {
				message : '表单校验失败',
				validators : {
					notEmpty : {
						message : '不能为空'
					},
					//远程验证，请求服务器接口后验证
					remote: {
                                type: 'POST',
                                url: './tagByTagName',
                                 validKey: 'valid',
		                        dataType:'json',
		                        message: '该标签名已经存在'
                            }
				}
			},
		}
	}
	if(type==2){
		validForm.fields.tagName={
			message : '表单校验失败',
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
		}
	}
	$('#thisForm').formValidation(validForm);
	$("#submit1").click(function() {
		var $form = $("#thisForm");
		var bv = $form.data('formValidation');
		bv.validate();
		if(bv.isValid()){
			$.ajax({
				type:'post',
				url:'./save',
				data:$('#thisForm').serialize(),
				dataType:'html',
				success:function(data){
					if(data>0){
						alert("成功");
						$.ajax({
							type: 'post',
							dataType : "html",
							url: '/tag/setTags2Redis',
							success: function(result) {
								console.log(result);
							}
						});
						window.parent.close_layer();
						window.parent.search();//刷新父页面
					}else{
						alert("失败");
					}
				}
				
			});
		}
	});

});

