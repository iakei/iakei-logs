package org.iakei.log.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaListeners {
	void Listener(ConsumerRecord<?, ?> record);
}
