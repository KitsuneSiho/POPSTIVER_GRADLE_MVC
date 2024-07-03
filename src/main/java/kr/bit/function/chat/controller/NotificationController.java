package kr.bit.function.chat.controller;

import kr.bit.function.chat.model.Notification;
import kr.bit.function.chat.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add")
    public String addNotification(@RequestBody Notification notification) {
        notificationService.addNotification(notification);
        return "Notification added successfully";
    }

    @GetMapping("/get")
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }

    @DeleteMapping("/clear")
    public String clearNotifications() {
        notificationService.clearNotifications();
        return "Notifications cleared successfully";
    }
}
