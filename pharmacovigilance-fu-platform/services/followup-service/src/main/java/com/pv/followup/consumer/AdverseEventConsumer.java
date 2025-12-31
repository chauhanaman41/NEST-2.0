package com.pv.followup.consumer;

import com.pv.followup.model.AdverseEventDTO;
import com.pv.followup.model.FollowUpCase;
import com.pv.followup.producer.NotificationProducer;
import com.pv.followup.repository.FollowUpCaseRepository;
import com.pv.followup.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdverseEventConsumer {

    @Autowired
    private FollowUpCaseRepository repository;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private NotificationProducer notificationProducer;

    @KafkaListener(topics = "adverse-events", groupId = "followup-group")
    public void consume(AdverseEventDTO event) {
        System.out.println("Received event: " + event.getId());

        boolean isIncomplete = false;
        StringBuilder missingInfo = new StringBuilder();

        // Validation Logic
        if (event.getDescription() == null || event.getDescription().length() < 20) {
            isIncomplete = true;
            missingInfo.append("Description too short. ");
        }
        if (event.getPatientDetails() == null || event.getPatientDetails().isEmpty()) {
            isIncomplete = true;
            missingInfo.append("Patient details missing. ");
        }

        if (isIncomplete) {
            // Priority Calculation via Redis Mock
            String priority = priorityService.calculatePriority(event.getReporterId());

            // Create FollowUpCase
            FollowUpCase followUpCase = new FollowUpCase();
            followUpCase.setCaseId("CASE-" + UUID.randomUUID().toString().substring(0, 8));
            followUpCase.setOriginalEventId(event.getId());
            followUpCase.setStatus("OPEN");
            followUpCase.setPriority(priority);
            followUpCase.setMissingInfo(missingInfo.toString());

            repository.save(followUpCase);
            System.out.println("Created FollowUp Case: " + followUpCase.getCaseId());

            // Trigger RabbitMQ Notification
            String notificationMsg = "Action Required: Follow up on Case " + followUpCase.getCaseId() + 
                                     " (Priority: " + priority + "). Missing: " + missingInfo;
            notificationProducer.sendNotification(notificationMsg);
        }
    }
}
