package com.pesu.er.controller;

import com.pesu.er.dto.PatientRegistrationRequest;
import com.pesu.er.model.ArrivalMode;
import com.pesu.er.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    private final PatientService patientService;

    public AdminController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/admin/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("patientRegistrationRequest")) {
            model.addAttribute("patientRegistrationRequest", new PatientRegistrationRequest());
        }
        model.addAttribute("arrivalModes", ArrivalMode.values());
        return "admin-register";
    }

    @PostMapping("/admin/register")
    public String registerPatient(@Valid @ModelAttribute PatientRegistrationRequest patientRegistrationRequest,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("arrivalModes", ArrivalMode.values());
            return "admin-register";
        }

        patientService.registerPatient(patientRegistrationRequest);
        redirectAttributes.addFlashAttribute("successMessage", "Patient registered successfully.");
        return "redirect:/";
    }
}
