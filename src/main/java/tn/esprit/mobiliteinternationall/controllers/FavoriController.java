package tn.esprit.mobiliteinternationall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.Favori;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;
import tn.esprit.mobiliteinternationall.entites.StatutCandidature;
import tn.esprit.mobiliteinternationall.services.EmailService;
import tn.esprit.mobiliteinternationall.services.ICandidatureService;
import tn.esprit.mobiliteinternationall.services.IFavoriService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/favori")
public class FavoriController {
    @Autowired
    IFavoriService iFavoriService;

    @DeleteMapping("/{idFavori}")
    public ResponseEntity removeFavori(@PathVariable int idFavori) {

        iFavoriService.removeFavori(idFavori);
        return new ResponseEntity<>("Favori a été Supprimé!", HttpStatus.OK);

    }

    @PutMapping
    public Favori updateFavori(@RequestBody Favori favori) {
        return iFavoriService.updateFavori(favori);
    }

    @GetMapping
    public List<Favori> findAllFavoris() {
        return iFavoriService.getAllFavoris();
    }

    @GetMapping("/{idFavori}")
    public Favori findFavoriById(@PathVariable int idFavori) {
        return iFavoriService.getById(idFavori);
    }



    @PostMapping("/assignCandidatToOffre/{idCandidat}/{idOffre}")
    public ResponseEntity assignCandidatToOffre(@RequestBody Favori favori, @PathVariable int idCandidat, @PathVariable int idOffre) {

        iFavoriService.assignCandidatToOffre(favori, idCandidat, idOffre);
        return new ResponseEntity<>("Favori Affecté", HttpStatus.OK);


    }

}
