package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.LikeComment;

public interface LikeRepository extends JpaRepository<LikeComment,Integer> {
}
