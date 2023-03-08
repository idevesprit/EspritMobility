package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.StatutCandidature;

import java.util.List;
import java.util.Optional;

public interface CandidatureRepository extends JpaRepository<Candidature,Integer> {

    List<Candidature> findByMoyenneBetween(Float moyenne1, Float moyenne2);

    Integer countByStatutCandidature(StatutCandidature statutCandidature);

   // MultipartFile findByCvAndAndLettreMotivation(Integer idCandidature);







}
