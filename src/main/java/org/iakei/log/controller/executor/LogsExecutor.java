package org.iakei.log.controller.executor;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.iakei.log.entity.LogsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class LogsExecutor {

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	private static ConcurrentLinkedQueue<LogsInfo> concurrentQueue = new ConcurrentLinkedQueue<LogsInfo>();
	private static KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired(required=true)
	public void SetStringKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate){
		LogsExecutor.kafkaTemplate=kafkaTemplate;
	}

	private final static Logger LOGGER = LoggerFactory.getLogger(LogsExecutor.class);

	public static void handleLogs(LogsInfo logsInfo) {
		LOGGER.debug("logsInfo ={}", logsInfo.toString());
		concurrentQueue.offer(logsInfo);
		executorService.submit(new HandleRunnable());
	}

	private static class HandleRunnable implements Runnable {
		@Override
		public void run() {
			if (!concurrentQueue.isEmpty()) {
				for (;;) {
					kafkaTemplate.send("bootLogsTopic", JSON.toJSONString(concurrentQueue.poll()));
					if (concurrentQueue.isEmpty()) {
						break;
					}
				}
			}
		}
	}

}
