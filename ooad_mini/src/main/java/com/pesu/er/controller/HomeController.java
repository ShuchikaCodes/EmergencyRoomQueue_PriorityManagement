package com.pesu.er.controller;

import com.pesu.er.service.ERQueueService;
import com.pesu.er.service.NotificationService;
import com.pesu.er.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PatientService patientService;
    private final ERQueueService queueService;
    private final NotificationService notificationService;

    public HomeController(PatientService patientService,
                          ERQueueService queueService,
                          NotificationService notificationService) {
        this.patientService = patientService;
        this.queueService = queueService;
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("patients", patientService.findAllPatients());
        model.addAttribute("queue", queueService.getQueue());
        model.addAttribute("notifications", notificationService.recentNotifications());
        return "index";
    }
}
