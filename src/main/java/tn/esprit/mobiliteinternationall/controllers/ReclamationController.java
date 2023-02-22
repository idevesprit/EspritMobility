package tn.esprit.mobiliteinternationall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.mobiliteinternationall.entites.Reclamation;
import tn.esprit.mobiliteinternationall.services.ReclamationService;

@RestController
public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;

    //http://localhost:9090/EspritMobility/add-Reclamation
    @PostMapping("/add-Reclamation")
    @ResponseBody
    public Reclamation addReclamation (@RequestBody Reclamation r){

        reclamationService.addReclamation(r);
        return r;
    }
}