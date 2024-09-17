package ca.algomau.groupkh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.algomau.groupkh.beans.Doctor;
import ca.algomau.groupkh.database.DoctorDatabaseAccess;

@Controller
public class DoctorController {

    @Autowired
    private DoctorDatabaseAccess da;
    
    @GetMapping("/")
    public String root() {
    	return "home";
    }

    @GetMapping("/doctors")
    public String index(Model model) {
       return "doctors";
    }

    @PostMapping("/insertDoctor")
    public String insertDoctor(Model model, @ModelAttribute Doctor doctor) {
        da.insertDoctor(doctor);
        model.addAttribute("doctorList", da.getDoctorList());
        model.addAttribute("doctor", new Doctor());
        return "doctors";
    }

    @GetMapping("/searchDoctors")
    public String searchDoctors(Model model, @ModelAttribute Doctor searchCriteria) {
        model.addAttribute("doctorList", da.searchDoctors(searchCriteria));
        return "doctors";
    }
}
