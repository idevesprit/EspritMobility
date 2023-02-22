package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;


public interface CandidatRepository extends JpaRepository<Candidat,Integer> {
 //   User findByFirstNameAndLastNameAndRole(String firstName, String lastName, Role role);

    Candidat findCandidatByRoleCandidat(RoleCandidat roleCandidat);
}
