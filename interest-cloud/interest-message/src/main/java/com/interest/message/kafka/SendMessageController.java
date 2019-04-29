package com.interest.message.kafka;

import com.interest.common.model.ResponseWrapper;
import com.interest.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(InterestSource.class)
public class SendMessageController {

    @Autowired
    private InterestSource interestSource;

    @GetMapping("/public/kafka/send")
    public ResponseWrapper<String> kafkaSend() {
        InterestPerson interestPerson = new InterestPerson();
        interestPerson.setName("小米");
        interestPerson.setAge(18);
        interestPerson.setSex("男");

        interestSource.messageOutput().send(MessageBuilder.withPayload(interestPerson).build());
        return new ResponseWrapper<>("success");
    }

}
