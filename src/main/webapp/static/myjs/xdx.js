//模拟form表单提交打开新的页面
$.extend({
	open_page: function(url, param) {
		var form = '<form action="' + url + '"  target="_blank"  id="windowOpen" style="display:none">';
		for(var key in param) {
			form += '<input name="' + key + '" value="' + param[key] + '"/>';
		}
		form += '</form>';
		$('body').append(form);
		$('#windowOpen').submit();
		$('#windowOpen').remove();
	},
	is_NaN:function(val){
    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
    if(val === "" || val ==null){
        return false;
    }
    if(!isNaN(val)){
        return true;
    }else{
        return false;
    }
	},
	timestampToTime:function(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D+h+m+s;
    }
});