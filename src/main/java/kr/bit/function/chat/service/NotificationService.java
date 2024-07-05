package kr.bit.function.chat.service;


import kr.bit.function.chat.dao.NotificationDAO;
import kr.bit.function.chat.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    public void addNotification(Notification notification) {
        notificationDAO.addNotification(notification);
    }

    public List<Notification> getNotifications() {
        return notificationDAO.getNotifications();
    }

    public void clearNotifications() {
        notificationDAO.clearNotifications();
    }
}
