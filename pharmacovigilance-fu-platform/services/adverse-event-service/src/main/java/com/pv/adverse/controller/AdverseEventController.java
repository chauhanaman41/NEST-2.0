package com.pv.adverse.controller;

import com.pv.adverse.kafka.AdverseEventProducer;
import com.pv.adverse.model.AdverseEvent;
import com.pv.adverse.repository.AdverseEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/events")
public class AdverseEventController {

    @Autowired
    private AdverseEventRepository repository;

    @Autowired
    private AdverseEventProducer producer;

    @PostMapping
    public ResponseEntity<AdverseEvent> createEvent(@RequestBody AdverseEvent event) {
        event.setReportedAt(LocalDateTime.now());
        event.setStatus("NEW");

        // Save to MongoDB
        AdverseEvent savedEvent = repository.save(event);

        // Publish to Kafka
        producer.sendEvent(savedEvent);

        return ResponseEntity.ok(savedEvent);
    }
}
