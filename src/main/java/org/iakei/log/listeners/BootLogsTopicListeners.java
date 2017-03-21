package org.iakei.log.listeners;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.iakei.log.entity.LogsInfo;
import org.iakei.log.service.LogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class BootLogsTopicListeners implements KafkaListeners{

	@Autowired
	LogsService logsService;

	private static final Logger LOGGER=LoggerFactory.getLogger(BootLogsTopicListeners.class);
	@Override
	@KafkaListener(topics = { "bootLogsTopic" })
	public void Listener(ConsumerRecord<?, ?> record) {
		Optional<?> messages = Optional.ofNullable(record.value());
		if (messages.isPresent()) {
			Object msg = messages.get();
			LOGGER.info("bootLogsTopic send message ={}",msg);
			if(msg instanceof String){
				LogsInfo logsInfo=JSON.parseObject(msg.toString(), LogsInfo.class);
				logsService.save(logsInfo);
			}
		}
		
	}

}
