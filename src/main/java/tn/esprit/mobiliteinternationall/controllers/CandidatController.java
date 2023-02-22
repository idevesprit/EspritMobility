package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.mobiliteinternationall.Services.ICandidatServices;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;

@AllArgsConstructor
@RestController
@RequestMapping("/Candidat")

public class CandidatController {
    @Autowired
    ICandidatServices iCandidatServices;
    @GetMapping("/FindById/{idCandidat}")
    public Candidat FindById(@PathVariable int idCandidat) {
        return iCandidatServices.getById(idCandidat);
    }
}
