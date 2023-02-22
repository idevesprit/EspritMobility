package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;
import tn.esprit.mobiliteinternationall.entites.Universite;

public interface UniversiteRepository extends JpaRepository<Universite,Integer> {
}
