package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.magazine.MagazineRepository;
import ftn.upp.naucnacentrala.domain.notification.Notification;
import ftn.upp.naucnacentrala.domain.notification.NotificationRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendNotificationsAfterSubmittingWork implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println("Sending notifications...");

        final Long recieverId = magazineRepository.findById((Long)execution.getVariable("magazineId"))
            .get()
            .getMainEditor()
            .getId();

        Notification notification = new Notification("Chief editor checks submitted work", recieverId);
        notificationRepository.save(notification);

        System.out.println("Notification with id: " + notification.getId() + " is sent!");

        System.out.println("Setting execution variable chiefEditorId");
        execution.setVariable("chiefEditorId", recieverId.toString());
    }
}
