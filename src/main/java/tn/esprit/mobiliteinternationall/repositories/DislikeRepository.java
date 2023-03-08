package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.DislikeComment;


public interface DislikeRepository extends JpaRepository<DislikeComment,Integer> {
}
