package tn.esprit.mobiliteinternationall.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;
import tn.esprit.mobiliteinternationall.entites.StatutCandidature;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.CandidatureRepository;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.stream.DoubleStream;
@AllArgsConstructor
@Service
public class ICandidatureServiceImp implements ICandidatureService {

    CandidatureRepository candidatureRepository;
    CandidatRepository candidatRepository;

    @Override
    public Candidature addCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature updateCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature getById(Integer idCandidature) {
        return candidatureRepository.findById(idCandidature).orElse(null);
    }

    @Override
    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    @Override
    public void removeCandidature(Integer idCandidature) {
        candidatureRepository.deleteById(idCandidature);

    }

    @Override
    public void assignCandidatureToCandidat(Integer idCandidature, RoleCandidat roleCandidat) {
        Candidature candidature = candidatureRepository.findById(idCandidature).orElse(null);
        Candidat candidat = candidatRepository.findCandidatByRoleCandidat(roleCandidat);
        candidature.setCandidat(candidat);
        candidatureRepository.save(candidature);
    }

    @Override
    public List<Candidature> findCandidatureByMoyenne(Float moyenne1, Float moyenne2) {
        return candidatureRepository.findByMoyenneBetween(moyenne1,moyenne2);

    }

     @Override
    public Integer nbrCandidaturesAcceptées(StatutCandidature statutCandidature) {
        return candidatureRepository.countByStatutCandidature(statutCandidature);
    }







  /*  @Override
    public Candidature saveCandidature(Candidature candidature, MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            candidature
                    = new Candidature(
                    file.getBytes());
            return candidatureRepository.save(candidature);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }

    }


    @Override
    public Candidature getCandidature(Integer fileId) throws Exception {
        return candidatureRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

*/





}



