package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.magazine.MagazineRepository;
import ftn.upp.naucnacentrala.domain.notification.Notification;
import ftn.upp.naucnacentrala.domain.notification.NotificationRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendFailureNotification implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Sending failure notification...");

        final Long recieverId = Long.parseLong((String)execution.getVariable("userId"));

        System.out.println("Creating failure notification...");
        Notification notification = new Notification("Author is notified that his work was not accepted", recieverId);
        notificationRepository.save(notification);

        System.out.println("Notification with id: " + notification.getId() + " is sent!");

    }
}
