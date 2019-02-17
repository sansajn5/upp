package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.notification.Notification;
import ftn.upp.naucnacentrala.domain.notification.NotificationRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyChiefEditor implements JavaDelegate {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Creating notification for failed review");
        String recieverId = (String)execution.getVariable("scientificFieldEditorId");
        System.out.println("Reciever is: " + recieverId);
        notificationRepository.save(
            new Notification(
                "Review wasn't done on time. Slect new reviewer",
                Long.parseLong(recieverId)
            )
        );
    }
}
