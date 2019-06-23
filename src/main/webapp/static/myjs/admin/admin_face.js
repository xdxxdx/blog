	 $(function(){
	 	$(".current-num").text(150 - $("#briefInfo").val().length);
	 	$('#briefInfo').bind('input propertychange', function(){
			var current_num=$(this).val().length;
				if(current_num<=150){
				$(".current-num").text(150 - $(this).val().length);
				}else{
					$(this).val($(this).val().slice(0,150));
				}
		})
	 })
	 
	$("#submit1").click(function() {
		var $form = $("#thisForm");
            $.ajax({
                type : 'post',
                url : './adminFaceAndInfoSave',
                cache: false,
                data: new FormData($('#thisForm')[0]),
                processData: false,
                contentType: false,
                dataType : 'html',
                success : function(data) {
                    if (data > 0) {
                    	alert("成功");
                    	location.href="/home"
                    } else {
                        alert("失败")
                    }
                }
            });
	});