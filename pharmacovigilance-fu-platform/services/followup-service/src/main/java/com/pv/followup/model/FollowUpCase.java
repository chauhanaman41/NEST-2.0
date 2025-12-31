package com.pv.followup.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "followup_cases")
public class FollowUpCase {

    @Id
    private String id;
    
    private String caseId; // Unique business key
    private String originalEventId;
    private String status; // OPEN, CLOSED
    private String priority; // HIGH, LOW, MEDIUM
    private String missingInfo; // Details of what is missing
}
