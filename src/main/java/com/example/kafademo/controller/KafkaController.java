package com.example.kafademo.controller;

import com.example.kafademo.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

//    @PostMapping(value = "/publish1")
//    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
//        this.producer.sendMessage(message);
//    }
    @PostMapping(value = "/publish")
    public void sendMessageToKafka(@RequestParam("topicName") String topicName, @RequestParam("message") String message) {
        this.producer.sendMessage(topicName, message);
    }
}
