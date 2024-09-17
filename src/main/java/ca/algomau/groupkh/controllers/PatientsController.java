package ca.algomau.groupkh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.algomau.groupkh.beans.Patients;
import ca.algomau.groupkh.database.PatientsDatabaseAccess;

@Controller
public class PatientsController {

    @Autowired
    private PatientsDatabaseAccess da;

    @GetMapping("/patients")
    public String index(Model model) {
       // model.addAttribute("patientList", da.getPatientList());
        //model.addAttribute("patient", new Patients());
        return "patients";
    }

    @PostMapping("/insertPatient")
    public String insertPatient(Model model, @ModelAttribute Patients patient) {
        //da.insertPatient(patient);
       // model.addAttribute("patientList", da.getPatientList());
        //model.addAttribute("patient", new Patients());
        return "patients";
    }

    @GetMapping("/searchPatients")
    public String searchPatients(Model model, @ModelAttribute Patients searchCriteria) {
        model.addAttribute("patientList", da.searchPatients(searchCriteria));
        return "patients";
    }
}
