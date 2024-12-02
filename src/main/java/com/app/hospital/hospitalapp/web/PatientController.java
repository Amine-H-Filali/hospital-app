package com.app.hospital.hospitalapp.web;

import com.app.hospital.hospitalapp.entities.Patient;
import com.app.hospital.hospitalapp.repositories.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @GetMapping("/")
    public String Home(){

        return "redirect:/user/index";
    }


    @GetMapping("/user/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "3") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword
    ) {
        Page<Patient> pagePatients = patientRepository.findByNomContainsIgnoreCase(keyword, PageRequest.of(page, size));
        model.addAttribute("patients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("path", "index");
        return "patients";
    }

    @GetMapping("/admin/deletePatient")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "keyword") String keyword,
                         @RequestParam(name = "page") String page

    ) {
        patientRepository.deleteById(id);

        return "redirect:/user/index?keyword=" + keyword + "&page=" + page;

    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model) {

        model.addAttribute("patient", new Patient());

        return "formPatients";
    }


    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);

        return "redirect:/admin/formPatients";

    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model,Long id,
                              @RequestParam(name = "keyword") String keyword,
                              @RequestParam(name = "page") String page) {
       Patient patient= patientRepository.findById(id).orElseThrow(()->new RuntimeException("Patient introuvable"));

        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);

        return "editPatient";
    }

    @PostMapping(path = "/admin/edit")
    public String edit(@RequestParam(name = "keyword") String keyword,
                       @RequestParam(name = "page") String page, @Valid Patient patient, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "editPatient";
        Patient newPatient=patientRepository.findById(patient.getId()).orElseThrow(() -> new RuntimeException("Patient not exist with id: " + patient.getId()));

        newPatient.setNom(patient.getNom());
        newPatient.setScore(patient.getScore());
        newPatient.setPrenom(patient.getPrenom());
        newPatient.setMalade(patient.isMalade());
        newPatient.setDateNaissance(patient.getDateNaissance());
        patientRepository.save(newPatient);



        return "redirect:/user/index?keyword=" + keyword + "&page=" + page;

    }
}
