package com.pv.notification.consumer;

import com.pv.notification.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void consume(String message) {
        // Parse the message to extract info if possible, or just log the raw message
        // Example format: "Action Required: Follow up on Case CASE-XYZ (Priority: HIGH). Missing: ..."
        
        System.out.println("[NOTIFICATION SERVICE] 📧 Sending alert: " + message);
    }
}
