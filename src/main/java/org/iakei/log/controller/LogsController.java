package org.iakei.log.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.iakei.log.entity.LogsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/iakei-logs/logs")
public class LogsController {

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping("/addLogs")
	public Object addLogs(@RequestBody LogsInfo logsInfo) {
		Map<String, Object> result = new HashMap<>();
		logsInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				kafkaTemplate.send("bootLogsTopic", JSON.toJSONString(logsInfo));
			}
		});
		result.put("errorCode", 200);
		result.put("desc", "success");
		return result;
	}

}
