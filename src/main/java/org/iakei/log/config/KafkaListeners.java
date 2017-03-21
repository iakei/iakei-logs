package org.iakei.log.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaListeners {
	void Listener(ConsumerRecord<?, ?> record);
}
