package com.interest.message.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {

    @KafkaListener(topics = "test0",groupId = "interest-message")
    public void receive(InterestPerson interestPerson) {
        log.info("receive message from kafka: {}", interestPerson.toString());
    }

}
