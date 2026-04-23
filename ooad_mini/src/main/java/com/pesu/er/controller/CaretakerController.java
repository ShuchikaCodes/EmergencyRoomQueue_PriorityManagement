package com.pesu.er.controller;

import com.pesu.er.service.NotificationService;
import com.pesu.er.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaretakerController {

    private final PatientService patientService;
    private final NotificationService notificationService;

    public CaretakerController(PatientService patientService, NotificationService notificationService) {
        this.patientService = patientService;
        this.notificationService = notificationService;
    }

    @GetMapping("/caretaker/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("patients", patientService.findAllPatients());
        model.addAttribute("notifications", notificationService.recentNotifications());
        return "caretaker-dashboard";
    }
}
