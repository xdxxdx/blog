$(function() {
	$('#thisForm').formValidation({
		message : 'This value is not valid',
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			partnerName : {
				message : '合伙人名称验证不通过',
				validators : {
					notEmpty : {
						message : '不能为空'
					},
				/*
				 * stringLength: { min: 6, max: 30, message: 'The username must
				 * be more than 6 and less than 30 characters long' },
				 */
				/*
				 * remote: { url: 'remote.php', message: 'The username is not
				 * available' },
				 */
				/*
				 * regexp: { regexp: /^[a-zA-Z0-9_\.]+$/, message: 'The username
				 * can only consist of alphabetical, number, dot and underscore' }
				 */
				}
			},
			realName : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},
				}
			},
			phone : {
				validators : {
					verbose: false,
					notEmpty : {
						message : '不能为空'
					},
					phone : {
						message : '不是有效的电话号码',
						country:'CN'
					},
					 remote: {
		                        type: 'GET',
		                        url: 'partnerByPhone',
		                        validKey: 'valid',
		                        dataType:'json',
		                        message: '该号码已经存在'
		                        //delay: 1000
		                    }
				}
			},
			 joinFee: {
		                validators: {
		                    notEmpty: {
		                    	message:'不能为空'
		                    	},
		                    digits: {
		                    	message:'不是有效的金额'
		                    	},
		                    greaterThan: {
		                        value: 0
		                    },
		           
		                }
		            }
		}
	})
	$("#submit1").click(function() {
		var $form = $("#thisForm");
		var bv = $form.data('formValidation');
		bv.validate();
		if(bv.isValid()){
			$.ajax({
				type:'post',
				url:'partnerSave',
				data:$('#thisForm').serialize(),
				dataType:'html',
				success:function(data){
					if(data>0){
						alert("成功");
						location.href="partnerHome";
					}else{
						alert("失败");
					}
				}
				
			});
		}
	});

});
