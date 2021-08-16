package com.example.kafademo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaIntegrationTest {
    private String topicName;
    @Autowired
    private com.example.kafademo.service.Consumer consumer;

    @Autowired
    private com.example.kafademo.service.Producer producer;

    @Value("${topics}")
    private String topic;
    @BeforeEach
    void beforeTest() {
        topicName = getClass().getSimpleName();
    }
    @Test
    public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
            throws Exception {
        producer.sendMessage(topic, "Sending Sample message");

        Assertions.assertEquals(consumer.consume();, topic, "Topic should have 3 partitions");
    }
}