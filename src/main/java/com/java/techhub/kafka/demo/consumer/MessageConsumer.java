/**
 * 
 */
package com.java.techhub.kafka.demo.consumer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.java.techhub.kafka.demo.model.NewSampleMessage;
import com.java.techhub.kafka.demo.model.SampleMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Consumer class for kafka listeners
 * In order to subsribe on new topics do add @KafkaListener and topic name.
 */
@Slf4j
@Service
public class MessageConsumer {


	@KafkaListener(topics = "spring-local-kafka")
	public void subsribeToTopicLocalKafka(ConsumerRecord<String, SampleMessage> message) {
		log.info("[ CONSUMER - Local KAFKA] -- Start consuming message");
		SampleMessage payload = message.value();
		LocalDateTime consumedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(message.timestamp()),
				ZoneId.systemDefault());
		log.info("Consuming: {}", message.value());
		// TODO add logic handler when consuming message
		log.info("[ CONSUMER - Local KAFKA] -- Successfully consumed message with id: {} published at: {}", payload.getUserId(), consumedTime);
	}

	/**
	 * Example of a new listener
	 * @param message - message that has been published
	 */
	@KafkaListener(topics = "new-message")
	public void subsribeToNewMessage(ConsumerRecord<String, NewSampleMessage> message) {
		log.info("[ CONSUMER - {}] -- Start consuming message", message.topic());
		NewSampleMessage payload = message.value();
		LocalDateTime consumedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(message.timestamp()),
				ZoneId.systemDefault());
		log.info("Consuming: {}", message.value());
		// TODO add logic handler when consuming message
		log.info("[ CONSUMER - {}] -- Successfully consumed message with id: {} published at: {}",message.topic(), payload.getUserId(), consumedTime);
	}
}
