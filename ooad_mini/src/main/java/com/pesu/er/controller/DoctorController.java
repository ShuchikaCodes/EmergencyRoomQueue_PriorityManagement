package com.pesu.er.controller;

import com.pesu.er.dto.PriorityOverrideRequest;
import com.pesu.er.dto.TreatmentRequest;
import com.pesu.er.service.ERQueueService;
import com.pesu.er.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DoctorController {

    private final ERQueueService queueService;
    private final PatientService patientService;

    public DoctorController(ERQueueService queueService, PatientService patientService) {
        this.queueService = queueService;
        this.patientService = patientService;
    }

    @GetMapping("/doctor/queue")
    public String doctorQueue(Model model) {
        model.addAttribute("queue", queueService.getQueue());
        model.addAttribute("nextPatient", queueService.getNextPatient());
        return "doctor-queue";
    }

    @GetMapping("/doctor/override/{id}")
    public String showOverrideForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.findPatient(id));
        if (!model.containsAttribute("priorityOverrideRequest")) {
            model.addAttribute("priorityOverrideRequest", new PriorityOverrideRequest());
        }
        return "doctor-override";
    }

    @PostMapping("/doctor/override/{id}")
    public String overridePriority(@PathVariable Long id,
                                   @Valid @ModelAttribute PriorityOverrideRequest priorityOverrideRequest,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patient", patientService.findPatient(id));
            return "doctor-override";
        }

        queueService.overridePriority(id, priorityOverrideRequest);
        redirectAttributes.addFlashAttribute("successMessage", "Priority overridden successfully.");
        return "redirect:/doctor/queue";
    }

    @GetMapping("/doctor/treat/{id}")
    public String showTreatmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.findPatient(id));
        if (!model.containsAttribute("treatmentRequest")) {
            model.addAttribute("treatmentRequest", new TreatmentRequest());
        }
        return "doctor-treat";
    }

    @PostMapping("/doctor/treat/{id}")
    public String startTreatment(@PathVariable Long id,
                                 @Valid @ModelAttribute TreatmentRequest treatmentRequest,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patient", patientService.findPatient(id));
            return "doctor-treat";
        }

        queueService.startTreatment(id, treatmentRequest);
        redirectAttributes.addFlashAttribute("successMessage", "Treatment started successfully.");
        return "redirect:/doctor/queue";
    }

    @PostMapping("/doctor/discharge/{id}")
    public String discharge(@PathVariable Long id,
                            @RequestParam(defaultValue = "Treatment completed and patient discharged.") String note,
                            RedirectAttributes redirectAttributes) {
        queueService.dischargePatient(id, note);
        redirectAttributes.addFlashAttribute("successMessage", "Patient discharged successfully.");
        return "redirect:/";
    }
}
