package com.xdx.spring_rabbit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xdx.entity.TLog;
import com.xdx.service.LogService;
//@Component("logRabbitRecHandler")
public class LogRabbitRecHandler implements RabbitRecHandler<TLog> {
	@Resource(name="logService")
	private LogService logService;

	@Override
	public void handleMessage(TLog log) {
		System.err.println("开始存储日志"+log.getOperator()+","+log.getEvent());
		logService.saveLog(log);
	}
}
