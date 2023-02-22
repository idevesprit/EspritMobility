package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.Favori;

public interface FavoriRepository extends JpaRepository<Favori,Integer> {
}
