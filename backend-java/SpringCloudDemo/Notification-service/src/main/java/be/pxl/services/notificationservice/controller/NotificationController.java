package be.pxl.services.notificationservice.controller;

import be.pxl.services.notificationservice.domain.Notification;
import be.pxl.services.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@RequestBody Notification notification){
      //  log.info("Received notification request at /notification/ endpoint");
     //   log.info("Notification details: {}", notification);
        notificationService.sendMessage(notification);
    }
}
