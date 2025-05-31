package Controller;

import Entity.Notification;
import Service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAll() {
        return notificationService.getAll();
    }

    @GetMapping("/{id}")
    public Notification getById(@PathVariable Long id) {
        return notificationService.getById(id).orElse(null);
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getByUserId(@PathVariable Long userId) {
        return notificationService.getByUserId(userId);
    }

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }
}
