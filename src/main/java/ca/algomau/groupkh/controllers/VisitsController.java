package ca.algomau.groupkh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.algomau.groupkh.beans.Visits;
import ca.algomau.groupkh.database.VisitsDatabaseAccess;

@Controller
public class VisitsController {

    @Autowired
    private VisitsDatabaseAccess da;

    @GetMapping("/visits")
    public String index(Model model) {
        model.addAttribute("visitList", da.getVisitList());
        model.addAttribute("visit", new Visits());
        return "visits";
    }

    @PostMapping("/insertVisit")
    public String insertVisit(Model model, @ModelAttribute Visits visit) {
        da.insertVisit(visit);
        model.addAttribute("visitList", da.getVisitList());
        model.addAttribute("visit", new Visits());
        return "visits";
    }

    @GetMapping("/searchVisits")
    public String searchVisits(Model model, @ModelAttribute Visits searchCriteria) {
      model.addAttribute("visitList", da.searchVisits(searchCriteria));
        return "visits";
    }
}
