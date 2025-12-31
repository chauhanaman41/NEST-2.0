package com.pv.adverse.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "adverse_events")
public class AdverseEvent {

    @Id
    private String id;

    private String reporterId;
    private String patientDetails; // Renamed from patientId and genericized
    private String description;

    // Keeping additional fields but ensuring requested ones are present
    private String drugName;
    private LocalDateTime timestamp; // Renamed from incidentDate to match request
    private LocalDateTime reportedAt; // Keeping for internal tracking
    private String status; // NEW, PROCESSING, SUBMITTED
}
