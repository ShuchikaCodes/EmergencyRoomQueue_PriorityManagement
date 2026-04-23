package com.pesu.er.controller;

import com.pesu.er.dto.TriageRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TriageController {

    private final PatientService patientService;
    private final ERQueueService queueService;

    public TriageController(PatientService patientService, ERQueueService queueService) {
        this.patientService = patientService;
        this.queueService = queueService;
    }

    @GetMapping("/triage/{id}")
    public String showAssessmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.findPatient(id));
        if (!model.containsAttribute("triageRequest")) {
            model.addAttribute("triageRequest", new TriageRequest());
        }
        return "triage-assess";
    }

    @PostMapping("/triage/{id}")
    public String assessPatient(@PathVariable Long id,
                                @Valid @ModelAttribute TriageRequest triageRequest,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patient", patientService.findPatient(id));
            return "triage-assess";
        }

        queueService.assessAndQueue(id, triageRequest);
        redirectAttributes.addFlashAttribute("successMessage", "Severity assessed and patient added to queue.");
        return "redirect:/";
    }
}
