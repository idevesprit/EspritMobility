package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {
}
