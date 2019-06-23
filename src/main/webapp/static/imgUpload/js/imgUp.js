$(function(){
	var limit_num=1;
	var delParent;
	var defaults = {
		fileType         : ["jpg","png","bmp","jpeg"],   // 上传文件的类型
		fileSize         : 1024 * 1024 * 10                  // 上传文件的大小 10M
	};
		/*点击图片的文本框*/
	$(document).on(
				'change',
				'.file',
				function(){
		var $file= $(this);
		var file =$file[0];//取得inputfile对象的第一个元素
		var imgContainer = $(this).parents(".z_photo"); //存放图片的父亲元素
		var fileList = file.files; //获取的图片文件
		var current_num = imgContainer.find(".z_file").length;//当前数量
		if(current_num > limit_num ){
			alert("超过指定的上传图片数目");  //一次选择上传超过5个 或者是已经上传和这次上传的到的总数也不可以超过5个
		}else{
			validateUp(file);//验证图片的合法性
			var fileObject=file.files[0];//取得file对象
			for(var i = 0;i<fileList.length;i++){
			 var imgUrl = window.URL.createObjectURL(fileObject);
			 var $section = $file.parent();//取得input file的父级section
			 $section.addClass("loading");
			 if ($file.attr('t') != 'c'){
			 	var $img0 = $("<img class='close-upimg'>").on("click",function(event){
//				    event.preventDefault();
//					event.stopPropagation();
					$(".works-mask").show();
					delParent = $(this).parent();
				});   
				$img0.attr("src","/static/imgUpload/img/a7.png").appendTo($section);
			 }
				var $img = $section.find('.add-img');
		     	console.log($img);
		     	$img.attr("src",imgUrl);      
		   }
		}
		setTimeout(function(){
             $file.parent().removeClass("loading");
		 },450);
		if(current_num <limit_num){
				if ($file.attr('t') != 'c') {
					$file.parents(".z_photo").append($file.parents(".z_photo").parent().find(
												'.templ').html());
				}
		}
		$file.attr('t', 'c');//标记该input已经被执行过一次change事件，用于判断是否新开一个input
	});
	
	
   
    $(".z_photo").delegate(".close-upimg","click",function(){
     	  $(".works-mask").show();
     	  delParent = $(this).parent();
	});
		
	$(".wsdel-ok").click(function(){
		$(".works-mask").hide();
		var numUp = delParent.parent().find(".close-upimg").length;//带有clos-upmg的z_file的个数
		var z_file_num=$(".z_file").length;
		if(numUp==z_file_num){
			delParent.parent().append(delParent.parent().parent().find(
												'.templ').html());
		}
		 delParent.remove();
		
	});
	
	$(".wsdel-no").click(function(){
		$(".works-mask").hide();
	});
		
		function validateUp(files){
			var arrFiles = [];//替换的文件数组
			for(var i = 0, file; file = files[i]; i++){
				//获取文件上传的后缀名
				var newStr = file.name.split("").reverse().join("");
				if(newStr.split(".")[0] != null){
						var type = newStr.split(".")[0].split("").reverse().join("");
						console.log(type+"===type===");
						if(jQuery.inArray(type, defaults.fileType) > -1){
							// 类型符合，可以上传
							if (file.size >= defaults.fileSize) {
								alert(file.size);
								alert('您这个"'+ file.name +'"文件大小过大');	
							} else {
								// 在这里需要判断当前所有文件中
								arrFiles.push(file);	
							}
						}else{
							alert('您这个"'+ file.name +'"上传类型不符合');	
						}
					}else{
						alert('您这个"'+ file.name +'"没有类型, 无法识别');	
					}
			}
			return arrFiles;
		}
		

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})
