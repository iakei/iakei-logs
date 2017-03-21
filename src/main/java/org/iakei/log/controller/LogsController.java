package org.iakei.log.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.iakei.log.controller.executor.LogsExecutor;
import org.iakei.log.entity.LogsInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iakei-logs/logs")
public class LogsController {

	@PostMapping("/addLogs")
	public Object addLogs(@RequestBody LogsInfo logsInfo) {
		Map<String, Object> result = new HashMap<>();
		logsInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
		LogsExecutor.handleLogs(logsInfo);
		result.put("errorCode", 200);
		result.put("desc", "success");
		return result;
	}

}
