package com.supportportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supportportal.domain.Candidat;
import com.supportportal.repository.CandidatRepository;


@Service
public class ServiceCandidat implements ICandidatService {

	@Autowired
	CandidatRepository candidatRepository  ;
	
	@Override
	public Candidat addCandidat(Candidat E) {
		return candidatRepository.save(E);
	}

	@Override
	public Candidat updateCandidat(Candidat E) {
		return candidatRepository.save(E);	}

	@Override
	public Candidat getCandidatId(Integer id) {
		return candidatRepository.findById(id).orElse(null);
	}

	@Override
	public List<Candidat> getAllCandidats() {
		
		return candidatRepository.findAll();
	}

	@Override
	public void removeCandidat(Integer id) {
		
		candidatRepository.deleteById(id);
	}

	
	
}
