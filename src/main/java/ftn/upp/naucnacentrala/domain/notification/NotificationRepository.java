package ftn.upp.naucnacentrala.domain.notification;

import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
