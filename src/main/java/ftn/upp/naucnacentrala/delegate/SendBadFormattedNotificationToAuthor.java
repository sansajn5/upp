package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.notification.Notification;
import ftn.upp.naucnacentrala.domain.notification.NotificationRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendBadFormattedNotificationToAuthor  implements JavaDelegate {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Getting authorId to send him badlyFormatted notification");

        Long authorId = Long.parseLong((String)execution.getVariable("userId"));

        System.out.println("Creating badlyFormatted notification...");
        Notification notification = new Notification("Your work is badly formatted, you have 1 day to" +
            "make it right", authorId);
        notificationRepository.save(notification);

        System.out.println("Notification with id: " + notification.getId() + " is sent!");
    }
}
