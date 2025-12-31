package com.pv.followup.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdverseEventDTO {
    private String id;
    private String reporterId;
    private String patientDetails;
    private String description;
    private String drugName;
    private LocalDateTime timestamp;
    private LocalDateTime reportedAt;
    private String status;
}
