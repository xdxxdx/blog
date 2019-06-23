$(function() {
	var type=$("[name=type]").val();
	//二级联动
	$("#pMenuIdSelect").remoteChained("#menuType","./menuListByMenuType?pMenuId="+$("#pMenuIdTmp").val())
	var validForm={
		message : 'This value is not valid',
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			menuName : {
				message : '表单校验失败',
				validators : {
					notEmpty : {
						message : '不能为空'
					},
					//远程验证，请求服务器接口后验证
					remote: {
                                type: 'POST',
                                url: './menuNameOnly',
                                 validKey: 'valid',
		                        dataType:'json',
		                        message: '该菜单已经存在'
                            }
				}
			},
			'employee.realName' : {
				message : '表单校验失败',
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
		}
	}
	if(type==2){
		validForm.fields.menuName={
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
			$("#pMenuId").val($("#pMenuIdSelect").val().substring(4));
			$.ajax({
				type:'post',
				url:'./save',
				data:$('#thisForm').serialize(),
				dataType:'html',
				success:function(data){
					if(data>0){
						alert("成功");
						window.parent.close_layer();
						window.parent.query();//刷新父页面
					}else{
						alert("失败");
					}
				}
				
			});
		}
	});

});

