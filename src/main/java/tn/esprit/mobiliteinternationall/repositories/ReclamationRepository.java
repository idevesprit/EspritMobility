package com.supportportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supportportal.domain.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
}
