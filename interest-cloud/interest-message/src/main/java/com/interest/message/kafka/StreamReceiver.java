package com.interest.message.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InterestSink.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(InterestSink.MESSAGE_INPUT)
    public void process(Object message){
        log.info("StreamReceiver: {}",message);
    }

}
