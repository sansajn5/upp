package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.magazine.MagazineRepository;
import ftn.upp.naucnacentrala.domain.notification.Notification;
import ftn.upp.naucnacentrala.domain.notification.NotificationRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendSuccessNotification implements JavaDelegate {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Creating success notification...");

        final Long recieverId = Long.parseLong((String)execution.getVariable("userId"));



        Notification notification = new Notification("Your work is accepted!", recieverId);
        System.out.println("Sending success notification...");
        notificationRepository.save(notification);

        System.out.println("Notification with id: " + notification.getId() + " is sent!");

    }
}
