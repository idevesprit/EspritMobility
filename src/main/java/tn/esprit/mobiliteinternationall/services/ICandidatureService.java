package tn.esprit.mobiliteinternationall.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;
import tn.esprit.mobiliteinternationall.entites.StatutCandidature;

import java.util.List;

public interface ICandidatureService {
    public Candidature addCandidature(Candidature candidature);
    public Candidature updateCandidature(Candidature candidature);
    public Candidature getById(Integer idCandidature);

    public List<Candidature> getAllCandidatures();
    public void removeCandidature(Integer idCandidature);
    public void assignCandidatureToCandidat(Integer idCandidature, RoleCandidat roleCandidat);

    public List<Candidature> findCandidatureByMoyenne(Float moyenne1, Float moyenne2);

    public Integer nbrCandidaturesAcceptées(StatutCandidature statutCandidature);





}
