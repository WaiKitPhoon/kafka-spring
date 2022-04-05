package com.java.techhub.kafka.demo.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.techhub.kafka.demo.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Generic producer to produce all messages
 */
@Slf4j
@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Map<String, Object> sendMessage(Object message, String topic) throws ExecutionException, InterruptedException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String messageString = objectMapper.writeValueAsString(message);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic, messageString);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("[ PRODUCER - topic: {} ] Message successfully sent : {}", topic, message);

            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message : {} due to : {}", message, ex.getMessage());
            }
        });

        return populateRecordDetails(future, topic);
    }

    private Map<String, Object> populateRecordDetails(ListenableFuture<SendResult<String, String>> future, String topic)
            throws ExecutionException, InterruptedException {
        Map<String, Object> returnMap = new HashMap<>();

        returnMap.put("topic", topic);
        returnMap.put("published-at", DateTimeUtil.toLocalDateTime(future.get().getRecordMetadata().timestamp()));
        returnMap.put("message", future.get().getProducerRecord().value());
        returnMap.put("offset", future.get().getRecordMetadata().offset());
        return returnMap;
    }


}
