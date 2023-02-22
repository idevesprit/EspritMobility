package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.Chat;
import tn.esprit.mobiliteinternationall.entites.Commentaire;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
}
