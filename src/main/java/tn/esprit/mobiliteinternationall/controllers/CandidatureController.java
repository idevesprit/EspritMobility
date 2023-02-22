package tn.esprit.mobiliteinternationall.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.mobiliteinternationall.ResponseData;
import tn.esprit.mobiliteinternationall.dto.MailRequest;
import tn.esprit.mobiliteinternationall.dto.MailResponse;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;
import tn.esprit.mobiliteinternationall.entites.StatutCandidature;
import tn.esprit.mobiliteinternationall.repositories.CandidatureRepository;
import tn.esprit.mobiliteinternationall.services.EmailService;
import tn.esprit.mobiliteinternationall.services.ICandidatureService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/candidature")
public class CandidatureController {

    Logger LOGGER = LoggerFactory.getLogger(CandidatureController.class);
    @Autowired
    ICandidatureService iCandidatureService;
    @Autowired
    private EmailService service;
   @Autowired
    private CandidatureRepository candidatureRepository;

    @PostMapping
    public Candidature addCandidature(@RequestBody Candidature candidature) {
        candidature.setStatutCandidature(StatutCandidature.EnCoursDeTraitement);
        Date currentDate = Calendar.getInstance().getTime();
        candidature.setDebutMobilite(currentDate);
        //LOGGER.info("[CandidatureController] - Inside getStudents method");
        return iCandidatureService.addCandidature(candidature);

    }

    @DeleteMapping("/{idCandidature}")
    public ResponseEntity removeCandidature(@PathVariable int idCandidature) {

        iCandidatureService.removeCandidature(idCandidature);
        return new ResponseEntity<>("Candidature a été Supprimé!", HttpStatus.OK);

    }

    @PutMapping
    public Candidature updateCandidature(@RequestBody Candidature candidature) {
        Date currentDate = Calendar.getInstance().getTime();
        candidature.setDebutMobilite(currentDate);
        return iCandidatureService.updateCandidature(candidature);
    }

    @GetMapping
    public List<Candidature> findAllCandidatures() {
        return iCandidatureService.getAllCandidatures();
    }

    @GetMapping("/{idFact}")
    public Candidature findCandidatureById(@PathVariable int idCandidature) {
        return iCandidatureService.getById(idCandidature);
    }

    @GetMapping("/assignCandidatureToCandidat/{idCandidature}/{roleCandidat}")
    public ResponseEntity assignCandidatureToCandidat(@PathVariable int idCandidature, @PathVariable RoleCandidat roleCandidat) {

        iCandidatureService.assignCandidatureToCandidat(idCandidature, roleCandidat);
        return new ResponseEntity<>("Candidature Affecté", HttpStatus.OK);

    }



    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "Esprit");
        return service.sendEmail(request, model);

    }



    @GetMapping("/MoyenneFilter/{moyenne1}/{moyenne2}")
    public List<Candidature> MoyenneFilter(@PathVariable Float moyenne1,@PathVariable Float moyenne2) {
        LOGGER.info("[CandidatureController] - Inside Moyenne Filter method");
        return iCandidatureService.findCandidatureByMoyenne(moyenne1,moyenne2);
    }


    @GetMapping("/countByStatutCandidature/{statutCandidature}")
    public Integer nbrCandidaturesAcceptées(@PathVariable StatutCandidature statutCandidature) {
        return iCandidatureService.nbrCandidaturesAcceptées(statutCandidature);
    }

    @PostMapping("/upload")
    public ResponseEntity ajouterCandidature(@RequestParam("CV") MultipartFile cv, @RequestParam("Lettre de Motivation") MultipartFile lettre, @RequestParam("Moyenne") Float moyenne) {
        Candidature c = new Candidature();
        try {
            c.setCv(cv.getBytes());
            c.setLettreMotivation(lettre.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.setStatutCandidature(StatutCandidature.EnCoursDeTraitement);
        Date currentDate = Calendar.getInstance().getTime();
        c.setDebutMobilite(currentDate);
        c.setMoyenne(moyenne);
        candidatureRepository.save(c);

        return new ResponseEntity<>("Candidature Ajoutée ", HttpStatus.OK);

    }

   @GetMapping("/upload/{idCandidature}")
   public ResponseEntity<byte[]> getFile(@PathVariable Integer idCandidature) {
       Optional<Candidature> fileEntity = candidatureRepository.findById(idCandidature);
       if (fileEntity.isPresent()) {
           byte[] fileContent = fileEntity.get().getCv();
           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_PDF);
           headers.setContentLength(fileContent.length);
           headers.set("Content-Disposition", "inline; filename=" + fileEntity.get().getCv());
           return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }




}