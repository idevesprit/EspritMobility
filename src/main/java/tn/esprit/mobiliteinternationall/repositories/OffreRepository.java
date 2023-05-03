package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.mobiliteinternationall.entites.Offre;
import tn.esprit.mobiliteinternationall.entites.Universite;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre,Integer> {
    @Query(value = "SELECT * FROM offre " +
            "WHERE universite_id_universite=:id " , nativeQuery = true)
    List<Offre> getoffreuniversite(@Param("id") int id_universite);

}
