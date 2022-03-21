/**
 *
 */
package com.java.techhub.kafka.demo.controller;

import com.java.techhub.kafka.demo.model.NewSampleMessage;
import com.java.techhub.kafka.demo.model.SampleMessage;
import com.java.techhub.kafka.demo.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Kafka controller to manually produce message to desired topic
 * Provide $topicName as parameter to the URL
 *
 */
@RestController
@RequestMapping("/kafka/publish")
public class KafkaController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping
    public ResponseEntity<Map<String, Object>> produceMessage(@RequestBody SampleMessage sampleMessage, @RequestParam String topicName) throws Exception {
        Map<String, Object> map = messageProducer.sendMessage(sampleMessage, topicName);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * eg Endpoint to publish message to a new topic
     * @param sampleMessage
     * @param topicName
     * @return
     * @throws Exception
     */
    @PostMapping("/new")
    public ResponseEntity<Map<String, Object>> produceMessage(@RequestBody NewSampleMessage sampleMessage, @RequestParam String topicName) throws Exception {
        Map<String, Object> map = messageProducer.sendMessage(sampleMessage, topicName);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
