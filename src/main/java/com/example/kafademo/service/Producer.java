package com.example.kafademo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    /* create 2 topics */
    public void sendMessage(String topicName, String message) {
        logger.info("For Topic: "+topicName," Producing message ->"+ message);
        this.kafkaTemplate.send(topicName, message);
    }
}
