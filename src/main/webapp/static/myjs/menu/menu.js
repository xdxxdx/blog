$(function() {
	query();
	// 弹出层
	$(".btn_add").click(function() {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.open({
				title : '新增',
				type : 2,
				area : [ '600px', '450px' ],
				shade : 0.5,
				maxmin : true,
				content : '/menu/add'
			});
		});
	});
	$("#btn_search").click(function(){
		query();
	})
})
var zTreeNodes;
var setting = {
	view : {
		showLine : false,
		showIcon : false,
		addDiyDom : addDiyDom
	},
	data : {
		simpleData : {
			enable : true,
			idKey:"menuId",
			pIdKey:"pMenuId",
			rootPId:null
		},
		key:{
		name:"menuName",
	}
	},
	
};
/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
	var spaceWidth = 15;
	var liObj = $("#" + treeNode.tId);
	var aObj = $("#" + treeNode.tId + "_a");
	var switchObj = $("#" + treeNode.tId + "_switch");
	var icoObj = $("#" + treeNode.tId + "_ico");
	var spanObj = $("#" + treeNode.tId + "_span");
	aObj.attr('title', '');
	aObj.append('<div class="diy swich"></div>');
	var div = $(liObj).find('div').eq(0);
	switchObj.remove();
	spanObj.remove();
	icoObj.remove();
	div.append(switchObj);
	div.append(spanObj);
	var spaceStr = "<span style='height:1px;display: inline-block;width:"
			+ (spaceWidth * treeNode.level) + "px'></span>";
	switchObj.before(spaceStr);
	var editStr = '';
	editStr += '<div class="diy">'
			+ (treeNode.menuType ==1 ? '主菜单' : '树菜单')
			+ '</div>';
	var menuSrc = '<div title="' + treeNode.menuSrc + '">' + treeNode.menuSrc
			+ '</div>';
	editStr += '<div class="diy">'
			+ (treeNode.menuSrc == null ? '#' : menuSrc) + '</div>';
	editStr += '<div class="diy">'
			+ (treeNode.menuIntro == null ? '&nbsp;'
					: treeNode.menuIntro) + '</div>';
	if(treeNode.menuLevel==1){
		editStr += '<div class="diy">'
			+ (treeNode.priority1 == null ? '&nbsp;'
					: treeNode.priority1) + '</div>';
	}else if(treeNode.menuLevel==2){
		editStr += '<div class="diy">'
			+ (treeNode.priority2 == null ? '&nbsp;'
					: treeNode.priority2) + '</div>';
	}
	editStr += '<div class="diy">' + formatHandle(treeNode) + '</div>';
	aObj.append(editStr);
}
/**
 * 查询数据
 */
function query() {
	$.ajax({
				type : 'get',
				url : './list?menuType='+$("#menuType").val()+'&menuName='+$("#menuName").val(),
				dataType : 'json',
				success : function(data) {
					zTreeNodes = data;
					// 初始化树
					$.fn.zTree.init($("#dataTree"), setting, zTreeNodes);
					// 添加表头
					var li_head = ' <li class="head"><a><div class="diy">菜单名称</div><div class="diy">菜单类型</div><div class="diy">链接地址</div>'
							+ '<div class="diy">简介</div><div class="diy">优先级</div><div class="diy">操作</div></a></li>';
					var rows = $("#dataTree").find('li');
					if (rows.length > 0) {
						rows.eq(0).before(li_head)
					} else {
						$("#dataTree").append(li_head);
						$("#dataTree")
								.append(
										'<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
					}
				},
				error : function(data) {
					alert(data);
				}

			});

}
/**
 * 根据权限展示功能按钮
 * 
 * @param treeNode
 * @returns {string}
 */
function formatHandle(treeNode) {
	var htmlStr = '';
	htmlStr += '<div class="icon_div"><a class="" title="查看"  href="javascript:view(\''
			+ treeNode.menuId + '\')">查看</a></div>';
	htmlStr += '<div class="icon_div"><a class="" title="修改" href="javascript:edit(\''
			+ treeNode.menuId + '\')">修改</a></div>';
	htmlStr += '<div class="icon_div"><a class="" title="删除" href="javascript:del(\''
			+ treeNode.menuId + '\')">删除</a></div>';
	if(treeNode.menuLevel==1){
		htmlStr += '<div class="icon_div"><a class="" title="优先级" href="javascript:priority('+ treeNode.menuId +','+treeNode.priority1+')">优先级</a></div>';
	}else if(treeNode.menuLevel==2){
		htmlStr += '<div class="icon_div"><a class="" title="优先级" href="javascript:priority('+ treeNode.menuId +','+treeNode.priority2+')">优先级</a></div>';
	}
	
	return htmlStr;
}
function edit(menuId){
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.open({
				title : '编辑',
				type : 2,
				area : [ '600px', '450px' ],
				shade : 0.5,
				maxmin : true,
				content : '/menu/edit?menuId='+menuId
			});
		});
}
function priority(menuId,priority){
	console.log(menuId+","+priority);
	$('#priority_div [name="menuId"]').val(menuId);
	$('#priority_div [name="priority"]').val(priority);
	layui.use('layer', function() {
			var layer = layui.layer;
			layer.open({
				title : '优先级',
				area : [ '300px', '200px' ],
				type : 1,
				content : $('#priority_div')
			});
		});
}
//关闭弹出层
function close_layer(){
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.closeAll();
		});
}

	$("#submit1").click(function() {
		var $form = $("#thisForm");
		var priority=$('#priority_div [name="priority"]').val();
		var flag=$.is_NaN(priority);
		console.log(flag);
		if(flag){
			$.ajax({
				type:'post',
				url:'./priority',
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
		}else{
			alert("请填入整数");
			return false;
		}
	});