package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.mobiliteinternationall.entites.*;

import java.util.List;
import java.util.Optional;

public interface CandidatureRepository extends JpaRepository<Candidature,Integer> {

    List<Candidature> findByMoyenneBetween(Float moyenne1, Float moyenne2);

    Integer countByStatutCandidature(StatutCandidature statutCandidature);

   // MultipartFile findByCvAndAndLettreMotivation(Integer idCandidature);

    @Query(value = "SELECT * FROM candidature,offre  " + "WHERE  candidature.offre_id_offre=offre.id_offre AND offre.universite_id_universite =:id and " + "statut_candidature= \"AcceptéPourEntretient \"", nativeQuery = true)
    List<Candidature> getcondidaturebyuniversite(@Param("id") int id_universite);





}
