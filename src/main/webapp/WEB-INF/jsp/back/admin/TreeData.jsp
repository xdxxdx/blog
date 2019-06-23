<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>Demo by dq</title>
<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9;IE=8;" />
<!--引入文件： 1、zTree默认css样式  2、jquery  3、zTree js-->
<link
	href="https://cdn.bootcss.com/zTree.v3/3.5.29/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/zTree.v3/3.5.29/js/jquery.ztree.all.min.js"></script>
</head>
<style>
/*按钮*/
.icon_div {
	display: inline-block;
	height: 25px;
	width: 35px;
	background:
		url(http://c.csdnimg.cn/public/common/toolbar/images/f_icon.png)
		no-repeat 12px -127px;
}

.icon_div a {
	display: inline-block;
	width: 27px;
	height: 20px;
	cursor: pointer;
}

/*end--按钮*/

/*ztree表格*/
.ztree {
	padding: 0;
	border: 2px solid #CDD6D5;
}

.ztree li a {
	vertical-align: middle;
	height: 30px;
}

.ztree li>a {
	width: 100%;
}

.ztree li>a, .ztree li a.curSelectedNode {
	padding-top: 0px;
	background: none;
	height: auto;
	border: none;
	cursor: default;
	opacity: 1;
}

.ztree li ul {
	padding-left: 0px
}

.ztree div.diy span {
	line-height: 30px;
	vertical-align: middle;
}

.ztree div.diy {
	height: 100%;
	width: 20%;
	line-height: 30px;
	border-top: 1px dotted #ccc;
	border-left: 1px solid #eeeeee;
	text-align: center;
	display: inline-block;
	box-sizing: border-box;
	color: #6c6c6c;
	font-family: "SimSun";
	font-size: 12px;
	overflow: hidden;
}

.ztree div.diy:first-child {
	text-align: left;
	text-indent: 10px;
	border-left: none;
}

.ztree .head {
	background: #5787EB;
}

.ztree .head div.diy {
	border-top: none;
	border-right: 1px solid #CDD2D4;
	color: #fff;
	font-family: "Microsoft YaHei";
	font-size: 14px;
}

/*end--ztree表格*/
</style>
<body>
	<div class="layer">
		<div id="tableMain">
			<ul id="dataTree" class="ztree">

			</ul>
		</div>
	</div>
	<script>
		var zTreeNodes;
		var setting = {
			view : {
				showLine : false,
				showIcon : false,
				addDiyDom : addDiyDom
			},
			data : {
				simpleData : {
					enable : true
				}
			}
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
					+ (treeNode.CONTACT_USER == null ? '&nbsp;'
							: treeNode.CONTACT_USER) + '</div>';
			var corpCat = '<div title="' + treeNode.CORP_CAT + '">'
					+ treeNode.CORP_CAT + '</div>';
			editStr += '<div class="diy">'
					+ (treeNode.CORP_CAT == '-' ? '&nbsp;' : corpCat)
					+ '</div>';
			editStr += '<div class="diy">'
					+ (treeNode.CONTACT_PHONE == null ? '&nbsp;'
							: treeNode.CONTACT_PHONE) + '</div>';
			editStr += '<div class="diy">' + formatHandle(treeNode) + '</div>';
			aObj.append(editStr);
		}
		/**
		 * 查询数据
		 */
		function query() {
			$.ajax({
						type : 'get',
						url : './static/json/node_data.json',
						dataType : 'json',
						success : function(data) {
							zTreeNodes = data;
							//初始化树
							$.fn.zTree.init($("#dataTree"), setting, zTreeNodes);
							//添加表头
							var li_head = ' <li class="head"><a><div class="diy">名称</div><div class="diy">联系人</div><div class="diy">主管行业</div>'
									+ '<div class="diy">联系方式</div><div class="diy">操作</div></a></li>';
							var rows = $("#dataTree").find('li');
							if (rows.length > 0) {
								rows.eq(0).before(li_head)
							} else {
								$("#dataTree").append(li_head);
								$("#dataTree").append(
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
		 * @param treeNode
		 * @returns {string}
		 */
		function formatHandle(treeNode) {
			var htmlStr = '';
			htmlStr += '<div class="icon_div"><a class="icon_view" title="查看"  href="javascript:view(\''
					+ treeNode.id + '\')"></a></div>';
			htmlStr += '<div class="icon_div"><a class="icon_edit" title="修改" href="javascript:edit(\''
					+ treeNode.id + '\')"></a></div>';
			htmlStr += '<div class="icon_div"><a class="icon_add" title="添加子部门" href="javascript:addSector(\''
					+ treeNode.id + '\')"></a></div>';
			htmlStr += '<div class="icon_div"><a class="icon_del" title="删除" href="javascript:del(\''
					+ treeNode.id + '\')"></a></div>';
			return htmlStr;
		}

		$(function() {
			query();
		})
	</script>
</body>
</html>