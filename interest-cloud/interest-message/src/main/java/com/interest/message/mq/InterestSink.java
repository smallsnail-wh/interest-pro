package com.interest.message.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InterestSink {

    String MESSAGE_INPUT = "message_input";

    @Input(InterestSink.MESSAGE_INPUT)
    SubscribableChannel messageInput();

}
