$(function() {
	if (top != self) {
	    window.parent.location.reload();
	}
	$("input[type='password'][data-eye]").each(function(i) {//查找tag为input,且属性type为password,且包含了data-eye这个属性的远古三
		let $this = $(this);//let相当于var，是最新的用法

		$this.wrap($("<div/>", {
			style: 'position:relative'
		}));//
		$this.css({
			paddingRight: 60
		});
		$this.after($("<div/>", {
			html: 'Show',
			class: 'btn btn-primary btn-sm',
			id: 'passeye-toggle-'+i,
			style: 'position:absolute;right:10px;top:50%;transform:translate(0,-50%);padding: 2px 7px;font-size:12px;cursor:pointer;'
		}));
		$this.after($("<input/>", {
			type: 'hidden',
			id: 'passeye-' + i
		}));
		$this.on("keyup paste", function() {
			$("#passeye-"+i).val($(this).val());
		});
		$("#passeye-toggle-"+i).on("click", function() {
			if($this.hasClass("show")) {
				$this.attr('type', 'password');
				$this.removeClass("show");
				$(this).removeClass("btn-outline-primary");
			}else{
				$this.attr('type', 'text');
				$this.val($("#passeye-"+i).val());				
				$this.addClass("show");
				$(this).addClass("btn-outline-primary");
			}
		});
	});
	//提交按钮触发事件
//	$("#btn_submit").click(function(){
//			$.ajax({
//			type : 'post',
//			url : 'login',
//			data : $('#thisForm').serialize(),
//			dataType : 'html',
//			success : function(data) {
//				if (data > 0) {					
//					alert("成功");
//					location.reload();
//				} else {
//					alert("失败")
//				}
//			}
//		});
//	});
});