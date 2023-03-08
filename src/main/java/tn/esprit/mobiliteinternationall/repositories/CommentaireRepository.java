package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.mobiliteinternationall.entites.Commentaire;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {

    @Query("SELECT commentaire from Commentaire commentaire"+
            "   GROUP BY (commentaire.idCommentaire)"+
            "   ORDER BY (commentaire.dateCommentaire) DESC ")
    List<Commentaire> triComment();
}
