package com.pv.adverse.kafka;

import com.pv.adverse.model.AdverseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdverseEventProducer {

    private static final String TOPIC = "adverse-events";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(AdverseEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
