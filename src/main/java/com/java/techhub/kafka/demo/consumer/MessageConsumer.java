/**
 * 
 */
package com.java.techhub.kafka.demo.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.techhub.kafka.demo.model.NewSampleMessage;
import com.java.techhub.kafka.demo.model.SampleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * Consumer class for kafka listeners
 * In order to subsribe on new topics do add @KafkaListener and topic name.
 */
@Slf4j
@Service
public class MessageConsumer {

	@KafkaListener(topics = "spring-local-kafka")
	public void subsribeToTopicLocalKafka(@Payload String message,
										  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
										  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
										  @Header(KafkaHeaders.OFFSET) long offset) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		SampleMessage payload = objectMapper.readValue(message, SampleMessage.class);
		log.info("[ CONSUMER - topic: {} ] Message consumed: {}", topic, payload);
	}

	/**
	 * Example of a new listener
	 * @param message - message that has been published
	 */
	@KafkaListener(topics = "new-message")
	public void subsribeToNewMessage(@Payload String message,
									 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
									 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
									 @Header(KafkaHeaders.OFFSET) long offset) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		NewSampleMessage payload = objectMapper.readValue(message, NewSampleMessage.class);
		log.info("[ CONSUMER - topic: {} ] Message consumed: {}", topic, payload);
	}
}
