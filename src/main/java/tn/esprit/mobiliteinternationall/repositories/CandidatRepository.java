package com.supportportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supportportal.domain.Candidat;
import com.supportportal.domain.RoleCandidat;



public interface CandidatRepository extends JpaRepository<Candidat,Integer> {
 //   User findByFirstNameAndLastNameAndRole(String firstName, String lastName, Role role);

    Candidat findCandidatByRoleCandidat(RoleCandidat roleCandidat);
}
