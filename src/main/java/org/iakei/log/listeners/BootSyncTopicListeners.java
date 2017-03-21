package org.iakei.log.listeners;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BootSyncTopicListeners implements KafkaListeners {

	private static final Logger LOGGER=LoggerFactory.getLogger(BootSyncTopicListeners.class);
	@Override
	@KafkaListener(topics = { "bootSyncTopic" })
	public void Listener(ConsumerRecord<?, ?> record) {
		Optional<?> messages = Optional.ofNullable(record.value());
		if (messages.isPresent()) {
			Object msg = messages.get();
			LOGGER.info("bootSyncTopic send message ={}",msg);
		}
		
	}
}
