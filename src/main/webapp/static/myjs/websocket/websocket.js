var layim = '';
var option = '';
var websocket = '';
var lockReconnect = false;// 避免重复连接
var tt;
// var last_health;
// var health_timeout = 10;
// var tDates = [], tData = [];
var userId = $('#session_userId').val();
// im
layui.use('layim', function() {
	layim = layui.layim;
	// 基础配置
	option = {
		init : {
			url : '/im/init?adminId=' + userId // 接口地址（返回的数据格式见下文）
			,
			type : 'get' // 默认get，一般可不填
			,
			data : {}
		// 额外参数
		} // 获取主面板列表信息，下文会做进一步介绍

		// 获取群员接口（返回的数据格式见下文）
		,
		members : {
			url : '/im/member' // 接口地址（返回的数据格式见下文）
			,
			type : 'get' // 默认get，一般可不填
			,
			data : {}
		// 额外参数
		}

		// 上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
		,
		uploadImage : {
			url : '' // 接口地址
			,
			type : 'post' // 默认post
		}

		// 上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
		,
		uploadFile : {
			url : '' // 接口地址
			,
			type : 'post' // 默认post
		}
		// 扩展工具栏，下文会做进一步介绍（如果无需扩展，剔除该项即可）
		,
		tool : [ {
			alias : 'code' // 工具别名
			,
			title : '代码' // 工具名称
			,
			icon : '&#xe64e;' // 工具图标，参考图标文档
		} ]

		,
		msgbox : layui.cache.dir + 'css/modules/layim/html/msgbox.html' // 消息盒子页面地址，若不开启，剔除该项即可
		,
		find : layui.cache.dir + 'css/modules/layim/html/find.html' // 发现页面地址，若不开启，剔除该项即可
		,
		chatLog : layui.cache.dir + 'css/modules/layim/html/chatlog.html' // 聊天记录页面地址，若不开启，剔除该项即可
	}
	layim.config(option);
	// 监听发送消息
	layim.on('sendMessage', function(res) {
		console.log("监听到发送一条消息");
		var mine = res.mine;// 发送者信息
		var to = res.to;// 接受者信息
		// 监听到上述消息后，就可以轻松地发送socket了，如：
		websocket.send(JSON.stringify({
			type : 'chat' // 随便定义，用于在服务端区分消息类型
			,
			data : res
		}));
		console.log("监听到发送一条消息结束");
	});
	// websocket
	var initWs = function() {
		if (window.WebSocket) {
			websocket = new WebSocket(encodeURI('ws://localhost:8888'));
			websocket.onopen = function() {
				// NotificationHandler.requestPermission();
				websocket.send(JSON.stringify({
					type : 'onopen',
					data : userId + "连接成功"
				}));
				heartCheck.reset().start();
			};
			websocket.onerror = function() {
				console.log('连接发生错误');
			};
			websocket.onclose = function() {
				console.log('已经断开连接');
				// NotificationHandler.showNotification('./favicon.ico','消息推送已断开','请多次刷新页面，若无效果请联系管理员','NotificationHandler.requestPermission();')
				//此时websocket已经被关闭，send方法已经不可用，但是会触发服务端的onClose方法。所以可在onclose方法中写相关代码
				reconnect();
			};
			// 消息接收
			websocket.onmessage = function(res) {
				console.log(res);
				console.log(res.data);
				var data = JSON.parse(res.data);
				// res = JSON.parse(res);//将String消息转为json
				heartCheck.reset().start();
				if (data.type == "chat") {
					layim.getMessage(data.content);
				} else if (data.type == "online") {
					// 好友置亮
					layim.setFriendStatus(data.content, 'online');
					console.log(data.content + "上线了！！");
				}else if(data.type=="offline"){
					//好友置灰
					layim.setFriendStatus(data.content, 'offline');
					console.log(data.content + "离线了！！");
				}
				// NotificationHandler.showNotification('./favicon.ico','新订单提示','您有新的订单,请及时处理！','NotificationHandler.requestPermission();$("#myaudio")[0].play();')
			};
		} else {
			alert("该浏览器不支持即时消息推送。<br/>建议使用高版本的浏览器，<br/>如 IE10、火狐 、谷歌  、搜狗等");
		}
	}
	if ($('body').attr('ws') == 'yes') {
		userId = $('#session_userId').val();
		initWs();
	}
	// 心跳检测,每20s心跳一次
	var heartCheck = {
		timeout : 20000,// 客户端每隔20s发送一个心跳信息给服务端
		timeoutObj : null,
		serverTimeoutObj : null,
		reset : function() {// 如果服务端有返回，就
			clearTimeout(this.timeoutObj);
			clearTimeout(this.serverTimeoutObj);
			return this;
		},
		start : function() {
			var self = this;
			this.timeoutObj = setTimeout(function() {
				// 这里发送一个心跳，后端收到后，返回一个心跳消息，
				// onmessage拿到服务器返回的心跳就说明连接正常，会先执行reset，执行完reset后，当前定时器的代码就不会在执行，即里层这个定时器的代码不会在执行(ws.close不会执行，也就不必重连)。反之，如果服务端
				// 在制定时间timeout内未能返回，则执行ws.close，从而触发重连操作。
				websocket.send(JSON.stringify({
					type : 'heartbeat' // 随便定义，用于在服务端区分消息类型
					,
					data : "ping"
				}));
				console.info("客户端发送心跳：" + new Date());
				self.serverTimeoutObj = setTimeout(function() {// 如果超过一定时间还没重置，说明后端主动断开了
					websocket.close();// 如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect
										// 会触发onclose导致重连两次
				}, self.timeout)
			}, this.timeout)
		}
	}
	function reconnect() {
		if (lockReconnect) {
			console.log("不执行重连");
			return;
		}
		lockReconnect = true;
		// 没连接上会自动一直重连，设置延迟避免请求过多
		tt && clearTimeout(tt);
		tt = setTimeout(function() {
			initWs();
			lockReconnect = false;
			console.log("执行重连");
		}, 4000);
	}
});
