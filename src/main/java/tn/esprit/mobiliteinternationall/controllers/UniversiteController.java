package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.Services.ICandidatServices;
import tn.esprit.mobiliteinternationall.Services.IServiceUniversiteServices;
import tn.esprit.mobiliteinternationall.Services.IUniversiteServices;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Universite;

@AllArgsConstructor
@RestController
@RequestMapping("/Universite")
public class UniversiteController {
    @Autowired
    IUniversiteServices iUniversiteServices;


    @GetMapping("/FindById/{idUniversite}")
    public Universite FindById(@PathVariable int idUniversite) {
        return iUniversiteServices.getById(idUniversite) ;  }
}
